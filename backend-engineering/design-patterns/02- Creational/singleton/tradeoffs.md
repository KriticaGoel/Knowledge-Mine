## Singleton-vs-Prototype

### Core Idea difference
| Singleton            | Prototype               |
| -------------------- | ----------------------- |
| One instance per JVM | New instance every time |
| Shared state         | Independent state       |
| Global access point  | Cloned / fresh objects  |
|Logger logger = Logger.getInstance();|User user1 = new User();<br/>User user2 = new User();|
| Same object reused everywhere.| Each call = new object.|

### Key Tradeoff
| Aspect        | Singleton       | Prototype    |
| ------------- | --------------- | ------------ |
| Memory        | Efficient       | Higher usage |
| State sharing | Yes             | No           |
| Thread safety | Hard if mutable | Easier       |
| Testing       | Hard            | Easy         |

### When to use
| Singleton      | Prototype       |
|----------------|-----------------|
| Logger         | User objects    |
| Cache          | DTOs            |
| Config manager | Request objects |

## Singleton-vs-Static

### Core Idea difference
| Singleton           | Static          |
| ------------------- | --------------- |
| Object-based        | Class-based     |
| Supports interfaces | No polymorphism |
| Can be lazy loaded  | Always loaded   |
| Can be mocked       | Hard to test    |
|Logger logger = Logger.getInstance();<br/>logger.log("Hello");|Logger.log("Hello");|
|object-oriented style|procedural style|

### Key Tradeoff
| Aspect      | Singleton  | Static        |
| ----------- | ---------- | ------------- |
| OOP support | ✔          | ❌             |
| Inheritance | ✔          | ❌             |
| Testability | Better     | Worst         |
| Flexibility | High       | Low           |
| Memory      | Controlled | Always loaded |


### When to use
| Singleton      | Static       |
|----------------|-----------------|
|When you need lifecycle control|Utility methods (Math, Utils)|
|When you may swap implementations|Pure functions (no state)|


## Singleton-vs-DependencyInjection

### Core Idea difference

| Singleton                           | Dependency Injection        |
| ----------------------------------- |-----------------------------|
| Manually controlled global instance | Framework manages objects   |
| Hard-coded access                   | Injected dependencies       |
| Tight coupling                      | Loose coupling              |
| Manual lifecycle                    | Container-managed lifecycle |
|Logger logger = Logger.getInstance();| see below example ⬇️        |

```java
@Service
public class OrderService {

    private final Logger logger;

    public OrderService(Logger logger) {
        this.logger = logger;
    }
}
```
### Key Tradeoff
| Aspect            | Singleton | DI                |
| ----------------- | --------- | ----------------- |
| Coupling          | Tight     | Loose             |
| Testability       | Hard      | Easy              |
| Flexibility       | Low       | High              |
| Lifecycle control | Manual    | Framework-managed |
| Scalability       | Limited   | High              |


## Classical Singleton vs Spring Singleton

### ⚖️ 1. Classical Singleton (Design Pattern)

This is what you wrote in plain Java:
```java
public class Logger {

    private static final Logger INSTANCE = new Logger();

    private Logger() {}

    public static Logger getInstance() {
        return INSTANCE;
    }
}
```

#### 🔹 Key characteristics:
* You control object creation
* JVM enforces single instance
* You call getInstance()
* Only ONE object per JVM

### 🏗️ 2. Spring Singleton (Container-managed)

In Spring:
```java
@Service
public class OrderService {
}
```
You never write getInstance().

#### 👉 What Spring does internally:
* Spring creates the object
* Stores it in ApplicationContext (IoC container)
* Reuses same instance everywhere

So:
```
OrderService obj1 = context.getBean(OrderService.class);
OrderService obj2 = context.getBean(OrderService.class);

👉 obj1 == obj2 → TRUE

```

🧠 Key differences:

| Feature               | Classical Singleton | Spring Singleton       |
| --------------------- | ------------------- | ---------------------- |
| Who controls creation | You (developer)     | Spring container       |
| Pattern type          | Design pattern      | IoC container behavior |
| Access method         | getInstance()       | Dependency injection   |
| Lifecycle control     | Manual              | Framework-managed      |
| Testability           | Hard                | Easy                   |

🚨 3. Why Spring calls it "Singleton"
```
Because:

Spring creates exactly ONE bean instance per container (by default scope = singleton)

But this is NOT GoF Singleton pattern.

```