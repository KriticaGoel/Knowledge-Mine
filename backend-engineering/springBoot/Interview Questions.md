1. Your application runs perfectly on local, but fails after deployment. what do you verify first>
2. An API is fast in dev byt painfully slow in production-how do you trace the root cause?
3. You updated application.properties but nothing changed-what went wrong?
4. Under heavy traffic, your service starts crashing - what are the likely bottlenecks?
5. Spring complains about multiple beans of the same type - how do you resolve it cleanly?
6. Your API randomly returns 401 even though logic hasn't changed - where do you look?
7. Database connection get exhausted - how do you detect and fix this?
8. One microservice goes down and others start failing - how do you design to avoid this?
9. Memory usage keeps growing over time - what do you immediately suspect?
10. Logs are missing in production but fine locally - what are the first checks?
11. Container, Dependency, and IOC
12. What is dependency injection and what are the advantages?
13. What is an interface and what are the advantages of making use of them in Java?
14. Why are they recommended for Spring beans?
15. What is meant by â€œapplication-context?
16. How are you going to create a new instance of an ApplicationContext?
17. Can you describe the lifecycle of a Spring Bean in an ApplicationContext?
18. How are you going to create an ApplicationContext in an integration test?
19. What is the preferred way to close an application context? Does Spring Boot do this for
    you?
20. Can you describe:
21. Dependency injection using Java configuration?
22. Dependency injection using annotations (@Autowired)?
23. Component scanning, Stereotypes?
24. Scopes for Spring beans? What is the default scope?
25. Are beans lazily or eagerly instantiated by default? How do you alter this behavior?
26. What is a property source? How would you use @PropertySource?
27. What is a BeanFactoryPostProcessor and what is it used for? When is it invoked?
28. Why would you define a static @Bean method?
29. What is a ProperySourcesPlaceholderConfigurer used for?
30. What is a BeanPostProcessor and how is it different to a BeanFactoryPostProcessor?
    What do they do? When are they called?
31. What is an initialization method and how is it declared on a Spring bean?
32. What is a destroy method, how is it declared and when is it called?
33. Consider how you enable JSR-250 annotations like @PostConstruct and
    @PreDestroy? When/how will they get called?
34. How else can you define an initialization or destruction method for a Spring bean?
35. What does component-scanning do?
36. What is the behavior of the annotation @Autowired with regards to field injection,
    constructor injection and method injection?
37. What do you have to do, if you would like to inject something into a private field? How does
    this impact testing?
38. How does the @Qualifier annotation complement the use of @Autowired?
39. What is a proxy object and what are the two different types of proxies Spring can create?
40. What are the limitations of these proxies (per type)?
41. What is the power of a proxy object and where are the disadvantages?
42. What does the @Bean annotation do?
43. What is the default bean id if you only use @Bean? How can you override this?
44. Why are you not allowed to annotate a final class with @Configuration
45. How do @Configuration annotated classes support singleton beans?
46. Why canâ€™t @Bean methods be final either?
47. How do you configure profiles? What are possible use cases where they might be useful?
48. Can you use @Bean together with @Profile?
49. Can you use @Component together with @Profile?
50. How many profiles can you have?
51. How do you inject scalar/literal values into Spring beans?
52. What is @Value used for?
53. What is Spring Expression Language (SpEL for short)?
54. What is the Environment abstraction in Spring?
55. Where can properties in the environment come from â€“ there are many sources for
    properties â€“ check the documentation if not sure. Spring Boot adds even more.
56. What can you reference using SpEL?
57. What is the difference between $ and # in @Value expressions?
    Aspect oriented programming
58. What is the concept of AOP? Which problem does it solve? What is a cross cutting
    concern?
59. Name three typical cross cutting concerns.
60. What two problems arise if you don't solve a cross cutting concern via AOP?
61. What is a pointcut, a join point, an advice, an aspect, weaving?
62. How does Spring solve (implement) a cross cutting concern?
63. Which are the limitations of the two proxy-types?
64. What visibility must Spring bean methods have to be proxied using Spring AOP?
65. How many advice types does Spring support. Can you name each one?
66. What are they used for?
67. Which two advices can you use if you would like to try and catch exceptions?
68. What do you have to do to enable the detection of the @Aspect annotation?
69. What does @EnableAspectJAutoProxy do?
70. If shown pointcut expressions, would you understand them?
71. For example, in the course we matched getter methods on Spring Beans, what would
    be the correct pointcut expression to match both getter and setter methods?
72. What is the JoinPoint argument used for?
73. What is a ProceedingJoinPoint? When is it used?
    Data Management: JDBC, Transactions
74. What is the difference between checked and unchecked exceptions?
75. Why does Spring prefer unchecked exceptions?
76. What is the data access exception hierarchy?
77. How do you configure a DataSource in Spring? Which bean is very useful for
    development/test databases?
78. What is the Template design pattern and what is the JDBC template?
79. What is a callback? What are the three JdbcTemplate callback interfaces that can be used
    with queries? What is each used for? (You would not have to remember the interface
    names in the exam, but you should know what they do if you see them in a code sample).
80. Can you execute a plain SQL statement with the JDBC template?
81. When does the JDBC template acquire (and release) a connection, for every method
    called or once per template? Why?
82. How does the JdbcTemplate support generic queries? How does it return objects and
    lists/maps of objects?
83. What is a transaction? What is the difference between a local and a global transaction?
84. Is a transaction a cross cutting concern? How is it implemented by Spring?
85. How are you going to define a transaction in Spring?
86. What does @Transactional do? What is the PlatformTransactionManager?
87. Is the JDBC template able to participate in an existing transaction?
88. What is a transaction isolation level? How many do we have and how are they ordered?
89. What is @EnableTransactionManagement for?
90. What does transaction propagation mean?
91. What happens if one @Transactional annotated method is calling another
    @Transactional annotated method on the same object instance?
92. Where can the @Transactional annotation be used? What is a typical usage if you put it
    at class level?
93. What does declarative transaction management mean?
94. What is the default rollback policy? How can you override it?
95. What is the default rollback policy in a JUnit test, when you use the
    @RunWith(SpringJUnit4ClassRunner.class) in JUnit 4 or
    @ExtendWith(SpringExtension.class) in JUnit 5, and annotate your @Test annotated
    method with @Transactional?
96. Why is the term "unit of work" so important and why does JDBC AutoCommit violate this
    pattern?
97. What do you need to do in Spring if you would like to work with JPA?
98. Are you able to participate in a given transaction in Spring while working with JPA?
99. Which PlatformTransactionManager(s) can you use with JPA?
    March 2019 Â© Copyright 2019 Pivotal Software, Inc. All rights reserved 8
100. What do you have to configure to use JPA with Spring? How does Spring Boot make this
    easier?
    Spring Data JPA
101. What is a Repository interface?
102. How do you define a Repository interface? Why is it an interface not a class?
103. What is the naming convention for finder methods in a Repository interface?
104. How are Spring Data repositories implemented by Spring at runtime?
105. What is @Query used for?
    Spring MVC and the Web Layer
106. MVC is an abbreviation for a design pattern. What does it stand for and what is the idea
    behind it?
107. What is the DispatcherServlet and what is it used for?
108. What is a web application context? What extra scopes does it offer?
109. What is the @Controller annotation used for?
110. How is an incoming request mapped to a controller and mapped to a method?
111. What is the difference between @RequestMapping and @GetMapping?
112. What is @RequestParam used for?
113. What are the differences between @RequestParam and @PathVariable?
114. What are some of the parameter types for a controller method?
115. What other annotations might you use on a controller method parameter? (You can
    ignore form-handling annotations for this exam)
116. What are some of the valid return types of a controller method?
    Security
117. What are authentication and authorization? Which must come first?
118. Is security a cross cutting concern? How is it implemented internally?
119. What is the delegating filter proxy?
120. What is the security filter chain?
121. What is a security context?
122. What does the ** pattern in an antMatcher or mvcMatcher do?
123. Why is the usage of mvcMatcher recommended over antMatcher?
124. Does Spring Security support password hashing? What is salting?
125. Why do you need method security? What type of object is typically secured at the method
    level (think of its purpose not its Java type).
126. What do @PreAuthorized and @RolesAllowed do? What is the difference between them?
127. How are these annotations implemented?
128. In which security annotation are you allowed to use SpEL?
    REST
129. What does REST stand for?
130. What is a resource?
131. What does CRUD mean?
132. Is REST secure? What can you do to secure it?
133. Is REST scalable and/or interoperable?
134. Which HTTP methods does REST use?
135. What is an HttpMessageConverter?
136. Is REST normally stateless?
137. What does @RequestMapping do?
138. Is @Controller a stereotype? Is @RestController a stereotype?
139. What is a stereotype annotation? What does that mean?
140. What is the difference between @Controller and @RestController?
141. When do you need @ResponseBody?
142. What are the HTTP status return codes for a successful GET, POST, PUT or DELETE
    operation?
143. When do you need @ResponseStatus?
144. Where do you need @ResponseBody? What about @RequestBody? Try not to get these
    muddled up!
145. If you saw example Controller code, would you understand what it is doing? Could you tell
    if it was annotated correctly?
146. Do you need Spring MVC in your classpath?
147. What Spring Boot starter would you use for a Spring REST application?
148. What are the advantages of the RestTemplate?
149. If you saw an example using RestTemplate would you understand what it is doing?
    Testing
150. Do you use Spring in a unit test?
151. What type of tests typically use Spring?
152. How can you create a shared application context in a JUnit integration test?
153. When and where do you use @Transactional in testing?
154. How are mock frameworks such as Mockito or EasyMock used?
155. How is @ContextConfiguration used?
156. How does Spring Boot simplify writing tests?
157. What does @SpringBootTest do? How does it interact with @SpringBootApplication
    and @SpringBootConfiguration?
    Spring Boot Intro
158. What is Spring Boot?
159. What are the advantages of using Spring Boot?
160. Why is it â€œopinionatedâ€?
161. What things affect what Spring Boot sets up?
    March 2019 Â© Copyright 2019 Pivotal Software, Inc. All rights reserved 10
162. What is a Spring Boot starter POM? Why is it useful?
163. Spring Boot supports both properties and YML files. Would you recognize and understand
    them if you saw them?
164. Can you control logging with Spring Boot? How?
165. Where does Spring Boot look for property file by default?
166. How do you define profile specific property files?
167. How do you access the properties defined in the property files?
168. What properties do you have to define in order to configure external MySQL?
169. How do you configure default schema and initial data?
170. What is a fat far? How is it different from the original jar?
171. What is the difference between an embedded container and a WAR?
172. What embedded containers does Spring Boot support?
    Spring Boot Auto Configuration
173. How does Spring Boot know what to configure?
174. What does @EnableAutoConfiguration do?
175. What does @SpringBootApplication do?
176. Does Spring Boot do component scanning? Where does it look by default?
177. How are DataSource and JdbcTemplate auto-configured?
178. What is spring.factories file for?
179. How do you customize Spring auto configuration?
180. What are the examples of @Conditional annotations? How are they used?
    Spring Boot Actuator
181. What value does Spring Boot Actuator provide?
182. What are the two protocols you can use to access actuator endpoints?
183. What are the actuator endpoints that are provided out of the box?
184. What is info endpoint for? How do you supply data?
185. How do you change logging level of a package using loggers endpoint?
186. How do you access an endpoint using a tag?
187. What is metrics for?
188. How do you create a custom metric with or without tags?
189. What is Health Indicator?
190. What are the Health Indicators that are provided out of the box?
191. What is the Health Indicator status?
192. What are the Health Indicator statuses that are provided out of the box
193. How do you change the Health Indicator status severity order?
194. Why do you want to leverage 3rd-party external monitoring system?
    Spring Boot Testing
195. When do you want to use @SpringBootTest annotation?
196. What does @SpringBootTest auto-configure?
    March 2019 Â© Copyright 2019 Pivotal Software, Inc. All rights reserved 11
197. What dependencies does spring-boot-starter-test brings to the classpath?
198. How do you perform integration testing with @SpringBootTest for a web application?
199. When do you want to use @WebMvcTest? What does it auto-configure?
200. What are the differences between @MockBean and @Mock?
201. When do you want @DataJpaTest for? What does it auto-configure?
