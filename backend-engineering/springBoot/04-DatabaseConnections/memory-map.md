# 🧠 DB Access Master Sheet (JDBC → Spring Data JPA)

## 🟣 Evolution

JDBC  
→ JdbcTemplate  
→ NamedParameterJdbcTemplate  
→ Spring Data JPA

👉 From control → abstraction

---

## 🔵 Core Idea

JDBC → manual control  
JdbcTemplate → less boilerplate  
NamedParameter → readable SQL  
JPA → no SQL (ORM)

---

## 🟡 Flow Comparison

JDBC  
SQL → PreparedStatement → ResultSet → manual mapping

JdbcTemplate  
SQL → JdbcTemplate → RowMapper → Object

NamedParameterJdbcTemplate  
SQL(:name) + Params → NamedTemplate → Object

Spring Data JPA  
Repository → Entity → DB

---

## 🟢 Syntax Trigger

JDBC → ?  
JdbcTemplate → ? + Object[]  
Named → :name  
JPA → method name / @Query

---

## 🔴 Control vs Abstraction

| Tool | Control | Abstraction |
|------|--------|------------|
| JDBC | 🔥🔥🔥🔥 | ❌ |
| JdbcTemplate | 🔥🔥🔥 | ⚖️ |
| NamedParameter | 🔥🔥🔥 | ⚖️ |
| JPA | 🔥 | ✅✅✅ |

---

## 🟠 Performance

JDBC → ⭐⭐⭐⭐  
JdbcTemplate → ⭐⭐⭐⭐  
NamedParameter → ⭐⭐⭐⭐  
JPA → ⭐⭐ (depends on usage ⚠️)

---

## ⚫ Boilerplate

JDBC ❌ High  
JdbcTemplate ✅ Reduced  
NamedParameter ✅ Clean  
JPA ✅ Minimal

---

## 🟣 Mapping

JDBC → manual  
JdbcTemplate → RowMapper  
Named → RowMapper / Extractor  
JPA → automatic (ORM)

---

## 🟡 CRUD Methods

| Operation | JDBC | JdbcTemplate | Named | JPA |
|----------|------|-------------|------|-----|
| CREATE | executeUpdate | update() | update() | save() |
| READ 1 | executeQuery | queryForObject() | queryForObject() | findById() |
| READ many | executeQuery | query() | query() | findAll() |
| UPDATE | executeUpdate | update() | update() | save() |
| DELETE | executeUpdate | update() | update() | delete() |

---

## 🔵 Query Style

JDBC → raw SQL  
JdbcTemplate → raw SQL  
Named → SQL with names  
JPA → JPQL / Derived / Native

---

## 🟤 Best Use Case

JDBC → low-level control  
JdbcTemplate → fast SQL apps  
Named → complex readable SQL  
JPA → CRUD apps

---

## 🔴 When NOT to Use

JDBC ❌ production  
JdbcTemplate ❌ dynamic params heavy  
Named ❌ simple queries (overkill)  
JPA ❌ complex queries / performance critical

---

## 🔥 Hybrid Architecture (REAL WORLD)

JPA → CRUD  
JdbcTemplate / Named → complex queries

👉 Best combo ✅

---

## ⚪ 15-sec Recall

JDBC → control  
JdbcTemplate → speed  
Named → readability  
JPA → abstraction

? → JDBC  
? + [] → JdbcTemplate  
:name → Named  
method → JPA  