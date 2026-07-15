# 📚 DB Access Master Flashcards

## 🔹 Basics

<details>
<summary>Q: What is JDBC?</summary>

A: Low-level DB API (manual control)

</details>

<details>
<summary>Q: What is JdbcTemplate?</summary>

A: Spring wrapper over JDBC

</details>

<details>
<summary>Q: What is NamedParameterJdbcTemplate?</summary>

A: JdbcTemplate with named parameters

</details>

<details>
<summary>Q: What is Spring Data JPA?</summary>

A: ORM abstraction over DB using entities

</details>

---

## 🔹 Syntax

<details>
<summary>Q: Param syntax in JDBC?</summary>

A: ?

</details>

<details>
<summary>Q: Param syntax in JdbcTemplate?</summary>

A: ? with Object[]

</details>

<details>
<summary>Q: Param syntax in NamedParameter?</summary>

A: :name

</details>

<details>
<summary>Q: Query style in JPA?</summary>

A: Method name / @Query

</details>

---

## 🔹 Control

<details>
<summary>Q: Which has highest control?</summary>

A: JDBC

</details>

<details>
<summary>Q: Which has highest abstraction?</summary>

A: JPA

</details>

---

## 🔹 Performance

<details>
<summary>Q: Which tools have best raw performance?</summary>

A: JDBC, JdbcTemplate, NamedParameter

</details>

<details>
<summary>Q: Why JPA can be slow?</summary>

A: ORM overhead + N+1 problem

</details>

---

## 🔹 Mapping

<details>
<summary>Q: Mapping in JDBC?</summary>

A: Manual ResultSet

</details>

<details>
<summary>Q: Mapping in JdbcTemplate?</summary>

A: RowMapper

</details>

<details>
<summary>Q: Mapping in JPA?</summary>

A: Automatic ORM

</details>

---

## 🔹 CRUD

<details>
<summary>Q: Method for insert/update in JPA?</summary>

A: save()

</details>

<details>
<summary>Q: Method for single fetch in JdbcTemplate?</summary>

A: queryForObject()

</details>

<details>
<summary>Q: Method for list fetch?</summary>

A: query() / findAll()

</details>

---

## 🔹 Usage Decision

<details>
<summary>Q: When to use JDBC?</summary>

A: Rarely, for low-level control

</details>

<details>
<summary>Q: When to use JdbcTemplate?</summary>

A: Fast SQL operations

</details>

<details>
<summary>Q: When to use NamedParameter?</summary>

A: Complex readable SQL

</details>

<details>
<summary>Q: When to use JPA?</summary>

A: CRUD-based applications

</details>

---

## 🔹 Real World

<details>
<summary>Q: Best production approach?</summary>

A:
JPA → CRUD  
JdbcTemplate / Named → complex queries

</details>

---

## 🔥 Ultra Recall

<details>
<summary>Q: 5-sec comparison?</summary>

A:
JDBC → control  
JdbcTemplate → speed  
Named → readability  
JPA → abstraction

</details>