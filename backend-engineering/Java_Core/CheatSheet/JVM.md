# JVM — Quick Revision Sheet
*(Read this first. For deep-dive explanations & code walkthroughs, see `2_JVM_Detailed_Notes.md`)*

---

## 1. What is JVM?
- Abstract computing machine that reads **Java bytecode** and executes it.
- Core responsibilities: **load/interpret bytecode → provide security → manage memory (GC)**.
- Every `java` command → **new JVM instance** with its own memory. Multiple programs = multiple independent JVM instances.

## 2. Compilation Pipeline
```
Hello.java --(javac)--> Hello.class (bytecode) --(JVM/Interpreter+JIT)--> Machine Code --> CPU
```
| | Platform independent | Speed |
|---|---|---|
| Source code | ✅ | fastest to start writing, slow to run |
| Bytecode | ✅ | compact, optimized |
| Machine code | ❌ | fastest execution |

- **javac**: checks syntax/semantics, produces bytecode.
- **JVM itself is platform-dependent** — that's why you need a different JVM per OS, but the **same `.class` file** runs everywhere → "write once, run anywhere."

### Interpreter vs JIT
- **Interpreter**: executes bytecode line-by-line → fast startup, slow for repeated code.
- **JIT (Just-In-Time)**: JVM detects **hot spots** (frequently executed bytecode) → compiles to machine code → caches in **Code Cache** → reused directly next time (no re-interpretation).
- Code Cache cleared when: JVM exits / **deoptimization** (an earlier optimization assumption becomes invalid) / cache full.
- Startup helpers: **CDS** (shares preprocessed class metadata), **AOT compilation**, **GraalVM Native Image**.

## 3. Class Loading — 3 Phases
```
Loading → Linking (Verification → Preparation → Resolution) → Initialization
```
| Phase | What happens |
|---|---|
| **Loading** | ClassLoader finds `.class`, reads bytecode, creates `Class` object, stores metadata in **Metaspace**. No static values yet. |
| **Verification** | Bytecode checked for validity/safety → `VerifyError` if invalid. |
| **Preparation** | Memory allocated for **static variables**, set to **default values** (`0`, `false`, `null`). |
| **Resolution** | Symbolic references → actual runtime references (loads referenced classes). |
| **Initialization** | Static variables assigned **real values** + static blocks run, **in source-code order**. |

- **Lazy Class Loading**: classes loaded only when first referenced (not all at startup) → faster startup, lower memory.
- Triggers for loading a class: `new` instance, static method/field access (except compile-time constants), subclass loaded, run from command line, reflection.
- Interfaces: loaded but **not initialized** unless a static method is accessed.
- Superclass is always loaded & initialized **before** the subclass.

### ClassLoader Hierarchy (Parent Delegation Model)
```
Bootstrap CL  (java.lang.*, java.util.* — JDK core)
     ▲
Platform CL   (java.sql, java.xml, etc.)
     ▲
Application CL (your classes)
```
- Child **always asks parent first**; only loads itself if parent can't find it.
- **Why:** security (can't override core classes), avoids duplicate class loading, consistency.
- Native libraries (`.dll`/`.so`) are loaded via **JNI**, not the Platform ClassLoader.

## 4. Runtime Memory Areas
```
Shared (all threads):        Metaspace, Heap
Thread-private (per thread):  Java Stack, PC Register, Native Method Stack
```
| Area | Stores | Shared? | Managed by |
|---|---|---|---|
| **Metaspace** | Class metadata (fields, methods, constant pool) — **not objects** | Yes | JVM |
| **Heap** | Objects, arrays, instance variables | Yes | Garbage Collector |
| **Java Stack** | Stack Frames (local vars, params, references, operand stack, return address) | No (per thread) | JVM (auto) |
| **PC Register** | Address of next bytecode instruction | No (per thread) | JVM |
| **Native Method Stack** | Frames for native (C/C++) method calls | No (per thread) | JVM |
| **Code Cache** | JIT-compiled machine code | Shared | JVM |

**Golden rule:** References live in the **Stack**; objects live in the **Heap**. Stack ≠ objects, ever.

### Object Creation Flow — `new Student()`
```
Is class loaded? → No: ClassLoader loads into Metaspace
                 → Yes: skip
Allocate memory in Heap
Assign default values (0/false/null)
Run constructor (real values assigned)
Return reference → stored in Stack
```

## 5. Garbage Collection

### GC Roots (reachability analysis, NOT reference counting)
- Local variables on thread stacks
- Static variables
- Active threads
- JNI (native) references
- **Object reachable from a GC Root → alive. Otherwise → eligible for GC** (even if objects reference each other — solves circular reference problem that reference counting can't).

### Heap Structure
```
Young Generation (Eden + S0 + S1)   |   Old Generation
```
- New objects → **Eden**.
- Eden full → **Minor GC**: dead objects removed, survivors copied to a Survivor space (S0/S1), **age++**.
- Two survivor spaces so one is always empty to copy into (avoids fragmentation).
- Object survives many Minor GCs (commonly age ~15) → **promoted to Old Generation**.

### GC Types
| Type | Scope | Frequency | Speed |
|---|---|---|---|
| Minor GC | Young Gen only | Frequent | Fast |
| Major GC | Old Gen | Less frequent | Slow |
| Full GC | Entire heap | Rare | Slowest |

### Stop-The-World (STW)
- All application threads pause while GC runs, so the heap stays consistent.
- Modern collectors (**G1, ZGC, Shenandoah**) minimize (not eliminate) STW pauses.

### G1 GC (default since Java 9)
- Heap split into many small equal-sized **regions** (not one big Young/Old block).
- Collects regions with **most garbage first** → "Garbage First."
- Chosen over Parallel GC as default because it gives **shorter, more predictable pauses** on large heaps.

## 6. Exception Handling Internals
```
Object → Throwable → Error / Exception
                         Exception → RuntimeException (unchecked) / Checked Exceptions
```
- **Error** (`OutOfMemoryError`, `StackOverflowError`) — not meant to be caught/recovered.
- **RuntimeException** (unchecked) — compiler doesn't force catch/declare (`NullPointerException`, `ArithmeticException`...).
- **Checked Exception** — compiler forces catch or `throws` (`IOException`, `SQLException`...).

### What happens when an exception is thrown
1. Exception object allocated in **Heap**.
2. **Stack trace captured immediately** (stack unwinds and info would be lost otherwise).
3. JVM checks current method's **Exception Table** (compiler-generated: protected range → handler address → exception type).
4. Match found → jump to catch block (no unwinding).
5. No match → **pop the frame** (stack unwinding) → check caller → repeat until handled or stack is empty (→ program terminates, JVM prints trace).

### `throw` vs `throws`
- `throw` → executable statement, generates **`athrow`** bytecode, triggers handling at runtime.
- `throws` → compile-time-only metadata; compiler enforces checked-exception rules; generates no bytecode.

### `finally`
- Always runs before method exits (normal return or exception) — compiler wires the bytecode for it.
- ⚠️ `return` inside `finally` **overrides** any earlier return/exception (swallows exceptions) → avoid it.

### try-with-resources
- Compiler rewrites it into `try { ... } finally { resource.close(); }` automatically.

### Exceptions are expensive because
Object allocation + full stack capture + possible multi-frame unwinding — much costlier than a simple `if` check.

### Chained exceptions (Exception Translation)
- Lower layer exception wrapped in higher-level exception via `cause` field — preserves original cause without leaking low-level details to callers.

## 7. Classic Gotcha Snippets (say these out loud before interview)

**Static init order** — fields/blocks execute top-to-bottom in source order at Initialization; Preparation gives defaults first.
```java
static int x = getValue();   // runs first -> "getValue()" printed, x=10
static { System.out.println("Static Block"); }  // runs second
```
Referencing a static field **before its declaration** inside a static block → **compile error**.

**Java is pass-by-value (of the reference)**
```java
static void greet(Person x) { x = new Person(); x.age = 100; }
// caller's Person p is untouched — x was a *copy* of the reference
```
```java
static void greet(Person x) { x.age = 100; }
// caller's object IS changed — copy of reference still points to same heap object
```

**Reassignment leaves old object unreachable**
```java
p1 = p2;   // whichever object p1 used to point to, if nothing else references it, is now GC-eligible
```

**GC eligibility with mutual references** — objects referencing each other but unreachable from any GC Root are still collected (this is why Java doesn't use reference counting).

---
