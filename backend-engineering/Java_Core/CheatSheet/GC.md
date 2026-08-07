✅ Garbage Collection removes unreachable objects.

✅ null doesn't immediately delete an object.

✅ Objects become eligible for GC.

✅ GC Roots are the starting points for reachability.

✅ The JVM uses Reachability Analysis.

✅ Java avoids Reference Counting because it fails with circular references.

✅ Heap generations

✅ Eden Space

✅ Survivor Spaces (S0 & S1)

✅ Object aging

✅ Promotion to Old Generation

✅ Minor GC, Major GC, and Full GC

✅ Promotion failure and why a Full GC may be triggered

✅ Stop-The-World

✅ G1 Garbage Collector and why it's the default

# Garbage Collection (GC) - Quick Revision Notes

## 1. Why Garbage Collection?

### C/C++
- Memory allocated manually.
- Programmer must free memory (`delete`).
- Forgetting causes memory leaks.

### Java
- JVM automatically reclaims memory.
- Developer focuses on business logic.

---

# 2. What is Garbage Collection?

**Definition**

Garbage Collection is the process by which the JVM automatically identifies **unreachable objects** and reclaims their heap memory.

### Interview Point

❌ GC removes unused objects.

✅ GC removes unreachable objects.

---

# 3. Eligible vs Immediately Collected

```java
Student s = new Student();
s = null;
```

The object becomes:

✅ Eligible for GC

NOT

❌ Immediately deleted

GC runs whenever JVM decides.

---

# 4. Reachability Analysis

Modern JVM does NOT use reference counting.

It starts from **GC Roots**.

If an object can be reached from any GC Root:

✅ Keep it

Otherwise:

✅ Eligible for GC

---

# 5. GC Roots

Most important GC Roots:

- Local variables (Java Stack)
- Static variables
- Active Threads
- JNI References

Example

Stack (GC Root)

s -----------> Student Object

Student object is reachable.

---

# 6. Why Java Doesn't Use Reference Counting

Problem:

```java
A ----> B
^       |
|       |
+-------+
```

Both reference each other.

Reference count is never zero.

Memory leak.

Instead Java uses Reachability Analysis.

---

# 7. Heap Structure

```
Java Heap

Young Generation
 ├── Eden
 ├── Survivor S0
 └── Survivor S1

Old Generation
```

---

# 8. Weak Generational Hypothesis

Most Java objects:

- Are created
- Used quickly
- Die quickly

Hence:

New objects start in Eden.

---

# 9. Eden Space

Every new object is allocated in Eden.

When Eden becomes full:

Minor GC starts.

---

# 10. Minor GC

During Minor GC

- Scan Young Generation
- Remove unreachable objects
- Copy surviving objects to Survivor Space
- Increase object age

Fast

Frequent

---

# 11. Survivor Spaces

Two Survivor spaces

S0

S1

Objects move:

Eden

↓

S0

↓

S1

↓

S0

↓

S1

Each survival:

Age++

---

# 12. Object Aging

Created in Eden

Age = 0

After first Minor GC

Age = 1

Age increases after every Minor GC.

---

# 13. Promotion

When object survives multiple Minor GCs
(default around 15, JVM may adjust dynamically)

↓

Move to Old Generation

Reason:

Long-lived objects should not be copied repeatedly.

---

# 14. Major GC

Works mainly on:

Old Generation

Characteristics

- Slow
- Less frequent
- Expensive

---

# 15. Full GC

Collects:

- Young Generation
- Old Generation
- Entire Heap

Slowest GC.

---

# 16. Promotion Failure

Scenario

- Eden full
- Object survives
- Old Generation has no space

↓

JVM may trigger Full GC.

If enough space is reclaimed

↓

Promotion succeeds.

Otherwise

↓

OutOfMemoryError

---

# 17. Stop-The-World (STW)

During GC

Application threads pause.

Reason:

Prevent object references from changing while GC analyzes the heap.

Without STW

Heap becomes inconsistent.

Modern collectors still have short STW pauses.

---

# 18. G1 Garbage Collector

Default from Java 9.

Instead of one large Young/Old heap

Heap is divided into many Regions.

```
R1 R2 R3 R4 R5
R6 R7 R8 R9 R10
```

---

# 19. Why "Garbage First"?

G1 collects regions with the highest percentage of garbage first.

Example

R1 → 5% garbage

R2 → 90% garbage

R3 → 10% garbage

Collect R2 first.

Benefits

- Shorter pause time
- Better performance
- Predictable GC

---

# 20. Interview Questions

### Why doesn't Java use Reference Counting?

Because it cannot detect circular references.
Java uses Reachability Analysis.

---

### Why two Survivor Spaces?

To copy live objects between S0 and S1 efficiently, avoid fragmentation, and track object aging.

---

### Difference between Minor, Major and Full GC?

Minor GC
- Young Generation
- Fast
- Frequent

Major GC
- Old Generation
- Slow

Full GC
- Entire Heap
- Slowest

---

### Why Stop-The-World?

To prevent application threads from modifying object references while the GC is analyzing memory.

---

### Why is G1 faster?

Because it divides the heap into regions and collects the regions containing the most garbage first instead of scanning the entire heap.

---

# Interview Keywords (Must Remember)

- Unreachable Objects
- Eligible for GC
- GC Roots
- Reachability Analysis
- Weak Generational Hypothesis
- Eden
- Survivor S0
- Survivor S1
- Object Aging
- Promotion
- Old Generation
- Minor GC
- Major GC
- Full GC
- Stop-The-World (STW)
- G1 GC
- Regions
- Garbage First