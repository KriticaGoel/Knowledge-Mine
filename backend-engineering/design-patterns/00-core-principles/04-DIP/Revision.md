# DIP (Dependency Inversion Principle)

#### Definition

👉 High-Level Modules should not depend on Low-Level Modules.

👉 Both should depend on Abstractions.

Business logic should depend on interfaces, not concrete implementations.

#### Formula to remember:

**High-Level → Abstraction ← Low-Level**

NOT

**High-Level → Low-Level**

---

#### Problem (DIP Violation)

❌ `Application` (High-Level Module) directly depends on:

* SaveToSQL
* SaveToMongoDB

```java
class Application {

    SaveToSQL sql = new SaveToSQL();
    SaveToMangoDB mongo = new SaveToMangoDB();

}
```

If a new database is introduced:

```java
SaveToPostgres
SaveToRedis
SaveToOracle
```

We must modify Application.

Result: Tight Coupling

---

#### Why is this a Violation?

Application knows too much about storage implementations.

```java
sql.save();
mongo.save();
```

Business logic is directly connected to database details.

#### Problems:

* Hard to extend
* Hard to test
* Hard to replace implementations
* Violates OCP as well

❌ DIP Violation

---

#### Solution (Apply DIP)

Create an abstraction:

```java
interface Persistence {
    void save();
}
```

Both High-Level and Low-Level modules depend on it.

```java
class DIPApplication {

    private Persistence persistence;

}
```

#### Implementations:

* DIPSaveToSQL
* DIPSaveToMongo
* DIPSaveToRedis

Application never changes.

✅ DIP Satisfied

---

### Q: How do you identify DIP violation?

Ask: "Is my business logic directly dependent on concrete implementations?"

If Yes → DIP Violated

If it depends on interfaces/abstractions → DIP Followed

---

### Before vs After

#### Before ❌

```java
Application
     │
     ▼
SaveToSQL

Application
     │
     ▼
SaveToMongo
```

Application directly depends on databases.

---

#### After ✅

```java
           Persistence
           ▲        ▲
           │        │
      SQL Save   Mongo Save

               ▲
               │
          Application
```

Everyone depends on abstraction.

---

### Benefits

✅ Loose Coupling

✅ Easy Testing

✅ Easy Extension

✅ Better Maintainability

✅ Supports Dependency Injection

✅ Supports OCP

---

### Real World Example

#### Notification System (Wrong)

```java
class NotificationService {

    EmailSender sender = new EmailSender();

    void send() {
        sender.sendEmail();
    }
}
```

Need SMS?

```java
SmsSender
```

Need WhatsApp?

```java
WhatsAppSender
```

Must modify NotificationService.

❌ DIP Violation

---

#### Correct

```java
interface MessageSender {
    void send();
}
```

Implementations:

* EmailSender
* SmsSender
* WhatsAppSender

```java
class NotificationService {

    private MessageSender sender;

}
```

Now NotificationService works with any sender.

✅ DIP Followed

---

### Memory Trick

Think of a Power Socket

Appliances:

* TV
* Laptop
* Mobile Charger

None of them connect directly to the power plant.

They connect through a standard socket.

```text
Power Plant = Low-Level Module

Appliance = High-Level Module

Socket = Abstraction
```

Everyone depends on the socket.

That is DIP.

---

### DIP vs OCP

Many developers confuse them.

#### OCP

Focuses on:

```text
How to add new functionality
without modifying existing code.
```

#### DIP

Focuses on:

```text
Reducing dependency
between modules.
```

DIP often helps achieve OCP.

---





