# Real World Usage

| System | Why Singleton? |
|---|---|
| Logger | One centralized logger |
| Cache Manager | Shared cache |
| Config Manager | Single config source |
| Database Pool | Shared DB connections |
| Spring Beans | Singleton scope by default |

---

# Spring Boot Example

```java
@Service
public class UserService {
}
```

Spring creates:

only one bean object
shared across application

Default scope:

singleton
Industry Examples
Hibernate SessionFactory
Spring Beans
Runtime class
Cache systems
<details> <summary>When NOT to Use Singleton</summary>

❌ User-specific data
❌ Stateful services
❌ Request-scoped logic

</details> ```