# Singleton Solution

## Core Rules

1. Private Constructor
2. Static Instance
3. Public Getter Method

---

# Step 1 → Private Constructor

```java
private Logger() {
}
```
Why?

To stop outside object creation.

Step 2 → Static Object
private static Logger instance;
Why?

Only one shared object needed.

Step 3 → Global Access Method
public static Logger getInstance() {
if(instance == null) {
instance = new Logger();
}
return instance;
}


Flow Diagram
User
↓
getInstance()
↓
Object Exists?
├── Yes → Return Existing Object
└── No  → Create Object

<details> <summary>Important Concept</summary>

Static belongs to class.

Only one static instance exists per JVM class loader.

</details> ```