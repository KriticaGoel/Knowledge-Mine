# 🧠 Spring Data JPA – Memory Map

## 🟣 Big Picture

Config → Entity → Repository → Relations → Fetch/Cascade → Query → Performance

---

## 🔵 Setup Flow

Dependency → application.properties → Entity → Repository

---

## 🟡 Core Config

spring.datasource.* → DB connection  
spring.jpa.hibernate.ddl-auto → schema control

create → fresh DB  
update → safe changes ✅ (most used)

---

## 🟢 Entity Basics

@Entity → table  
@Table → custom name  
@Id → PK  
@GeneratedValue → ID strategy

---

## 🔴 ID Generation (🔥 Important)

AUTO → avoid in prod  
IDENTITY → MySQL, no batching ❌  
SEQUENCE → best performance ✅  
UUID → distributed systems

👉 10-sec recall:  
IDENTITY = simple  
SEQUENCE = scalable

---

## 🔵 Repository

CrudRepository → basic  
JpaRepository → + pagination + sorting + batch ✅

---

## 🟣 JPA Methods

save → insert/update  
findById → single  
findAll → list  
delete → remove  
count → total

🔥 Rule:
save → upsert  
queryForObject equivalent → findById

---

## 🟠 Relations

1-1 → OneToOne  
1-M → OneToMany + ManyToOne  
M-M → ManyToMany

👉 Owner side → FK holder

---

## ⚫ FetchType (🔥)

LAZY → load on demand ✅  
EAGER → load immediately ❌

Default:
1-M → LAZY  
M-1 → EAGER

👉 Rule:
Use LAZY always unless needed

---

## 🟤 Cascade

PERSIST → save child  
MERGE → update  
REMOVE → delete  
ALL → everything

👉 Rule:
Use ALL only if child fully dependent

---

## 🔴 Performance (🔥🔥)

Problem → N+1

Fix:
JOIN FETCH  
Batch Fetch

---

## 🟡 Query Types

Derived → findByName  
JPQL → @Query  
Native → raw SQL  
Criteria → dynamic

👉 10-sec recall:
Simple → Derived  
Custom → JPQL  
Complex → Native  
Dynamic → Criteria

---

## ⚪ Best Practices

❌ findAll() on large table  
❌ EAGER everywhere  
❌ AUTO strategy  
❌ Missing @Transactional

✅ Pagination  
✅ LAZY  
✅ SEQUENCE  
✅ JOIN FETCH

---

## 🔥 15-sec Recall

Entity → Table  
Repository → DB access  
LAZY → performance  
SEQUENCE → scale  
JOIN FETCH → fix N+1  