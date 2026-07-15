1. **What is a REST API?** 
    
A REST API (Representational State Transfer) is an architectural style for building web services that use HTTP methods to access and manage resources. It guarantees stateless communication and supports JSON, XML, and other formats.

2. **What are the main HTTP methods used in REST APIs?**

This is one of the most important interview questions on REST web services.

The main HTTP methods are GET (read data), POST (create data), PUT (update data), and DELETE (remove data).

3. **What is statelessness in the REST?**

Statelessness means each request from the client to the server must contain all necessary information. The server does not store any client context between requests.

4. **What is a resource in the REST?**

A resource is any piece of data or information identified by a unique URI, such as a user profile, image, or document.

5. **What is JSON, and why is it used in REST APIs?**

JSON (JavaScript Object Notation) is a lightweight data interchange format. It is easy for humans to read and write and is widely used in REST APIs for data exchange.

6. **What is the purpose of HTTP status codes?**

HTTP status codes indicate the outcome of a request. For example, 200 means success, 404 means not found, and 500 indicates a server error.

7. **What is REST API integration?**

REST API integration connects two software systems to allow them to exchange data using RESTful principles and HTTP methods.

8. **What is versioning in REST APIs?**

Versioning allows API providers to make changes without breaking existing clients. It is often managed by including a version number in the URL, like /v1/users.

9. **What is HATEOAS in REST APIs?**

HATEOAS (Hypermedia as the Engine of Application State) is a REST principle where responses include links to related actions. It allows clients to navigate the API dynamically without hard-coding endpoints.

10. **How do you handle rate limiting in REST APIs?**

Rate limiting controls the number of requests a client can make to an API within a set period. It prevents overloading and abuse by using strategies like fixed windows or token buckets.

11. **What is idempotency, and why is it important in REST APIs?**

Idempotency means that making the same request multiple times will have the same effect as making it once. It is crucial for methods like PUT and DELETE to guarantee consistent results.

12. **How do you handle pagination in REST APIs?**

Pagination manages large sets of data by dividing responses into smaller chunks. It can be implemented using query parameters like ?page=2&limit=10 or ?offset=20&count=10.

13. **What are some key security practices for REST APIs?**

Common security practices include –

* Using HTTPS for secure data transfer
* Validating inputs
* Implementing authentication and authorization (e.g., OAuth 2.0)
* And protecting against common attacks like SQL injection and XSS

14. **What are the pros and cons of using REST APIs?**

Pros: REST is simple, stateless, and widely adopted with flexible data formats.

Cons: It can be less efficient for complex operations and doesn’t support real-time communication as well as WebSockets.

15. **What is the role of caching in REST APIs?**

Caching reduces the load on the server and speeds up responses by storing copies of responses. REST APIs use headers like Cache-Control to define caching rules.

16. **How do you manage error handling in REST APIs?**

REST APIs use HTTP status codes (e.g., 400 for bad requests, 401 for unauthorized access) and provide detailed error messages in the response body for better clarity.

17. **How do you design REST APIs for high performance?**

High-performance REST APIs can be designed using techniques like

* Caching
* Using lightweight data formats
* Minimizing database calls
* And optimizing query structures with efficient indexing

18. **What are best practices for REST API documentation?**

Good documentation includes comprehensive details of endpoints, methods, request/response examples, and error codes. Tools like Swagger/OpenAPI make it easier to create interactive API documentation.

19. **How do you handle REST API versioning when dealing with breaking changes?**

Versioning strategies include –

* URI versioning (/v2/resource)
* Header versioning (custom header like Accept-Version)
* Or query parameter versioning (?version=2)
* This helps maintain backward compatibility.

20. **What is the difference between REST and SOAP?**

REST is an architectural style using HTTP and lightweight data formats like JSON. SOAP is a protocol that uses XML and offers stricter standards. REST is simpler and faster, while SOAP is more secure and has built-in ACID compliance.

21. How do you implement authentication in REST APIs?

Authentication can be implemented using techniques like API keys, OAuth 2.0, and JWT (JSON Web Tokens). These methods guarantee that only authorized clients can access the API securely.

22. What is content negotiation in REST APIs?

Content negotiation allows clients to request different data formats (e.g., JSON, XML) through headers like Accept. The server responds in the requested format, making the API more flexible and adaptable.

23. What is API throttling, and why is it used?

API throttling limits the number of requests a client can make in a given timeframe. It helps prevent overuse of resources, maintain server performance, and protect against denial-of-service (DoS) attacks.

24. What are the key principles of REST API design?

REST API design should follow principles like statelessness, a clear structure of URIs, proper use of HTTP methods, meaningful status codes, and support for various content types. This guarantees consistent, scalable, and easy-to-use APIs.

25. How would you handle a situation where a client sends a large number of requests in a short time?

“I would implement rate limiting to control how many requests a client can make within a set timeframe. This helps prevent server overload and protects against potential abuse or DoS attacks, ensuring stable API performance.”

If a client needs to send a large file through a REST API, how would you handle it?

“I would use chunked transfer encoding to let the client send the file in smaller, manageable pieces. This way, the server handles data more efficiently and prevents memory overload, allowing for smoother large file uploads.”

26. How would you design a REST API that needs to serve different clients with different data requirements (e.g., web app and mobile app)?

“Using query parameters or custom headers allows clients to specify their data needs. Content negotiation can also help serve different formats, offering tailored responses for both web and mobile applications.”

27. What would you do if a REST API endpoint has become too slow to respond?

“I would start by profiling the API to pinpoint bottlenecks. From there, I’d optimize database queries, introduce caching, and use load balancing. If needed, I’d implement asynchronous processing for resource-intensive tasks to improve response times.”

28. How do you test the performance of a REST API?

“I would use tools like JMeter or LoadRunner to simulate multiple requests and analyze response times under load. This helps identify performance bottlenecks, evaluate scalability, and ensure the API can handle high traffic.”

29. How do you test authentication and authorization in REST APIs?

“I test authentication by verifying that valid credentials are accepted and invalid ones are rejected. For authorization, I guarantee that users only have access to the data or endpoints they’re permitted to, based on roles or permissions.”

30. What is the importance of testing API error handling?

Testing error handling ensures that the API returns the appropriate status codes and error messages for invalid inputs or server issues. Proper error responses help clients diagnose problems and improve the overall user experience.

31. What is the difference between JAX-RS and Jersey in Java REST API development?

JAX-RS (Java API for RESTful Web Services) is the official API specification for building REST services in Java. Jersey is a reference implementation of JAX-RS that simplifies the process with additional features and integration support.

32. What is the role of @Path annotation in JAX-RS?

The @Path annotation defines the URI path for a resource class or method. It maps HTTP requests to specific methods in a resource class – enabling RESTful behaviour and routing based on the requested endpoint.

33. How do you implement Java RESTful web services with Spring? 
 
“In Spring, I use @RestController to create RESTful web services. I annotate methods with @RequestMapping or @GetMapping/@PostMapping to define endpoints, and Spring handles request mapping, serialization, and deserialization automatically.”


34. How would you design a simple REST API endpoint in Java to return a list of users?

Using Spring Boot, you can create a simple REST endpoint as follows:

@RestController

@RequestMapping(“/users”)

public class UserController {

    @GetMapping

    public List<User> getAllUsers() {

        return userService.getUsers();

    }

}

35. How would you handle pagination in a REST API that returns a list of items?

You can implement pagination with query parameters for page and size:

@GetMapping(“/items”)

public List<Item> getItems(@RequestParam int page, @RequestParam int size) {

    return itemService.getItems(page, size);

}

36. How would you implement an authentication mechanism for a REST API using JWT in Java?

In Spring Security, JWT can be implemented by creating a filter for request validation:

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(“Authorization”);

        if (token != null && validateToken(token)) {

            Authentication authentication = getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }

}

37. How would you implement an API endpoint to update a user resource in Java REST API?

You can implement the PUT method to update an existing resource like this:

@PutMapping(“/users/{id}”)

public User updateUser(@PathVariable long id, @RequestBody User userDetails) {

    return userService.updateUser(id, userDetails);

}

38. How do you handle CORS in a Spring Boot REST API?

CORS (Cross-Origin Resource Sharing) can be handled by adding the @CrossOrigin annotation to a controller or method in Spring Boot. You can also configure global CORS settings using a WebMvcConfigurer bean for more control.

39. What is the role of @RestController in Spring Boot?

@RestController is a specialized version of @Controller that combines @Controller and @ResponseBody. It simplifies the creation of RESTful web services by automatically serializing return objects into JSON or XML.

40. How can you validate request body data in a Spring Boot REST API?

Spring Boot provides validation annotations like @NotNull, @Size, and @Min. These can be used in conjunction with @Valid or @Validated to validate incoming request data in the controller methods before processing.

41. What is REST Assured, and how is it used for API testing?

REST Assured is a Java library for testing RESTful APIs. It simplifies sending HTTP requests, validating responses, and extracting data from JSON or XML responses. It is widely used for automating API tests with minimal code.

42. How can you extract a value from a JSON response in REST Assured?

To extract a value from a JSON response:

String value = given()

                   .when()

                   .get(“/api/endpoint”)

                   .then()

                   .extract()

                   .jsonPath()

                   .getString(“key”);


43. How do you handle authentication in REST API automation using REST Assured?

REST Assured supports various authentication methods like basic authentication and OAuth. For basic auth, you can use:

given()

    .auth().basic(“username”, “password”)

.when()

    .get(“/api/endpoint”)

.then()

    .statusCode(200);

44. How do you automate the testing of different HTTP methods using REST Assured?

REST Assured supports all HTTP methods like GET, POST, PUT, DELETE. Example for PUT:

given()

    .contentType(“application/json”)

    .body(updatedData)

.when()

    .put(“/api/endpoint”)

.then()

    .statusCode(200);

