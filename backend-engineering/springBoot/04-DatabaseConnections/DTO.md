### DTO
- Data transfer object — Design pattern used to transfer the data between software application subsystems
- Decoupling—Separate response from Model. so if we add anything in database then my request object will not impacted
  unless we want it to be impacted.
- Example. if we add model directly in controller, then adding column (create_date) will change my request body. Thats
  why we need a mapper which decouple database table and request body
- Customised the response object. If we dont use DTO then all column present in model will come in a response. which is
  not good sometime like password.

> JSON <- DTO <- Model(Entity)
![DTO Architecture.jpg](..%2F..%2Fresources%2FDTO%20Architecture.jpg)



Step 1: use below dependency to validate the DTO
```xml
<dependency>
<groupId>jakarta.validation</groupId>
<artifactId>jakarta.validation-api</artifactId>
</dependency>
```

Step 2: import below
import jakarta.validation.constraints.Size;

Step 3 use annotation in dto

```java
@NotNull(message = "username cannot be null")
@Size(min=3, max=20,message="username must contain at least 3 character")
private String username;

@NotNull(message = "Password cannot be null")
@Size(min = 5, max = 50, message = "Password must contain at least 5 characters")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]+$",
        message = "Password must contain at least one number, one capital letter, and one special character")

//    @Pattern(regexp = "^(?=(?:.*[0-9]){2,})(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]+$",
//            message = "Password must contain at least two numbers and one special character")

private String password;
@JsonProperty("email") // this is used to map the json property to the field
@Email(message = "Email is not valid")
@NotNull(message = "Email cannot be null")
@Size(min=3, max=50, message="Email must contain at least 3 character")
private String email;

```

Create separate DTO for request and response


### Model Mapper
Step 1 : Dependency
```xml
<dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.0.0</version>
        </dependency>
```

Step 2 : Create an object that can handle by spring framework
```java
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}


```

Step 3: Calling
```Java
 private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public AuthServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper    = modelMapper;
    }
    @Override
    public User signup(SignUpRequestDto name) {
        
        userRepository.save(modelMapper.map(name,User.class));
        return null;
    }
```



#### ModelMapper

1. add configuration in pom.xml
2. Create a config class having Model mapper bean so that object can be handled by spring framework and we dont need to create objects t all places

```java

@Configuration
public class AppConfig {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
```

3. in service layer map the entity to DTO using model mapping just to reduce line of codes.

```java
public CategoryResponse getCategories() {
  long count = categoryRepository.count();
  if (count == 0)
    throw new APIException("No categories found");
  // Database - Entity
  List<Category> categories = categoryRepository.findAll();
  //Response Model
  CategoryResponse categoryResponse = new CategoryResponse();

  //convert entity to response model
  List<CategoryDTO> categoryDTOList = categories.stream()
          .map(category -> modelMapper.map(category, CategoryDTO.class))
          .toList();
  //Setting response model
  categoryResponse.setContent(categoryDTOList);
  return categoryResponse;
}
```

4. Create Category using DTO

```java
 public String createCategory(CategoryDTO category) {

  Category categoryFound = categoryRepository.findByCategoryName(category.getName());
  if (categoryFound != null) {
    throw new APIException("Category already exists");
  }
  Category modelCategory = modelMapper.map(category, Category.class);
  categoryRepository.save(modelCategory);
  return "Category created successfully";
}
```



#### ModelMapper

1. add configuration in pom.xml
2. Create a config class having Model mapper bean so that object can be handled by spring framework and we dont need to create objects t all places

```java

@Configuration
public class AppConfig {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
```

3. in service layer map the entity to DTO using model mapping just to reduce line of codes.

```java
public CategoryResponse getCategories() {
  long count = categoryRepository.count();
  if (count == 0)
    throw new APIException("No categories found");
  // Database - Entity
  List<Category> categories = categoryRepository.findAll();
  //Response Model
  CategoryResponse categoryResponse = new CategoryResponse();

  //convert entity to response model
  List<CategoryDTO> categoryDTOList = categories.stream()
          .map(category -> modelMapper.map(category, CategoryDTO.class))
          .toList();
  //Setting response model
  categoryResponse.setContent(categoryDTOList);
  return categoryResponse;
}
```

4. Create Category using DTO

```java
 public String createCategory(CategoryDTO category) {

  Category categoryFound = categoryRepository.findByCategoryName(category.getName());
  if (categoryFound != null) {
    throw new APIException("Category already exists");
  }
  Category modelCategory = modelMapper.map(category, Category.class);
  categoryRepository.save(modelCategory);
  return "Category created successfully";
}
```