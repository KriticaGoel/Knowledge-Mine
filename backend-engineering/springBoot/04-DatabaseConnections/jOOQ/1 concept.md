

#### Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jooq</artifactId>
</dependency>
```

#### Code Generation (IMPORTANT)

jOOQ generates classes like:

USERS

ADDRESS

USERADDRESS

👉 These map to tables

#### Autowire DSLContext
```java 
@Autowired
private DSLContext dsl;
```

#### Complex Query Example (User → Orders → Items)
🟢 Requirement

Fetch:
- user info
- order count
- total order amount
- list of items