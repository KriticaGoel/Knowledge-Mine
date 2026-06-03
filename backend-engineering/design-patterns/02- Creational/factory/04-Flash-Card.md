# 🧠 Factory Design Pattern — Flashcards

---

## ❓ What is Factory Design Pattern?

<details>
<summary>Answer</summary>

A creational design pattern that separates object creation from business logic.

Client does not use `new` directly.

</details>

---

## ❓ Why do we need Factory Pattern?

<details>
<summary>Answer</summary>

To avoid:

* Giant if-else
* Tight coupling
* Duplicate object creation logic

To achieve:

* Loose coupling
* Centralized creation

</details>

---

## ❓ What is Simple Factory?

<details>
<summary>Answer</summary>

A single class responsible for creating objects based on input type.

Example:

```java
ParserFactory.getParser(type)
```

</details>

---

## ❓ Limitation of Simple Factory?

<details>
<summary>Answer</summary>

Violates Open/Closed Principle because factory must change when new types are added.

</details>

---

## ❓ What is Factory Method Pattern?

<details>
<summary>Answer</summary>

A pattern where subclasses decide which object to create.

```
createParser() implemented by subclasses
```

</details>

---

## ❓ Advantage of Factory Method?

<details>
<summary>Answer</summary>

* Follows Open/Closed Principle
* Removes if-else logic
* Easy to extend

</details>

---

## ❓ What is Abstract Factory Pattern?

<details>
<summary>Answer</summary>

A factory that creates families of related objects.

Example:

* Android UI components
* iOS UI components

</details>

---

## ❓ Difference: Factory Method vs Abstract Factory?

<details>
<summary>Answer</summary>

* Factory Method → creates one product
* Abstract Factory → creates family of products

</details>

---

## ❓ When should you use Factory Pattern?

<details>
<summary>Answer</summary>

* Multiple implementations
* Runtime object selection
* Complex object creation logic

</details>

---

## ❓ When NOT to use Factory?

<details>
<summary>Answer</summary>

* Simple object creation (`new User()`)
* Only one implementation
* No variation in creation logic

</details>

---

## ❓ Spring vs Factory Pattern?

<details>
<summary>Answer</summary>

* Spring → creates objects (DI container)
* Factory → selects correct object at runtime

</details>

---

## ❓ Real-world use cases?

<details>
<summary>Answer</summary>

* Payment systems
* File parsers
* Notification services
* Report generators

</details>
