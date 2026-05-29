# Singleton Design Pattern

> Allow object creation only once and provide a global point of access.

---

# 🧠 Think

```text
One Class
     ↓
One Object
     ↓
Shared Everywhere
```

---

# ⚡ 30 Second Revision

<details>
<summary>Open Quick Revision</summary>

### Rules:

✔ Private constructor

✔ Static instance

✔ getInstance()

### Use when:

✔ Expensive object creation

✔ Shared globally

✔ Need one object only

### Examples:

- Logger
- Configuration Manager
- Cache Manager
- Thread Pool
- Database Connection Pool

Spring Bean:

```java
@Service
```

Default scope:

```java
Singleton
```

Best implementation:

⭐ Bill Pugh Singleton

</details>

---

# 🎯 Why Singleton?

<details>
<summary>Open</summary>

Without Singleton:

```java
DBConnection db1=new DBConnection();

DBConnection db2=new DBConnection();

DBConnection db3=new DBConnection();
```

Problems:

❌ Multiple objects

❌ Memory waste

❌ Resource overhead

❌ Expensive creation

Singleton:

```java
DBConnection db=
DBConnection.getInstance();
```

Only one object.

</details>

---

# 🧠 Visual Memory

Open:

👉 [memory-map.md](memory-map.md)

Contains:

- Visual tree
- Learning flow
- Interview memory map

---

# 📂 Learning Path

Follow in sequence:

### Step 1

👉 Bad Design

```text
src/bad-design
```

Learn:

Why normal object creation fails.

---

### Step 2

👉 Improved Versions

```text
src/improved
```

Contains:

- Lazy
- Eager
- Synchronized
- Double Checked Locking

---

### Step 3

👉 Final Production Solution

```text
src/final
```

Contains:

⭐ Bill Pugh

⭐ Enum Singleton

---

# 🧾 Cheat Sheet

👉 [cheat-sheet.md](cheat-sheet.md)

2-minute revision.

Contains:

✔ Rules

✔ Variations

✔ Advantages

✔ Disadvantages

---

# 🎴 Flash Cards

👉 [flashcards.md](flashcards.md)

Active recall:

Question → Answer

Useful before interview.

---

Contains:

❌ Thread safety issues

❌ Reflection problem

❌ Serialization issue

❌ Stateful Singleton mistakes

---

# 🎤 Interview Questions

👉 [interview-questions.md](interview-questions.md)

Contains:

- Why volatile?
- Difference between Eager/Lazy?
- Bill Pugh internals?
- Reflection issue?
- Serialization issue?

---

# 🚀 Real World Usage


Examples:

Logger

Cache

Connection Pool

Spring Beans

ExecutorService

---

# 🧐 Decision Tradeoffs

| Approach  | Thread Safe | Lazy | Use Case                     |
| --------- | ----------- | ---- | ---------------------------- |
| Eager     | ✔           | ❌    | Simple always-needed objects |
| Lazy      | ❌           | ✔    | Basic learning               |
| DCL       | ✔           | ✔    | Performance-critical         |
| Bill Pugh | ✔           | ✔    | ✅ Recommended                |
| Enum      | ✔           | ✔    | Serialization-safe best      |


# 🏆 Final Mental Picture

```text
Basic Lazy
      ↓
Not Thread Safe
      ↓
Synchronized
      ↓
Slow
      ↓
Double Locking
      ↓
Complex
      ↓
Bill Pugh ⭐
```

Remember:

```text
JVM class loading gives thread safety
```

---

# Folder Structure

```text
singleton/
│
├── README.md
├── memory-map.md
├── cheat-sheet.md
├── flashcards.md
├── interview-questions.md
├── tradeoffs.md
├── theory.md
│
├── src/
│      ├── bad-design/
│      ├── improved/
│      └── final/
│
├── diagrams/
│
└── examples/

```

---

# One Line Story

```text
Create once → Share everywhere → Save memory/resources
```