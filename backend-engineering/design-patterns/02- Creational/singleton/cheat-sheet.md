# Cheat-Sheet.md — Singleton Design Pattern

# Singleton Design Pattern

Allow object creation only once and provide a global point of access to that object.

## Think

```text
One Class → One Object → Shared Everywhere
```

Goal:

```text
Reuse one shared instance across application
```

---

# Key Points

1. Use Singleton when objects need to be shared.
2. Object creation may be expensive.
3. Reuse existing object instead of creating new ones.
4. Spring Beans are singleton by default.
5. Singleton is commonly used for stateless/shared resources.

⚠ Note:
Singletons are not always immutable.
Singleton can maintain state; mutable state can create issues.

---

# Recognition Smell

Ask:

* Should only one object exist?
* Is object expensive to create?
* Is state shared globally?
* Should everyone use same object?

If YES → Think Singleton.

---

# Core Idea

### Step 1: Private constructor

Prevent external object creation.

```java
private Singleton(){
    System.out.println("Singleton instance created");
}
```

### Step 2: Hold one instance

```java
static Singleton instance;
```

### Step 3: Global access point

```java
public static Singleton getInstance(){

   if(instance==null){
      instance=new Singleton();
   }

   return instance;
}
```

---

# Ways to Create Singleton

* Eager Initialization
* Lazy Initialization
* Thread-safe Singleton
* Double Checked Locking
* Bill Pugh Singleton
* Enum Singleton

---

# Real-world Examples

* Logger
* Configuration Manager
* Database Connection Pool
* Thread Pool
* Cache Manager
* Application Settings

---

# Basic Singleton (Lazy Initialization)

Object created only when needed.

```java
class Singleton {

   static Singleton instance;

   private Singleton(){}

   public static Singleton getInstance(){

      if(instance==null){
          instance=new Singleton();
      }

      return instance;
   }
}
```

### Problem

❌ Not thread safe

Race condition:

```text
Thread1 → instance==null
Thread2 → instance==null

Both create object
```

---

# Eager Initialization

Create object during class loading.

```java
class EagerSingleton {

   private static final EagerSingleton instance=
          new EagerSingleton();

   private EagerSingleton(){}

   public static EagerSingleton getInstance(){
       return instance;
   }
}
```

Advantages:

✔ Thread safe
✔ Simple

Disadvantages:

❌ Object created even if unused
❌ No runtime parameter support

---

# Synchronized Singleton

```java
public static synchronized Singleton getInstance(){

   if(instance==null)
      instance=new Singleton();

   return instance;
}
```

Advantages:

✔ Thread safe

Disadvantages:

❌ Every call acquires lock
❌ Performance overhead

---

# Double Checked Locking

```java
class Singleton {

 private static volatile Singleton instance;

 public static Singleton getInstance(){

     if(instance==null){

         synchronized(Singleton.class){

             if(instance==null){
                  instance=new Singleton();
             }
         }
     }

     return instance;
 }
}
```

Advantages:

✔ Thread safe
✔ Better performance

Why volatile?

Without volatile:

```text
Allocate memory
Assign reference
Initialize object
```

Instructions may reorder.

Another thread can receive partially initialized object.

---

# Bill Pugh Singleton Design (Best Solution)

Uses static inner helper class.

```java
class Singleton {

    private Singleton(){}

    private static class Helper{

       private static final Singleton INSTANCE=
              new Singleton();
    }

    public static Singleton getInstance(){
        return Helper.INSTANCE;
    }
}
```

Advantages:

✔ Lazy loaded
✔ Thread safe
✔ No synchronization overhead
✔ Cleaner

---

# Internal Working

### Step 1

Outer class loads.

```text
Singleton loaded
Helper not loaded
```

Object not created.

Lazy loading achieved.

---

### Step 2

First call:

```java
Singleton.getInstance()
```

Triggers:

```java
return Helper.INSTANCE
```

Now JVM loads Helper.

```java
INSTANCE=new Singleton()
```

Object created now.

---

### Step 3

JVM guarantees:

✔ Class initialization happens once
✔ Class loading internally synchronized
✔ Only one thread initializes class

Think:

```text
Thread1 → load Helper
Thread2 → load Helper
Thread3 → load Helper
```

JVM:

```text
Lock Helper
Thread1 initialize
Unlock
```

Other threads:

```text
Thread2 → existing object
Thread3 → existing object
```

No race condition.

---

# Why Better Than Synchronized?

Synchronized:

```text
Acquire lock
Release lock
Acquire lock
Release lock
```

Bill Pugh:

```text
Lock only once during class initialization
```

After that:

```java
return Helper.INSTANCE;
```

No locking cost.

---

# Quick Comparison

| Type         | Lazy | Thread Safe | Performance |
| ------------ | ---: | ----------: | ----------: |
| Basic        |  Yes |          No |        Fast |
| Synchronized |  Yes |         Yes |        Slow |
| Eager        |   No |         Yes |        Fast |
| DCL          |  Yes |         Yes |        Good |
| Bill Pugh    |  Yes |         Yes |   Excellent |

---

# Spring Singleton vs Java Singleton

| Java Singleton       | Spring Singleton         |
| -------------------- | ------------------------ |
| Class manages object | Container manages object |
| getInstance()        | BeanFactory              |
| One per JVM          | One per Spring container |

Spring internally:

```java
singletonObjects.put(beanName,obj)
```

---

# Interview One-Liner

> Singleton ensures a class has only one instance and provides a global access point. Bill Pugh Singleton is preferred because it gives lazy loading and thread safety without synchronization overhead.
