# 📚 Spring Data JPA Flashcards

## 🔹 Configuration

<details>
<summary>Q: Which dependency is used for JPA?</summary>

A: spring-boot-starter-data-jpa

</details>

<details>
<summary>Q: What does spring.jpa.hibernate.ddl-auto do?</summary>

A: Controls schema creation/update

</details>

<details>
<summary>Q: Most used ddl-auto in production?</summary>

A: update

</details>

---

## 🔹 Entity

<details>
<summary>Q: What does @Entity do?</summary>

A: Maps class to table

</details>

<details>
<summary>Q: What is @Id?</summary>

A: Primary key

</details>

<details>
<summary>Q: What is @GeneratedValue?</summary>

A: Auto-generates ID

</details>

---

## 🔹 ID Strategy

<details>
<summary>Q: Best ID strategy for performance?</summary>

A: SEQUENCE

</details>

<details>
<summary>Q: Why avoid IDENTITY?</summary>

A: No batching

</details>

<details>
<summary>Q: When to use UUID?</summary>

A: Distributed systems

</details>

---

## 🔹 Repository

<details>
<summary>Q: Difference between CrudRepository and JpaRepository?</summary>

A: JpaRepository adds pagination, sorting, batch

</details>

---

## 🔹 JPA Methods

<details>
<summary>Q: save() does what?</summary>

A: Insert or update

</details>

<details>
<summary>Q: Method to fetch single record?</summary>

A: findById()

</details>

<details>
<summary>Q: Method for pagination?</summary>

A: findAll(Pageable)

</details>

---

## 🔹 Relations

<details>
<summary>Q: Which side owns relationship?</summary>

A: Side with foreign key

</details>

<details>
<summary>Q: Annotation for One-to-Many?</summary>

A: @OneToMany

</details>

<details>
<summary>Q: Annotation for Many-to-One?</summary>

A: @ManyToOne

</details>

---

## 🔹 FetchType

<details>
<summary>Q: Default fetch type for OneToMany?</summary>

A: LAZY

</details>

<details>
<summary>Q: Default fetch type for ManyToOne?</summary>

A: EAGER

</details>

<details>
<summary>Q: Which is recommended?</summary>

A: LAZY

</details>

---

## 🔹 Cascade

<details>
<summary>Q: What does CascadeType.ALL mean?</summary>

A: Apply all operations to child

</details>

<details>
<summary>Q: When to use cascade?</summary>

A: When child depends on parent

</details>

---

## 🔹 Performance

<details>
<summary>Q: What is N+1 problem?</summary>

A: Multiple queries for related data

</details>

<details>
<summary>Q: How to fix N+1?</summary>

A: JOIN FETCH

</details>

<details>
<summary>Q: Alternative to JOIN FETCH?</summary>

A: Batch fetching

</details>

---

## 🔹 Queries

<details>
<summary>Q: What are Derived Queries?</summary>

A: Queries from method name

</details>

<details>
<summary>Q: When to use @Query?</summary>

A: Custom queries

</details>

<details>
<summary>Q: When to use Native SQL?</summary>

A: Complex or DB-specific queries

</details>

<details>
<summary>Q: When to use Criteria API?</summary>

A: Dynamic queries

</details>

---

## 🔹 Best Practices

<details>
<summary>Q: Why avoid findAll()?</summary>

A: Loads entire table → performance issue

</details>

<details>
<summary>Q: Why avoid EAGER?</summary>

A: Loads unnecessary data

</details>

<details>
<summary>Q: What annotation is important for transactions?</summary>

A: @Transactional

</details>

---

## 🔥 Ultra Fast Recall

<details>
<summary>Q: 5-sec JPA summary?</summary>

A:
Entity → Table  
Repository → DB  
LAZY → performance  
SEQUENCE → scale  
JOIN FETCH → fix N+1

</details>