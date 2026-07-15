    1. Why not use @Component instead of @Configuration?
Although @Configuration is meta-annotated with @Component, there’s an important difference:

📌 @Configuration classes are proxy-enhanced, meaning when you call a @Bean method, Spring ensures singleton behavior and dependencies are resolved correctly.

📌 If you use @Component instead:
#### Incorrect usage
```java
@Component  // Not recommended
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


```
#### Correct usage
```java
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```

➡ Spring will create a new instance every time the method is called!

➡ Bean lifecycle will NOT be properly managed.

👉 Conclusion: Always use @Configuration if you write @Bean methods.
    
    2. Difference between stateless and stateful sessions

📌 Stateless Session

Server does NOT store session data.

Each request contains all information needed to process it.

The server treats every request as independent.

Common in REST APIs.

Easier to scale because no session dependency.

🔹 Example:

HTTP request with token → server validates token → gives response.
No memory of previous login or requests.

📌 Stateful Session

Server stores user/session data.

Each request depends on previous interactions.

The client sends only an identifier (e.g., session ID), and the server retrieves session info.

Used in traditional web apps and systems requiring continuity.

🔹 Example:

User logs in → server stores session → client sends session ID → server uses stored data to maintain continuity, like cart items or UI state.
🔍 Comparison Table

| Feature                      | Stateless           | Stateful                 |
|-----------------------------|---------------------|---------------------------|
| Stores session on server?   | ❌ No                | ✔ Yes                    |
| Depends on previous request?| ❌ No                | ✔ Yes                    |
| Scaling                     | 🚀 Easy             | ⚠️ Difficult             |
| Performance                 | ⚡ Faster           | 🐢 Slightly slower       |
| Used in                     | REST APIs           | Traditional web apps     |
| Example                     | JWT-based login     | JSESSIONID login         |

🛠 When to Use What?

| Use when…                                          | Stateless | Stateful |
| -------------------------------------------------- | --------- | -------- |
| You want microservices or distributed system       | ✔         | ❌        |
| Need high scalability                              | ✔         | ❌        |
| Require interaction memory (cart, multi-step form) | ❌         | ✔        |
| Building modern APIs                               | ✔         | ❌        |
| Traditional Java web app (like JSP+Servlet)        | ❌         | ✔        |

💡 Spring Boot Perspective

Stateless: Use JWT, OAuth2, REST controllers.

Stateful: Use HttpSession, cookie-based authentication (e.g., JSESSIONID).

🧠 Quick Summary

Stateless = Every request is new.
Stateful = Server remembers you.

    3. What does {noop} mean?
It stands for "No Operation", meaning the password is stored and compared in plain text.

```java
User.withUsername("admin")
    .password("{noop}admin123")
    .roles("ADMIN")
    .build();

```

Here, Spring understands that "admin123" is the actual raw password, without any encryption.

    4. WHat are the different scope of dependency?

| Scope    | Compile | Runtime | Test | Included in artifact | Typical Use Case            |
| -------- | ------- | ------- | ---- | -------------------- | --------------------------- |
| compile  | ✅       | ✅       | ✅    | ✅                    | Most libs your app needs    |
| provided | ✅       | ❌       | ✅    | ❌                    | Servlet API, container libs |
| runtime  | ❌       | ✅       | ❌    | ✅                    | DB drivers, logging         |
| test     | ❌       | ❌       | ✅    | ❌                    | JUnit, Mockito              |
| system   | ✅       | ✅       | ✅    | ❌                    | Local JARs                  |
| import   | ✅       | ✅       | ✅    | ❌                    | BOM management              |
