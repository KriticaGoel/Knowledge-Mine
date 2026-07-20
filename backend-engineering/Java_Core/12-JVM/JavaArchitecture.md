
```text
                                   Java Program
                                  (Hello.class)
                                        │
                                        ▼
                        ┌────────────────────────────┐
                        │       Class Loader         │
                        │ ────────────────────────── │
                        │ • Loading                  │
                        │ • Linking                  │
                        │ • Initialization           │
                        │ • Bytecode Verifier        │
                        └─────────────┬──────────────┘
                                      │
                                      ▼
                        ┌────────────────────────────┐
                        │     Execution Engine       │
                        │ ────────────────────────── │
                        │ • Interpreter              │
                        │ • JIT Compiler             │
                        └─────────────┬──────────────┘
                                      │
                 ┌────────────────────┴────────────────────┐
                 │                                         │
                 ▼                                         ▼
      ┌─────────────────────┐                 ┌─────────────────────┐
      │  Runtime Data Area  │                 │ Native Interface    │
      │    (JVM Memory)     │                 │       (JNI)         │
      └──────────┬──────────┘                 └──────────┬──────────┘
                 │                                       │
                 ▼                                       ▼
     ┌───────────────────────────────┐      ┌────────────────────────┐
     │ Shared Across All Threads     │      │ Native Libraries       │
     ├───────────────────────────────┤      └────────────────────────┘
     │ • Heap                        │
     │ • Method Area                 │
     └───────────────────────────────┘
                 │
                 ▼
     ┌───────────────────────────────┐
     │ Per Thread Memory             │
     ├───────────────────────────────┤
     │ • JVM Stack                   │
     │ • Program Counter (PC)        │
     │ • Native Method Stack         │
     └───────────────────────────────┘

             JVM Internal Services
      ┌─────────────────────────────────┐
      │ • Garbage Collector             │
      │ • Security Manager              │
      └─────────────────────────────────┘
```