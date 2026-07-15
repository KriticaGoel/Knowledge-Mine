there are two ways to do this

1. wsdl to java
2. java to wsdl

### wsdl to java

1. add dependencies in pom.xml wsdl4
2. to create wsdl we need to write xsd file
3. Generate biniding classes from xsd.
4. Add plugin in pom.xml xjc



### Step 1: Set Up the Spring Boot Project
You can set up a Spring Boot project in several ways, such as using Spring Initializr or creating the project manually.

#### Option 1: Using Spring Initializr

Go to Spring Initializr.
Choose the following:
Project: Maven Project
Language: Java
Spring Boot version: Choose the latest stable version.
Group: com.example
Artifact: soap-api
Dependencies: Spring Web, Spring Boot DevTools, Spring Web Services
Click on Generate, download the project, and unzip it.
#### Option 2: Manually

Create a pom.xml file with the necessary dependencies.
```xml

<dependencies>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web-services</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.oxm</groupId>
<artifactId>spring-oxm</artifactId>
</dependency>
<dependency>
<groupId>javax.xml.bind</groupId>
<artifactId>jaxb-api</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-logging</artifactId>
</dependency>
</dependencies>

```
2. Create a src/main/resources/application.properties file for configuration (optional):
properties
Copy code
server.port=8080

### Step 2: Create the SOAP Web Service Endpoint
Now, let's create a simple SOAP service. We'll define a HelloService that returns a "Hello, [name]" message.

#### 1. Create the Service Endpoint Class
```java

@Endpoint
public class HelloServiceEndpoint {

    private static final String NAMESPACE_URI = "http://localhost/soapapi/generated";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HelloRequest")
    @ResponsePayload
    public HelloResponse sayHello(@RequestPayload HelloRequest request) {
        HelloResponse response = new HelloResponse();
        response.setMessage("Hello, " + request.getName());
        return response;
    }
}
```
#### 2.Create Request and Response Classes (JAXB)
```java
@XmlRootElement
public class HelloRequest {

    private String name;

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
@XmlRootElement
public class HelloResponse {

    private String message;

    @XmlElement
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
```

### Step 3: Create the SOAP Configuration
You’ll need to configure Spring Web Services to handle the SOAP requests and map the requests to the endpoint.

```java
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ccom.kritica.samplesoapwebservice.api.generated");
        return marshaller;
    }

    @Bean
    public HelloServiceEndpoint helloService() {
        return new HelloServiceEndpoint();
    }
}

```


### Step 5: Run the Application

```java
@SpringBootApplication
public class SampleSoapWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSoapWebserviceApplication.class, args);
    }

}
```