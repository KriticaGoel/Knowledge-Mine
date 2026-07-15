### Agenda

* [Configuration](#configuration)
* [Annotation](#annotations)
* [spring.jpa.hibernate.ddl](#springjpahibernateddl)
* [GenerateType](#generatetype)
* [Repository](#repository)
* [Customised Method in JPA](#customised-method-in-jpa)
* [Custom Query in JPA](#custom-query-in-jpa)
* [Relations](#relations)
  * [one-to-one mapping](#one-to-one)
  * [one-to-many mapping](#One-to-Many)
  * [many-to-many mapping](#Many-to-Many)
  * [CircularReference](#circularreference)
* [Cascading](#cascading)
* [FetchType](#fetchtype)
* [Best practise to create models with an example](#best-practise-to-create-models-with-an-example)
* [Batch Fetching (for @ManyToOne, @OneToOne)](#Batch-Fetching-(for-@ManyToOne,-@OneToOne))
* [Customised Method in JPA](#customised-method-in-jpa)
  * [Derived Query Methods (Method Name Convention)](#1-derived-query-methods-method-name-convention))
  * [JPQL (Java Persistence Query Language) with @Query Annotation](#2-jpql-java-persistence-query-language-with-query-annotation)
  * [Native SQL Queries](#3native-sql-queries)
  * [Criteria API (Dynamic Queries in Java Code)](#4-criteria-api-dynamic-queries-in-java-code)


Summary:
- Configure JPA in Spring Boot with dependencies & properties.
- Define entities, and repositories using Spring Data JPA.
- Define Relations: One-to-One, One-to-Many, Many-to-Many.
- Understand fetch types, cascading, and best practices for model design.
- Use appropriate query method: Derived Query Methods, JPQL with @Query,Native SQL Queries, or Criteria API.
- Be aware of performance pitfalls like the N+1 problem and use `JOIN FETCH` or batch fetch to optimize.

### Configuration

1. pom.xml

```xml
<dependency>
   <groupId>com.mysql</groupId>
   <artifactId>mysql-connector-j</artifactId>
   <version>9.1.0</version>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

```

2.application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sb_learn
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
```
Oracle Properties 
```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:oms
spring.datasource.username=ecom
spring.datasource.password=ecom
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.show-sql=true
```
[Home](#agenda)
### Annotations

* @Entity - change class to entity. Spring boot will create table same as class name
* @Table(name="abc") - giving specific name to table. not same as class name
* @ID - Make object as a primary key in table
* @GenerateValue(strategy= GenerateType.Identity)

#### spring.jpa.hibernate.ddl

> spring.jpa.hibernate.ddl-auto=create-drop

* **create**—when application starts create a schema and destroy previous data
* **create-drop** - when application starts create a schema and when application stop drop the schema
* **update**—if schema exists, update the changes only.
* By default, value is create-drop

#### GenerateType

1. **Auto**—Delegate to JPA to choose the appropriate strategy for the particular database
2. **Identity**—Indicates that the persistence provider must assign primary keys for the entity using a database
   identity column.
3. **Table**-Indicates that the persistence provider must assign primary keys for the entity using an underlying
   database table to ensure uniqueness.
4. **UUID**-Indicates that the persistence provider must assign primary keys for the entity by generating an RFC 4122
   Universally Unique Identifier.
5. **Sequence**—Indicates that the persistence provider must assign primary keys for the entity using a database
   sequence.

@GeneratedValue(strategy=GenerationType.Sequence,generator="cat_seq")

@SequenceGenerator(name="cat_seq", SequenceName="cat_sequence",allocationSize=1)

[Home](#agenda)

#### Repository

1. CrudRepository
2. JpaRepository

```java
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

```

#### JPA methods
Inject Repository in Service class using @Autowired or constructor injection

```java

@Service
public class CategoryServiceImpl implements CategoryService {

   // public List<Category> categories = new ArrayList<Category>();
   private int nextId = 1;
   @Autowired
   private CategoryRepository categoryRepository;

   public List<Category> getCategories() {
      return categoryRepository.findAll();
   }

   public String createCategory(Category category) {
      category.setId(nextId++);
      categoryRepository.save(category);
      return "Category created successfully";
   }

   public String deleteCategory(int id) {
      List<Category> categories = categoryRepository.findAll();
      Category category = categories.stream().filter(c -> c.getId() == id).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
      categoryRepository.deleteById(category.getId());
      return "Category deleted successfully";
   }

   @Override
   public String updateCategory(int id, Category updatedCategory) {
      List<Category> categories = categoryRepository.findAll();
      Category category = categories.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
      if (category == null) {
         createCategory(updatedCategory);
         return "Category created successfully";
      } else {
         category.setCategoryName(updatedCategory.getCategoryName());
         categoryRepository.save(category);
         return "Category updated successfully";
      }
   }

}

```

JPA ENTITY NEED GETTER AND SETTERS



[Home](#agenda)
### Relations

Entity Overview

| Entity         | Columns                        |
| -------------- | ----------------------------- |
| SocialUser     | user_id (PK), username, password |
| SocialProfile  | profile_id (PK), name, age    |
| Post           | post_id (PK), post            |
| SocialGroup    | group_id (PK)                 |


SocialUsers
```java
@Entity
public class SocialUsers {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username, password;
}
```
SocialProfile
```java
@Entity
public class SocialProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
}
```
Post
```java
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String post;
}
```


SocialGroup
```java
@Entity
public class SocialGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
```

Relationships 

#### One-to-One

SocialUsers ↔ SocialProfile


In SocialUsers
```java
//Tell SocialUsers class that there is one to one mapping with SocialProfile class.
//mappedBy should be the same as field name in SocialProfile class
@OneToOne(mappedBy = "socialUser")
private SocialProfile socialProfile;

```
In SocialProfile
```java
//this will create foreign key of social user class in SocialProfile table
@OneToOne
@JoinColumn(name = "social_user_id")
private SocialUsers socialUser;
```

#### One-to-Many

SocialUsers → Post


In SocialUsers
```java
@OneToMany(mappedBy = "socialUser")
private List<Post> posts = new ArrayList<>();

```
In Post
```java
@ManyToOne
@JoinColumn(name = "social_user_id")
private SocialUsers socialUser;
```
#### Many-to-Many
SocialUsers ↔ SocialGroup

In SocialUsers
```java
@ManyToMany
@JoinTable(
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "group_id")
)
private Set<SocialGroup> groups = new HashSet<>();

```
In SocialGroup
```java
@ManyToMany(mappedBy = "groups")
private Set<SocialUsers> socialUsers = new HashSet<>();
```

#### CircularReference
Add @JsonIgnore - to ignore object inside object 

or to make circular loop

or to ignore the field in JSON

[Home](#agenda)

#### Cascading
* Saving data of dependent entity with parent entity.
* Like creating a new profile while creating new User.
* Creating a new User having new profile and post.

Types of Cascading
1. Persist - if one entity is persisted, then it cascades the same operation to another entity
2. Merge
3. Remove
4. Refresh
5. Detach
6. All—All will perform all above operations

In bidirectional setting, we need to set foreign key using custom setter.

here in SocialProfile we have a SocialUser id as a foreign key. to set its value we need to create a custom method
```java

@OneToOne(mappedBy = "socialUser", cascade = CascadeType.ALL)
@OneToOne(mappedBy = "socialUser", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
```
```java
public void setSocialProfile(SocialProfile socialProfile) {
    // this.socialProfile = socialProfile;
    socialProfile.setSocialUser(this);
    this.socialProfile = socialProfile;
}
```

#### FetchType

>FetchType plays a crucial role in defining how and when related entities are loaded from the database in relation to the parent entity

There are two types of FetchType
1. **LAZY**—They are loaded on demand that they are loaded when access first time in code
2. **EAGER**-Related entities are loaded as soon as parent entities laoded in system

**When to use**:

In a system we have customer and order i.e., one-to-many relationship
one customer has multiple orders.

If we use EAGER FetchType, then while fetching customer, order data will be loaded immediately in memory, which is not useful at that time.

If we use LAZY FetchType, then order data will be loaded when need like showing customer order history. 

**Default FetchType**
1. OneToMany : Lazy
2. ManyToOne : Eager
3. ManyToMany : Lazy
4. OneToOne : Eager

```java
@ManyToMany(mappedBy = "socialUser",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
```

[Home](#agenda)

#### Best practise to create models with an example

Step 1: Create a Base model class and use annotation MappedSuperclass

```java
@Data
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date creationDate;
    private Date modificationDate;
    private String createdBy;
    private String modifiedBy;
    private State state;
}
```


Step 2 : Create other models and extend this Base model. Primary key will be created for other models

Example User class:
```java
@Data
@Entity
public class User extends BaseModel{

    @Column(unique = true,length = 50)
    private String username;
    @Column(length = 50)
    private String password;
    @Column(length = 100,unique = true)
    private String email;
    @Column(length = 15,unique = true)
    private String phone;
}
```

Example Role Class:
```java
@Data
@Entity
public class Role extends BaseModel{
    private String roleName;
}
```

[Home](#agenda)

#### <span style="color:blue">Customised Method in JPA</span>
There are 3 main ways to define these methods in JPA (especially when using Spring Data JPA):
#### 1. Derived Query Methods (Method Name Convention)

You define methods by following a naming convention. Spring Data JPA automatically interprets these and generates the corresponding SQL queries.

```java
List<User> findByLastName(String lastName);
User findByEmailAndStatus(String email, String status);
```
Automatically parsed into SQL like:
```sql
SELECT * FROM users WHERE last_name = ?;
```
Built-in methods like `findById()`, `findAll()`, etc., are also available.

More methods can be defined by combining field names and conditions, such as:
```java
List<User> findByLastNameAndAgeGreaterThan(String lastName, int age);
```
Automatically parsed into SQL like:
```sql
select * from User where Last_name = 'abc' and age>10;
```
```java
Category findByCategoryName(Sting categoryName);
```
```java
Token findByTokenAndExpiryDateAfter(String token, Date expiryDate);
```
Automatically convert to SQL like:
```sql
select * from token where token =<value> and expiry_date > current_date;
```

This method is simple and effective for straightforward queries, but can become complex for more advanced queries.

Simple join via @ManyToOne
```java
@Entity
public class Order {
    @Id
    private Long id;

    @ManyToOne
    private Customer customer;
}

@Entity
public class Customer {
    @Id
    private Long id;

    private String name;
}
```
JPA query can be written as:
```java
List<Order> findByCustomerName(String name);
```
This will be translated to SQL like:
```sql
SELECT o FROM Order o JOIN o.customer c WHERE c.name = :name;
```
Order have Customer and Customer have Name field. So, we can use that to get all orders of a customer by name.

@OneToMany relationship

You **can’t directly query** findByOrdersStatus(String status) using derived methods — for that, use @Query.

Join with Nested Fields
```java
List<Order> findByCustomerAddressCity(String city);
```

**Assuming:**

Order -> Customer

Customer -> Address

Address -> city

Spring Data JPA will walk the path using joins.

[Home](#agenda)

📌 **Best practices for derived methods:**
* OneToOne
* ManyToOne

<u>Problem while using with **ManyToMany or ManyToOne** relationships:</u>

Suppose you have:

```java
List<Order> orders = orderRepository.findAll();
for (Order order : orders) {
    System.out.println(order.getCustomer().getName());
}
```
If Order has a @ManyToOne to Customer, and that association is lazily loaded (fetch = FetchType.LAZY), JPA will:

1. Fetch all orders in 1 query: SELECT * FROM orders

2. For each order, fetch its customer in a separate query:

   SELECT * FROM customer WHERE id = ? → repeated N times

So for N orders, you get N + 1 queries — and that's expensive.

Best way to fix it is to use `@Query` annotation with JPQL or native SQL to fetch all orders with their customers in a single query.
Alternative way is to use Batch Fetching.

### Batch Fetching (for @ManyToOne, @OneToOne)

In persistence.xml or application.properties:
```properties
spring.jpa.properties.hibernate.default_batch_fetch_size=16
```
* Tells Hibernate: “If you need to fetch multiple lazy associations, do it in batches.”
* So instead of:
```sql
SELECT * FROM customer WHERE id = ?
...
```
You get:
```sql
SELECT * FROM customer WHERE id IN (?, ?, ?, ...)
```

#### 2. JPQL (Java Persistence Query Language) with @Query Annotation

You can write custom queries using JPQL, which is similar to SQL but operates on entity objects rather than database tables.

```java

@Query("update User u set u.role = ?1 where u.name = ?2")
int updateRole(String role, String name);

@Query("select t.id FROM User t where t.role = :role AND t.name = :name")
public Optional<User> findByRoleAndName(@Param("role") String role,
@Param("name") String name);

@Query("SELECT u FROM User u JOIN u.role r WHERE r.name = :roleName")
List<User> findUsersByRoleName(@Param("roleName") String roleName);

```
This allows for more complex queries, including joins and subqueries, while still being portable across different databases.

🧩 1. Basic Syntax of JPQL JOIN
Syntax
```jpaql
SELECT e FROM EntityA e JOIN e.relatedEntity r WHERE r.property = :value
```
* EntityA and relatedEntity must be connected via a JPA relationship (@OneToMany, @ManyToOne, etc.)

* JPQL uses entity and field names, not table and column names.

🧠 Example: JOIN Between Order and Customer
```java
@Entity
public class Order {
    @Id
    private Long id;

    @ManyToOne
    private Customer customer;
}

@Entity
public class Customer {
    @Id
    private Long id;
    private String name;
}

```
Repository Method
```java
@Query("SELECT o FROM Order o JOIN o.customer c WHERE c.name = :name")
List<Order> findOrdersByCustomerName(@Param("name") String name);
```

This performs an inner join from Order to Customer, and filters by Customer.name

🔀 Types of Joins in JPQL

| Type              | JPQL Example                 | Description                               |
| ----------------- | ---------------------------- | ----------------------------------------- |
| `JOIN`            | `JOIN e.relation r`          | Inner join (default)                      |
| `LEFT JOIN`       | `LEFT JOIN e.relation r`     | Includes `e` even if `relation` is `null` |
| `JOIN FETCH`      | `JOIN FETCH e.relation`      | Eagerly loads `relation` to avoid N+1     |
| `LEFT JOIN FETCH` | `LEFT JOIN FETCH e.relation` | Eagerly loads optional relation           |


Left Join
```java
@Query("SELECT o FROM Order o LEFT JOIN o.customer c WHERE c.name = :name or OR c IS NULL")
List<Order> findOrdersByCustomerName(@Param("name") String name);
```

This becomes a LEFT JOIN — includes Orders even if customer is null.


🚀 JOIN FETCH to Fix N+1 Problem
```java
@Query("SELECT o FROM Order o JOIN FETCH o.customer")
List<Order> findAllOrdersWithCustomers();
```

This:

* Loads both Order and Customer in one SQL query

* Avoids lazy loading (solves N+1)

* Still returns List<Order>


🧠 Nested Joins
```java
@Query("SELECT o FROM Order o JOIN o.customer c JOIN c.address a WHERE a.city = :city")
List<Order> findOrdersByCustomerCity(@Param("city") String city);
```
Assuming:

Order → Customer → Address

Writing Good JPQL Joins

| Tip                               | Explanation          |
| --------------------------------- | -------------------- |
| Use entity names, not table names | JPQL is object-based |
| Use `JOIN FETCH` for performance  | Prevents N+1 problem |
| Alias your joins (`JOIN x.y z`)   | Improves readability |
| Use parameters (`:param`)         | Secure and reusable  |


#### 3.Native SQL Queries
You can also use native SQL queries directly if you need to execute database-specific queries or complex SQL that cannot be expressed in JPQL.

```java
@Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
User findUserByEmail(String email);
```

**Useful when:**

* You need DB-specific features.

* JPQL can't express the query (e.g., full-text search, CTEs, etc.)

#### 4. Criteria API (Dynamic Queries in Java Code)

The Criteria API allows you to build queries programmatically, which is useful for dynamic queries where conditions may change at runtime.

```java
CriteriaBuilder cb = entityManager.getCriteriaBuilder();
CriteriaQuery<User> cq = cb.createQuery(User.class);
Root<User> user = cq.from(User.class);
cq.select(user).where(cb.equal(user.get("status"), "ACTIVE"));
List<User> results = entityManager.createQuery(cq).getResultList();
```

#### Summary Table:

| Method              | Description                     | Use When                          |
| ------------------- | ------------------------------- | --------------------------------- |
| **Derived Methods** | Auto-generated by method name   | Simple fixed queries              |
| **JPQL (@Query)**   | Object-oriented query language  | Custom but portable queries       |
| **Native SQL**      | Raw SQL                         | DB-specific or complex SQL needed |
| **Criteria API**    | Programmatic query construction | Dynamic and complex filtering     |



