#  JVM Internals Summary
# Chapter 2 -- JVM Runtime Memory Areas (Detailed Notes)

# 1. Introduction

When a Java program starts, the JVM creates several runtime memory
areas. Each area has a specific responsibility.

``` text
                  JVM
+--------------------------------------------------+
|  Metaspace                                       |
|--------------------------------------------------|
|  Heap                                            |
|--------------------------------------------------|
|  Java Stack (one per thread)                     |
|--------------------------------------------------|
|  PC Register (one per thread)                    |
|--------------------------------------------------|
|  Native Method Stack (covered later)             |
+--------------------------------------------------+
```

------------------------------------------------------------------------

# 2. Metaspace

## Purpose

Stores **class metadata**, not object instances.

Contains: - Class names - Method metadata - Field metadata - Runtime
Constant Pool - Class-level information - Method bytecode (conceptual
learning view)

Example:

``` java
class Person{
    int age;
    void greet(){}
}
```

Conceptually:

``` text
METASPACE

Person Class

Fields
- age

Methods
- greet()

Bytecode
```

**Important**

-   Only one copy of class metadata is loaded.
-   Multiple objects use the same class metadata.

------------------------------------------------------------------------

# 3. Heap

## Purpose

Stores: - Objects - Arrays - Instance variables (inside objects)

Example

``` java
Person p = new Person();
```

``` text
STACK

p -----------+

              |
              v

HEAP

Person Object
age = 0
```

Objects remain until unreachable and reclaimed by Garbage Collection.

------------------------------------------------------------------------

# 4. Java Stack

Each thread has its own Java Stack.

Each method call creates one **Stack Frame**.

Example:

``` java
main();
greet();
```

``` text
TOP

greet() Frame

--------------

main() Frame
```

When `greet()` returns, its frame is removed automatically.

------------------------------------------------------------------------

# 5. Stack Frame

A stack frame contains:

-   Local Variables
-   Method Parameters
-   Reference Variables
-   Operand Stack
-   Return Information

Example:

``` java
void greet(Person p){

    int x=10;

}
```

``` text
greet() Frame

Parameters
p

Local Variables
x

Operand Stack

Return Information
```

------------------------------------------------------------------------

# 6. References vs Objects

``` java
Person p1 = new Person();
Person p2 = p1;
```

``` text
STACK

p1 -----------+

              |
p2 -----------+

              |
              v

HEAP

Person Object
```

Two references can point to one object.

Changing the object through one reference affects the same object.

------------------------------------------------------------------------

# 7. Garbage Collection Eligibility

Example

``` java
Person p1 = new Person();
Person p2 = new Person();

p1 = p2;
```

After assignment:

``` text
STACK

p1 ----------+

             |
p2 ----------+

             |
             v

HEAP

Person Object #1   (Unreachable)

Person Object #2
```

Object #1 becomes eligible for GC.

Eligible does not mean immediate deletion.

------------------------------------------------------------------------

# 8. Method Calls

``` java
main()
{
 greet();
}
```

Execution:

1.  main() frame created.
2.  greet() frame pushed.
3.  greet() returns.
4.  greet() frame popped.
5.  main() continues.

------------------------------------------------------------------------

# 9. Pass-by-Value

Java always passes values.

For objects: - The value copied is the reference.

``` java
void greet(Person x){
    x.age=100;
}
```

`x` and caller's variable point to the same object.

Reassigning `x` does not change the caller's variable.

------------------------------------------------------------------------

# 10. Heap vs Stack

Stack               Heap
  ------------------- ----------------
Per thread          Shared
Stores frames       Stores objects
Automatic cleanup   GC managed
Faster              Larger

------------------------------------------------------------------------

# 11. Common Misconceptions

❌ Stack stores objects.

✔ Stack stores references and local variables.

❌ Heap stores references only.

✔ Heap stores actual objects.

❌ Objects disappear when a method ends.

✔ Only unreachable objects become GC candidates.

------------------------------------------------------------------------

# 12. Interview Questions

1.  Difference between Heap and Stack?
2.  Why is Stack thread-private?
3.  What is a Stack Frame?
4.  What happens when a method returns?
5.  Why is Java pass-by-value?
6.  When is an object eligible for GC?

------------------------------------------------------------------------

# 13. Summary

-   Metaspace stores class metadata.
-   Heap stores objects.
-   Stack stores method execution state.
-   One stack per thread.
-   One stack frame per method call.
-   References live in stack frames.
-   Objects live in heap.
-   GC removes unreachable heap objects.

# Revision Checklist

-   [x] Heap
-   [x] Stack
-   [x] Metaspace
-   [x] Stack Frames
-   [x] References
-   [x] Objects
-   [x] GC Eligibility
-   [x] Pass-by-Value

# 03_PC_Register_Quick_Revision

---

# PC Register

## Definition

The **Program Counter (PC) Register** is a small memory area that stores the **address (index) of the next bytecode instruction** to be executed by the current thread.

> Think of it as the JVM's **bookmark**.

---

# Why Does JVM Need It?

The JVM uses the PC Register to know:

- Which instruction to execute next
- Where to continue after a method call
- Where to continue after a loop
- Which instruction belongs to the current thread

Without the PC Register, the JVM would not know where execution should resume.

---

# Characteristics

- ✅ One PC Register per Thread
- ✅ Thread Private
- ✅ Very Small Memory Area
- ✅ Managed Automatically by JVM
- ✅ Stores Bytecode Instruction Address

---

# What Does It Store?

It stores the **next bytecode instruction** to execute.

Example

```text
main()

0 : invoke greet()

1 : return
```

Initially

```
PC = 0
```

After `greet()` returns

```
PC = 1
```

---

# Method Call Example

```java
main();

↓

greet();
```

Execution

```text
PC = main(), 0

↓

Call greet()

↓

PC = greet(), 0

↓

Return

↓

PC = main(), 1
```

The JVM resumes execution from the next instruction in the caller.

---

# Loops

Example

```java
while(i < 5){
    i++;
}
```

Simplified Bytecode

```text
0 : initialize

1 : check

2 : body

3 : increment

4 : goto 1

5 : return
```

Execution

```text
0

↓

1

↓

2

↓

3

↓

4

↓

1

↓

...
```

The PC Register moves **forward** and **backward** because of jump instructions.

---

# Recursion

```java
fun(3)
```

Stack

```text
fun(0)

fun(1)

fun(2)

fun(3)

main()
```

Important

- Only **one copy** of the method exists in **Metaspace**.
- Every recursive call creates a **new Stack Frame**.
- The PC Register always points to the current instruction being executed.

---

# Relationship with Stack

## Java Stack

Stores

- Stack Frames
- Local Variables
- Parameters
- References
- Return Information

## PC Register

Stores

- Next Bytecode Instruction

Both work together during method execution.

---

# What PC Register Does NOT Store

❌ Objects

❌ Local Variables

❌ Methods

❌ Class Metadata

---

# Common Misconceptions

❌ PC Register stores Java code.

✔ It stores the address of the next bytecode instruction.

---

❌ All threads share one PC Register.

✔ Every thread has its own PC Register.

---

❌ PC Register stores local variables.

✔ Local variables are stored in the Java Stack.

---

# Interview Questions

### Basic

- What is the PC Register?
- Why is it needed?
- Is it shared or thread-private?
- What does it store?

### Intermediate

- Explain the PC Register during a method call.
- Explain the PC Register during loops.
- Explain the PC Register during recursion.

### Senior

- Why does each thread require its own PC Register?
- How does the JVM resume execution after a method returns?

---

# Memory

```text
              JVM

----------------------------

Java Stack

main()

greet()

----------------------------

PC Register

Next Instruction

----------------------------
```

---

# Quick Revision Table

| Property | Value |
|----------|-------|
| Memory Area | PC Register |
| Scope | Per Thread |
| Shared | No |
| Stores | Next Bytecode Instruction |
| Used During | Method Calls, Loops, Recursion |
| Managed By | JVM |

---

# Key Takeaways

- PC Register keeps track of the **next bytecode instruction**.
- Every thread has its own PC Register.
- It is updated automatically by the JVM.
- It works closely with the Java Stack.
- It enables correct execution of method calls, loops, recursion, and thread switching.
# Native Method
## 1. Native Method Stack (NMS)

* Stores stack frames for **native (C/C++) methods**.
* Used when the JVM needs to interact with the **Operating System**.
* Every thread has its own Native Method Stack.
* Example: `Thread.start()`, file operations, socket operations.

**Key Point:**

* Java code executes on the **Java Stack**.
* Native code executes on the **Native Method Stack**.

---

## 2. Why Native Methods?

Java is platform-independent, but the **JVM is platform-specific**.

The JVM is mostly written in **C/C++** and uses OS APIs to perform operations that only the operating system can do, such as:

* Creating threads
* Opening files
* Network communication
* Memory mapping

**Flow:**

Java → JVM (C++) → OS API → Operating System

---

## 3. Execution Engine

The Execution Engine converts **bytecode** into **machine code** that the CPU can execute.

Responsibilities:

* Execute bytecode
* Convert bytecode into machine code
* Optimize execution

---

## 4. Interpreter

* Executes bytecode **instruction by instruction**.
* Starts execution immediately.
* Best for methods that are executed only a few times.

**Pros:** Fast startup.

**Cons:** Re-translates the same bytecode repeatedly.

---

## 5. JIT (Just-In-Time) Compiler

When a method becomes **hot** (executed many times), the JVM compiles it into optimized machine code.

**Pros:**

* Faster execution
* Better runtime performance

The JVM uses:

* Interpreter for cold methods
* JIT Compiler for hot methods

---

## 6. Code Cache

Stores **machine code generated by the JIT Compiler**.

* Managed by the JVM
* Not managed by the Garbage Collector
* Starts empty when the JVM starts
* Destroyed when the JVM exits

It is **not**:

* Heap
* Java Stack
* Metaspace

---

## 7. Object Creation Flow

For:

```java
Student s = new Student();
```

Sequence:

1. Check whether the class is loaded.
2. Load the class into Metaspace (if needed).
3. Allocate memory in the Heap.
4. Perform default initialization (`0`, `null`, `false`, etc.).
5. Execute instance field initializers.
6. Execute instance initialization blocks.
7. Execute the constructor body.
8. Return the object reference.
9. Store the reference in the local variable on the Java Stack.

**Interview Point:**
The **constructor does not create the object**. The JVM creates the object; the constructor initializes it.

---

## 8. Complete JVM Flow

```text
Java Source
      ↓
javac
      ↓
.class Bytecode
      ↓
Class Loader
      ↓
Metaspace
      ↓
Execution Engine
   ├── Interpreter
   └── JIT Compiler
           ↓
      Code Cache
      ↓
Java Stack
      ↓
Heap (Objects)
      ↓
Native Method Stack (if OS interaction is required)
      ↓
Operating System
```

---

# Interview Takeaways

* Native Method Stack is used for native (C/C++) execution.
* JVM is written mainly in C/C++ and uses OS APIs for OS-managed resources.
* Interpreter executes bytecode immediately.
* JIT compiles only hot methods.
* Compiled machine code is stored in the Code Cache.
* Code Cache is managed by the JVM, not the Garbage Collector.
* Heap allocation happens **before** the constructor executes.
* Default initialization occurs before field initializers and constructor execution.
* The constructor initializes an already-created object.
