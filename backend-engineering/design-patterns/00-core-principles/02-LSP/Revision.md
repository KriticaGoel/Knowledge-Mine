# LSP (Liskov Substitution Principle)

### Definition

👉 Objects of a subclass should be replaceable with objects of the parent class without changing the correctness of the program.

If B extends A, then B should work anywhere A is expected.

### Formula to remember:

**Child should behave like Parent**

OR

**Replace Child ↔ Parent without breaking code**

---

### Problem (LSP Violation)

❌ `LSPDBManager` defines a common contract:

```java
interface LSPDBManager {
    void processData(List<LSPProduct> products) throws Exception;
}
```

But implementations behave differently:

* SQL validates and continues
* Mongo validates and throws Exception
* File always throws Exception

Client expects same behavior but gets different results.

Result: Substitution Breaks

---

### Why is this a Violation?

#### Different Preconditions

SQL:

```java
if(product.getName().length() < 5) {
   System.out.println("Invalid");
}
```

Mongo:

```java
if(product.getName().length() < 5) {
   throw new Exception();
}
```

Mongo requires stricter conditions than SQL.

❌ LSP Violation

---

#### Different Postconditions

Client expects:

```java
processData(products);
```

Data should be persisted.

But:

* SQL persists
* Mongo persists differently
* File may fail

Guarantees are inconsistent.

❌ LSP Violation

---

#### Different Exception Behavior

SQL:

```java
Print message
```

Mongo:

```java
Throw Exception
```

File:

```java
Always throw Exception
```

Client cannot safely substitute one implementation with another.

❌ LSP Violation

---

#### Different Transaction Semantics

Client may expect:

```java
save()
commit()
rollback()
```

SQL supports transactions.

File does not.

Substituting File for SQL changes behavior.

❌ LSP Violation

---

### Solution (Apply LSP)

Instead of forcing every implementation into one interface:

```java
interface DBManager
```

Create specialized contracts.

```java
DataWriter
IdGeneratingWriter
TransactionalWriter
QueryableWriter
```

Each implementation chooses the contract it can truly satisfy.

Examples:

* SaveToSqlDBWithId → IdGeneratingWriter
* SaveToMongoDB → QueryableWriter
* SaveToFile → DataWriter

Now each class fulfills its contract correctly.

✅ LSP Satisfied

---

### Q: How do you identify LSP violation?

Ask:

> "Can I replace the parent object with the child object without changing program behavior?"

If No → LSP Violated

If Yes → LSP Followed

---

### Before vs After

#### Before ❌

```java
DBManager
      ▲
      │
 ┌────┼────┐
 │    │    │
SQL Mongo File
```

All implementations behave differently.

---

#### After ✅

```java
DataWriter
      ▲
      │
   FileWriter

IdGeneratingWriter
      ▲
      │
   SQLWriter

QueryableWriter
      ▲
      │
 MongoWriter
```

Each implementation fulfills its own contract.

---

### Benefits

✅ Predictable Behavior

✅ Safe Polymorphism

✅ Better Maintainability

✅ Fewer Runtime Surprises

✅ Stronger Contracts

---

### Real World Example

#### Bird Example (Wrong)

```java
class Bird {
    fly();
}
```

```java
class Sparrow extends Bird {
    fly();
}
```

```java
class Penguin extends Bird {
    fly() {
        throw new UnsupportedOperationException();
    }
}
```

Problem:

```java
Bird bird = new Penguin();
bird.fly();
```

Application breaks.

❌ LSP Violation

---

#### Correct

```java
interface Bird {}
```

```java
interface FlyingBird extends Bird {
    fly();
}
```

```java
class Sparrow implements FlyingBird {}
class Penguin implements Bird {}
```

Now Penguin is not forced to fly.

✅ LSP Followed

---

### Memory Trick

Think of a Mobile Charger

Suppose a socket accepts:

```text
Any USB Charger
```

If one charger suddenly requires:

```text
Special Adapter
Extra Voltage
Different Socket
```

then it is no longer a true substitute.

LSP means:

```text
If it fits the socket,
it should work correctly.
```

---

### Guidelines to Follow LSP

### 1. Signature Rule

#### Method Argument Rule

Child Method Argument >= Parent Method Argument

Same or Broader

✅ Allowed

---

#### Method Return Type Rule

Child Return Type <= Parent Return Type

Same or Narrower

✅ Allowed

---

#### Exception Rule

Child Exception <= Parent Exception

Same or Narrower

✅ Allowed

---

### 2. Property Rule

#### Class Invariant Rule

Child must preserve Parent rules.

Example:

Parent Rule:

```text
Product name must have at least 5 characters
```

Child cannot weaken or change this rule.

---

#### History Constraint Rule

Child cannot restrict operations allowed by Parent.

Example:

Parent:

```java
setValue();
updateValue();
```

Child:

```java
setValue();
// update not allowed
```

Program behavior changes.

❌ LSP Violation

---

### 3. Method Rule

#### Precondition Rule

Child Precondition <= Parent Precondition

Same or Weaker

✅ Allowed

Example:

Parent:

```text
Accept any valid Product
```

Child:

```text
Accept only Products with IDs
```

❌ Violation

---

#### Postcondition Rule

Child Postcondition >= Parent Postcondition

Same or Stronger

✅ Allowed

Example:

Parent guarantees:

```text
Data is saved
```

Child guarantees:

```text
Data is saved + Audit log created
```

✅ Valid
