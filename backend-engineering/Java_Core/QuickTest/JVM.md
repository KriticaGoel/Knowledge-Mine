10. Why did JVM designers separate memory into different areas?

11. What problems would occur if Stack and Heap were merged?

12. Why does each thread require its own PC Register?


javac compiles .java → .class (bytecode)
↓
ClassLoader (Bootstrap → Platform → Application, parent delegation)
↓
Loading → Linking (Verify/Prepare/Resolve) → Initialization
↓
Metaspace (class metadata) ready
↓
Execution Engine: Interpreter + JIT (hot spots → Code Cache)
↓
Java Stack (per-thread frames) drives execution
↓
"new" → Heap allocation → default values → constructor → reference in Stack
↓
Garbage Collector (GC Roots → reachability → Minor/Major/Full GC, STW)
↓
Exceptions: Heap object + stack trace + Exception Table lookup + unwinding