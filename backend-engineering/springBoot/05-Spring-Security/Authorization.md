
AGENDA
[Authentication Flow and Challenges](#authentication-flow-and-challenges)


#### Authentication Flow and Challenges
1. Basic Authentication (Username & Password in Every Request)
   
    **Process:**
   The client sends the username and password with every API request.

    **Problems:**

    1. The client has to send credentials repeatedly, which is inconvenient and insecure.
    2. The server has to validate credentials from the database on every request, which adds performance overhead.

2. Token-Based Authentication (Session Token Stored in DB)


   **Solution:**
   After validating the user's credentials once, the server generates a token and stores it in the database.
   The client stores this token in memory or cache and sends it with each subsequent request.
   

 **Pros:**

✅ Solves Problem 1.1 (no need to send credentials repeatedly).

**Remaining Issue:**

❌ Problem 1.2 still exists — the server must look up the token in the database for every request to validate it

3. JWT (JSON Web Token) Authentication

   **Solution:**


   Use JWT, a stateless token signed using a secret or public/private key.
   

The token contains user claims and can be verified without querying the database.

How it solves Problem 1.2:


✅ The server validates the token locally (using the signature), eliminating the need for a DB lookup on each request.


### Password
1. Plaintext Password (Not Recommended)

   Password is stored as-is (e.g., "abc").

    ❌ Highly insecure — if the database is compromised, all passwords are exposed
2. Hashing (e.g., SHA-256)

   Passwords are stored as a hashed value (e.g., SHA-256("abc")).

    ✅ More secure than plaintext.

    Problem:

    a. Same input always results in the same output.

    b. If multiple users have the same password, they will have the same hashed value.

    c. Vulnerable to rainbow table attacks.
3. Hashing + Salting
   
    Add a random salt to each password before hashing:
    
    SHA-256("abc" + random_salt)

    ✅ Produces different hashes even for the same password.

    ✅ Makes rainbow table attacks ineffective.

    ❌ Still needs secure management of salts and is more complex to implement correctly.

4. Bcrypt Password Encoder
   
    Bcrypt is a modern password hashing function designed for security:

    Automatically generates a unique salt.

    Rehashes multiple times (configurable work factor).

    Output is always different even for the same input.

    ✅ More secure than plain hashing + salting.

    ❌ Problem: Since output is different each time, you cannot directly compare stored and input hashes.

5. matches() Method in Bcrypt
   
    Bcrypt provides a built-in matches(rawPassword, hashedPassword) method.

    ✅ It handles salt and internal hashing logic.

    ✅ Allows safe comparison of raw password with a hashed one.


### How Authorization works:
## Basic Flow:
1. Client send username and password to server
2. Server will do following steps:   
   1. Verify the credentials 
   2. Generate JSON having parameters like
      {
        "user_id" : "  ";
        "expiry_at" : "  ";
         "value" : "  ";
         "role" : " ";
      }
   3. Generate Token form JSON
   4. Encode token using base64encoder. This is not secure since anyone can decode it using base64.
   5. Encrypt using token and secret key
   6. Return encrypt token

## JWT -JSON WEBBASE TOKEN

JWT have three components A,B,C
```
eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3VzLmFjY291bnRzLnNjYWxlci5jb20iLCJhdWQiOiJzY2FsZXIiLCJzdWIiOiJzY2FsZXIjdXNlciMyMzEwNjYyIiwic2Vzc2lvbl9pZCI6InNlc3Npb24jYWN0aXZlIzJ6Ump6TnQxbDFYQUZUMnZJM2tpamFJbGJoVSIsImlhdCI6MTc1MTcwMTA0MCwic2Vzc2lvbl9zb3VyY2UiOiJzZWNvbmRhcnkiLCJleHAiOjE3NTQyOTMwNDB9.ugiqplrZF7GAQGWRaVJzM3eAekX5H1gqeCgjs3ZZutc
```

A= eyJhbGciOiJIUzI1NiJ9  -> Header -> Encryption Algo in base64 format
 
Encryption ALgo : {"alg":"HS256"}

Base64Format : eyJhbGciOiJIUzI1NiJ9

B= eyJpc3MiOiJodHRwczovL3VzLmFjY291bnRzLnNjYWxlci5jb20iLCJhdWQiOiJzY2FsZXIiLCJzdWIiOiJzY2FsZXIjdXNlciMyMzEwNjYyIiwic2Vzc2lvbl9pZCI6InNlc3Npb24jYWN0aXZlIzJ6Ump6TnQxbDFYQUZUMnZJM2tpamFJbGJoVSIsImlhdCI6MTc1MTcwMTA0MCwic2Vzc2lvbl9zb3VyY2UiOiJzZWNvbmRhcnkiLCJleHAiOjE3NTQyOTMwNDB9
  -> Payload  -> contain JSON of user details in base64 format

JSON: {
"iss": "https://us.accounts.scaler.com",
"aud": "scaler",
"sub": "scaler#user#2310662",
"session_id": "session#active#2zRjzNt1l1XAFT2vI3kijaIlbhU",
"iat": 1751701040,
"session_source": "secondary",
"exp": 1754293040
}

Base64Format : eyJpc3MiOiJodHRwczovL3VzLmFjY291bnRzLnNjYWxlci5jb20iLCJhdWQiOiJzY2FsZXIiLCJzdWIiOiJzY2FsZXIjdXNlciMyMzEwNjYyIiwic2Vzc2lvbl9pZCI6InNlc3Npb24jYWN0aXZlIzJ6Ump6TnQxbDFYQUZUMnZJM2tpamFJbGJoVSIsImlhdCI6MTc1MTcwMTA0MCwic2Vzc2lvbl9zb3VyY2UiOiJzZWNvbmRhcnkiLCJleHAiOjE3NTQyOTMwNDB9

C = ugiqplrZF7GAQGWRaVJzM3eAekX5H1gqeCgjs3ZZutc  -> Signature -> encrypt(A&B, secret key)

C is not base64 thats why cannot decode


#### Login FLow
      
      secret key
      loin(email, password){
         // verification 
         A = base64encode({"alg":"HS256"})
         B= base64encode(json(userInfo))
         C=encrypt(A&B,secretkey)
         Store toekn in database
         return A + "." + B + "." + C;
      }

#### Verification Flow

      secret key
      verifyToken(token){
         A,B,C = token.split(".");
         decrypt(C,secreyKey)  
         if decypt fails:
            return fail;
         else
            return pass;
      }

Use cases  : Save user token in database to control user actions as well
1. Max two user can log in 
   
   We saved a token and user id in table from there we can check and implement this

2. Token is expired or not
   
   in Database we save expiry time also with all token to validate it. 



### OAUTH2 

Allows an application to access resources hosted on another server on behalf of user

Its uses Access token and refresh token.

OpenIdConnect - Allow for user authentication, user consent and token issuance.

4 component:

1. User
2. Authorization Server
3. Resource Server
4. Application


Case 1:
![GmailOAuth.png](../../resources/GmailOAuth.png)

Case 2: 
![OwnAuthServer.png](../../resources/OwnAuthServer.png)

Case 3:
![ownwebsiteWithOauth.png](../../resources/ownwebsiteWithOauth.png)



### Types of TOKEN
1. **Id connect Token**:  An ID token is an artifact that client applications can use to consume the user identity like name, email and photo to provide customised experience to user.
         Consumers are single page application or mobile application.
         when user login open id connect issues Id Connect token
2. **Access Token** : Access token ca be used to make secure api calls when a client application need to access protected resources on behalf of users.
3. **Refresh Token** :  
### Refresh Token


### Implementation

1. add spring security dependency in pom.xml
2. create user model
3. create Role Model
4. create AuthController
      * login
      * Signup
5. create DTO's
      * LoginDto
      * SignupDto
      * UserDto
6. Create AuthService class
7. Create Exception class
8. create config class having two bean ModelMapper and BCryptPasswordEncoder
9. Create WebSecurity class - To validate all incoming request.
10. Create token model
11. 




1. Sprint security - to add filter layer to validate all incoming request
```java

@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> {
                        try {
                            requests
									.anyRequest().permitAll()
                                  //  .requestMatchers("/", "/auth/signup").permitAll()
                             //   .anyRequest().authenticated()
                                    .and().cors().disable()
                                    .csrf().disable();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
			);
//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
//			.logout((logout) -> logout.permitAll());

		return http.build();
	}
```
2. BCryptPasswordEncoder - To encode and verify password.
```java
Encode: user.setPassword(bCryptPasswordEncoder.encode(signUpDto.getPassword()));
Veify :
        if(!bCryptPasswordEncoder.matches(userDto.getPassword(),userOptional.getPassword())){
        throw new PasswordValidationFail(userPasswordMismatch);
        }
```
3. Token Generation:
4. 




**************************Spring Security**************************
1. Add Spring security dependency
2. once dependency added all request by default will pass from the filter to validate user before going to controller.
3. There are two types of dependency given by spring security framework (SpringBootWebSecurityConfiguration.class).

   1.  Form Based -http.formLogin(withDefaults());
   2.  Http Based - http.httpBasic(withDefaults());
4. Steps to write own security filter
Add @EnableWebSecurity in main class
```properties
@SpringBootApplication
@EnableWebSecurity
```
```java
@Configuration
public class SecurityConfig {
	
	// Security configuration can be added here
		@Bean
		SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
			//To disable statefullness
			http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//					.maximumSessions(1)
//					.maxSessionsPreventsLogin(false)
					);
			//http.formLogin(withDefaults());
			http.httpBasic(withDefaults());
			return http.build();
		}

}
```
5. Making Basic Authentication stateless with spring security
```java
http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
```

Session Creation Policy values and usage

| Policy                    | Meaning                                           | When Session Used                                | Typical Use Case            |
| ------------------------- | ------------------------------------------------- | ------------------------------------------------ |-----------------------------|
| `ALWAYS`                  | Always create session                             | Created even if not needed                       | Legacy web apps             |
| `IF_REQUIRED` *(default)* | Create only when needed                           | Created only if authentication requires it       | Form-based login            |
| `NEVER`                   | Do not create session but use existing if present | Won’t create session, but can read from existing | Manual session Handling     |
| `STATELESS`               | Do NOT create or use any session                  | No session at all                                | JWT / Token-based REST APIs |

6. 🔐 Types of Authentication in Spring

   | Type                                      | Where credentials stored         | Best for            | Pros                      | Cons                                  |
   | ----------------------------------------- | -------------------------------- | ------------------- | ------------------------- | ------------------------------------- |
   | **In-Memory Authentication**              | Hardcoded in Java code           | Quick testing & POC | Very easy to implement    | Not scalable, insecure                |
   | **application.properties Authentication** | In `application.properties` file | Simple apps         | No code change needed     | Password visible in file, not dynamic |
   | **Database Authentication**               | Stored in DB (via User table)    | Real-world apps     | Scalable, dynamic, secure | Needs DB & additional setup           |


6.1.  Application.properties Authentication
```properties
spring.security.user.name=admin
spring.security.user.password=admin123
spring.security.user.roles=ADMIN
```
**▶ When to use:**

✔ Simple standalone apps

✔ Internal demo apps

❌ Username/password visible in file

❌ Only 1 user allowed

❌ No dynamic update

6.2. In-Memory Authentication

🔹 Defined inside Java code (SecurityConfig).

🔹 Used only for testing or temporary security.

▶ When to use:

✔ Small POC

✔ Quick testing

❌ Not for production

```java
@Bean
		UserDetailsService user() {
			UserDetails user = User.withUsername("user")
					.password("{noop}password")
					.roles("USER")
					.build();
			
				UserDetails admin = User.withUsername("admin")
						.password("{noop}password")
						.roles("ADMIN")
						.build();
			return new InMemoryUserDetailsManager(user,admin);
		}
```

6.3. Database Authentication

```java
@Bean
		UserDetailsService user() {
			UserDetails user1 = User.withUsername("user")
					.password("{noop}password")
					.roles("USER")
					.build();
			
				UserDetails admin = User.withUsername("admin")
						.password("{noop}password")
						.roles("ADMIN")
						.build();
				UserDetails cs = User.withUsername("cs")
						.password("{noop}password")
						.roles("CS")
						.build();
				//In-Memory Authentication
			//return new InMemoryUserDetailsManager(user1,admin,cs);
			
			
			//In database Authentication
			JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
			jdbcUserDetailsManager.createUser(user1);
			jdbcUserDetailsManager.createUser(admin);
			jdbcUserDetailsManager.createUser(cs);
			
			return jdbcUserDetailsManager;
		}
```

7. Role Based Authorization - @PreAuthorize()

 add annotation on main class @EnableMethodSecurity
```java
@GetMapping("/test")
	public String hello() {
		return "I am alive!!";
	}
	
	
	@PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
	@GetMapping("/random")
	public String randomNumber(@RequestParam("no") int random) {
		return "I am alive!!" + random;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/userOrAdmin")
	public String anyRole() {
		return "I am user/admin";
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String user() {
		return "Hello User!!";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String admin() {
		return "I am alive!!";
	}
```
8. Disable Spring security from specific url like h2-console(db)
```java
http			
			.csrf(csrf -> csrf.disable()) // disable CSRF for H2 console
	        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())) // allow frames from same origin
	       
			.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/h2-console/**").permitAll()
					.anyRequest().authenticated())
			.formLogin(withDefaults());
```
9. Password Encoding and Hashing
10. JWT  - JSON web Token, instead of sending username and password, used token which is highly customizable


#### Setting workplace for JWT

1. Add dependency in pom.xml
    google jjwt github maven and copy JWT dependency from pom.xml

#### Implementation of JWT

**JWT Utils**  - Containing utility methods for generating( token from Username), parsing(extracting username from Token), and Validating JWTs.

**Auth Token Filter** - When request coming in it passes from series of filters before reaching to controller. Auth Token Filter is a custom filter to intercept the request and do the validation with the help of JWT Utils. If request is valid it will set application context.

**Auth Entry Point JWT** - It provide custom handling of JWT unauthorized request. When an unauthorized request is detected, its logs the error and return JSON response with error message and status code.

**Security Config** - Configures Spring Security filter and rules for the application. Basically configure custom filter and setup how configuration would work like configure session management to stateless


##### JWT UTILS

Getting JWT from Header
Generating Token from Username
Getting Username from JWT Token
Generate Signing key
Validate JWT Token















