1. String Literal
```
   String s = "Java";
```   
*   JVM checks the String Pool.
*   If "Java" exists → reuse it.
*   Otherwise → create it in the String Pool.

✅ Memory efficient.

2. new String()
 ```
String s = new String("Java");
```
JVM does two things:

* Ensures "Java" exists in the String Pool.
* Creates another object in the Heap.

So you have:
```
Heap
├── Normal String Object  ← s points here

String Pool (inside Heap)
└── "Java"
```
3. intern()
```   
   String s = new String("Java");
   s = s.intern();
```   
*  intern() returns the String Pool reference.
*   s is updated to point to the pooled object.
*   The old heap object becomes eligible for GC if nothing else references it.
4. Comparison

   ==          // compares references
   
   equals()    // compares contents

5. Memory Location
```
Java 7+

JVM
├── Heap
│    ├── Normal Objects
│    └── String Pool
│
├── Stack
├── Metaspace
└── PC Register
```