# Common Mistakes

| Mistake | Problem |
|---|---|
| Public Constructor | Multiple objects possible |
| No Thread Safety | Race conditions |
| Excessive Singleton Usage | Global state problem |
| Mutable Shared State | Concurrency bugs |

---

# Dangerous Example

```java
if(instance == null) {
    instance = new Logger();
}
```

Two threads can enter together.

Result:

Two objects created
Reflection Problem

Reflection can break Singleton.

Serialization Problem

Deserialization can create new objects.

<details> <summary>How to Fix Reflection & Serialization?</summary>

Use:

enum Singleton
</details> ```