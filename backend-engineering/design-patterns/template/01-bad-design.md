# Bad Design

## Problem Statement

Multiple logger objects are being created.

---

# Bad Code

```java
class Logger {
}

```

<details> <summary>Why Beginner Developers Write This?</summary>
Easy to create objects
No restriction on instantiation
Lack of centralized control
</details> ```