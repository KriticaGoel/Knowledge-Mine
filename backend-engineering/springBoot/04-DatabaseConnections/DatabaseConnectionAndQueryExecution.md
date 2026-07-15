# Agenda
1. [Comparison of Approaches](#comparison-of-database-connection-and-query-execution-approaches-in-spring)
2. [Real-World Recommendation](#real-world-recommendation-senior-level-thinking)
3. [Implementation JDBC](#implementation-jdbc)
4. [Implementation JdbcTemplate](#implementation-jdbctemplate)
5. [Implementation NamedParameterJdbcTemplate](#implementation-namedparameterjdbctemplate)
5. Implementation JPA
6. Implementation Hibernate7. 
8. Implementation Spring Data JDBC
9. Implementation jOOQ
10. Implementation R2DBC
11. Implementation Combine Approaches - JPA, JdbcTemplate, HikariCP
12. Multiple database connections and transactions

### Comparison of Database Connection and Query Execution Approaches in Spring

JDBC → manual
JdbcTemplate → simple SQL
JPA → easy but slower
jOOQ → complex SQL
R2DBC → reactive

| **Approach** | **Performance** | **Control** | **Complexity** | **Best Use Case** | **How it work**                                 | ** Core Concept**         |
|---|---|---|---|---|-------------------------------------------------|---------------------------|
| **JDBC** | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ❌ High | Low-level work | You manually create connection and execute SQL. | JDBC                      |
| **JdbcTemplate** | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⚖️ Medium | Fast + SQL control | Spring wraps JDBC and removes boilerplate.| Spring JdbcTemplate       |
| **JPA** | ⭐⭐ | ⭐ | ⭐ Easy | CRUD apps |Uses ORM to map Java objects ↔ tables<br/>Performance issues (N+1 problem) | Spring Data JPA <br/>Hibernate |
| **Hibernate** | ⭐⭐ | ⭐⭐ | ❌ Medium | Custom ORM |You directly use ORM without Spring Data abstraction.|
|**NamedParameterJdbc<br/>Template** | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⚖️ Medium | SQL with named params | Like JdbcTemplate but supports named parameters. | Spring NamedParameterJdbcTemplate |
|**Spring Data JDBC**| ⭐⭐⭐ | ⭐⭐ | ⚖️ Medium | Simple SQL + Spring Data features | Like JPA but without ORM. Maps directly to tables. | Spring Data JDBC |
| **jOOQ** | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ❌ Medium | Complex SQL |Write SQL in a type-safe Java DSL| | jOOQ |
| **R2DBC** | ⭐⭐⭐⭐ | ⭐⭐⭐ | ❌ High | Reactive apps |Non-blocking DB access| | Reactive Relational Database Connectivity (R2DBC) |


### 💡 **Real-World Recommendation (Senior-level thinking)**

In production systems, people often combine approaches:

Use JPA → for normal CRUD

Use JdbcTemplate / jOOQ → for complex queries

Use HikariCP → for connection pooling

👉 **This hybrid approach gives:**

Clean code (JPA)

Performance (SQL tools)

Scalability

### Implementation JDBC

1. Dependency
```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. Configure Database Connection
```java
   String url = "jdbc:mysql://localhost:3306/sampleapi";
   String user = "root";
   String password = "password";
``` 

3. Get Connection
   Two ways:
- DriverManager
```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/testdb",
    "root",
    "password");
```   
❌ Problem

Creates a new connection every time

Expensive (slow in production)

- DataSource (Correct Approach)
```java
DataSource dataSource; // injected or configured

Connection con = dataSource.getConnection();
```
    
4. Create Query
```java
String sql = "SELECT id, name FROM users WHERE id = ?";
PreparedStatement ps = con.prepareStatement(sql);
ps.setInt(1, 1);
```

5. Execute Query
```java
ResultSet rs = ps.executeQuery(); 
```
6. Map results manually
```java
 while (rs.next()) {
   int id = rs.getInt("id");
   String name = rs.getString("name");
   System.out.println(id + " " + name);
}
```
7. Close resources
```java
finally {
    if (rs != null) rs.close();
    if (ps != null) ps.close();
    if (con != null) con.close();
}
```

😵‍💫 Problems with Raw JDBC
1. Boilerplate code
   try/catch/finally everywhere
2. Manual resource management
   Forget to close → memory leak
3. Manual mapping
   user.setName(rs.getString("name"));
4. No transaction management
   You must handle commit/rollback
   con.setAutoCommit(false);
   con.commit();
   con.rollback();
   ⚡ 4. Why It Still Matters

Even if you use Spring:

Spring JdbcTemplate → wraps JDBC
Hibernate → uses JDBC internally

👉 So understanding JDBC = understanding everything beneath

### Implementation JdbcTemplate

It removes:

* manual connection handling
* try/catch/finally
* ResultSet mapping boilerplate

👉 You only write:

* SQL
* mapping logic

1. Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

2. Database Configurations
```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/ORCLPDB1
spring.datasource.username=system
spring.datasource.password=password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```
👉 Spring Boot auto-creates:

* DataSource
* JdbcTemplate

3. Model Class
```java 
public class User {
    private Long id;
    private String name;

    // getters & setters
} 
```
4. Repository Layer

--READ (single)  -> queryForObject
```java
public Users findById(String id) {

        String sql ="SELECT * FROM users WHERE id = ?";

        Object[] params = new Object[]{id};

        RowMapper<Users> rowMapper = (rs, rowNum) -> {
            Users u = new Users();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            return u;
        };
    return jdbcTemplate.queryForObject(sql, params,rowMapper);

}
```
--READ (List)  -> query
```java
 public List<Users> findAll(){
        String sql ="Select * from Users";

        RowMapper<Users> rowMapper =(rs,rowNum)->{
            Users u= new Users();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            return u;
        };

        return jdbcTemplate.query(sql,new Object[]{},rowMapper);
    }
```

--Create -> update()
```java
public int insertUser(String name,String email){
        String sql="insert into users(name,email) values(?,?)";
        return jdbcTemplate.update(sql,name,email);
    }
```

--Update -> update()
```java
public int updateUser(String name, String email){
        String sql ="update Users set email=? where ucase(name)=?";
        return jdbcTemplate.update(sql,email,name.toUpperCase());
    }
```

-Delete -> update()
```java
 public int deleteUser(String name){
        String sql="Delete fromUsers where ucase(name)=?";
        return jdbcTemplate.update(sql,name.toUpperCase());

    }
```

### Implementation NamedParameterJdbcTemplate

* Powerful if we have model/DTO
* Spring uses: BeanPropertySqlParameterSource Its maps ->Java field → SQL named parameter
* Example : user.getName() → :name
* Important Rules
  * Field names must match SQL params
  * keep everything lowercase in SQL
  * In case of missing field :age -> Run time Error
  * Use Map for Dynamic Query or SImple static Query
  * **BeanPropertySqlParameterSource does NOT automatically flatten nested objects like address.city use Custom ParameterSource**

Flatten DTO means no nested DTO mention all field in single class
```java 
public class UserDTO {
    private Long id;
    private String name;
    private String city;
    private String country;
}
```

Repeated logic ->	Custom ParameterSource

Clean architecture ->	Flatten DTO ✅

Dependency, Database Configuration and Model class, Controller, Service remain same as JDBC Template

4. Repository

    **Create using Map** 
    ```java
    public int createUserMap(UserRequest user) {
        String sql = "insert into users(name,email) values (:name,:email)";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", user.getName());
        params.put("email", user.getEmail());

        return namedParameterJdbcTemplate.update(sql, params);

    }
   ```

    **Create using BeanPropertySqlParameterSource**
    BeanPropertySqlParameterSource -> Its map Java field → SQL named parameter

    Create DTO
    ```java9
   public class UserRequest {

    private String name;
    private String email;
    }
    ```
   ```java 
   public int createUser(UserRequest user) {
        String sql = "Insert into Users(name,email) values(:name,:email)";
        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user));
    } 
   ```