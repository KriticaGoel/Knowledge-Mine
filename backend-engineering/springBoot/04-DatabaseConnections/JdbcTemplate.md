Spring's JdbcTemplate provides a wide range of methods for executing SQL queries, updates, batch operations, and handling results — without the boilerplate of JDBC.

Here’s a categorized list of the most commonly used JdbcTemplate methods with their use cases:

#### 🔹 1. Querying Data (SELECT)

✅ queryForObject(...)

**Use When**: You expect a single row with a single column (e.g. a count or a specific value).

Example:

```java
int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
```
✅ queryForList(...)

**Use When**: You expect multiple rows, but each row is a map of column-value pairs.

Returns: List<Map<String, Object>>

Example:

```java
List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM users");
```
✅ query(...)

**Use When**: You want to map rows to POJOs or handle custom logic using a RowMapper.

Returns: List<T> or T

Example (POJO Mapping):

```java
List<User> users = jdbcTemplate.query("SELECT * FROM users",
    new BeanPropertyRowMapper<>(User.class));
```

Example (Custom RowMapper):

```java
List<User> users = jdbcTemplate.query("SELECT * FROM users", new RowMapper<User>() {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        return user;
    }
});
```
#### 🔹 2. Executing Updates (INSERT, UPDATE, DELETE)

✅ update(...)

**Use When**: You want to execute an INSERT, UPDATE, or DELETE statement.

**Returns**: Number of rows affected.

Example:

```java
int rows = jdbcTemplate.update("UPDATE users SET status = ? WHERE id = ?", "ACTIVE", 5);
```

#### 🔹 3. Batch Updates

✅ batchUpdate(...)

**Use When**: You want to insert or update multiple records in a single call.

Example:

```java
List<Object[]> batchArgs = Arrays.asList(
    new Object[] { "John", "john@example.com" },
    new Object[] { "Jane", "jane@example.com" }
);

jdbcTemplate.batchUpdate("INSERT INTO users (name, email) VALUES (?, ?)", batchArgs);
```

#### 🔹 4. Executing Arbitrary SQL (e.g., DDL)

✅ execute(...)

**Use When**: You want to execute DDL statements or general SQL that doesn’t return a result.

Example:
```
jdbcTemplate.execute("CREATE TABLE test(id INT PRIMARY KEY, name VARCHAR(50))");
```

#### 🔹 5. Querying with Named Parameters (via NamedParameterJdbcTemplate)

While not a JdbcTemplate method itself, it's often used alongside:

```java
NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

String sql = "SELECT * FROM users WHERE id = :id";
Map<String, Object> params = Collections.singletonMap("id", 5);
User user = namedTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(User.class));

```

🔹 Quick Summary Table

| Method                        | Purpose / Use Case                                         |
| ----------------------------- | ---------------------------------------------------------- |
| `queryForObject(sql, Class)`  | Single row, single column result (e.g. `COUNT(*)`, `name`) |
| `queryForList(sql)`           | Multiple rows as List of Map\<String, Object>              |
| `query(sql, RowMapper)`       | Multiple rows mapped to POJO or custom object              |
| `update(sql, args...)`        | Execute `INSERT`, `UPDATE`, `DELETE`                       |
| `batchUpdate(sql, List args)` | Batch insert/update                                        |
| `execute(sql)`                | Arbitrary SQL, typically DDL                               |
