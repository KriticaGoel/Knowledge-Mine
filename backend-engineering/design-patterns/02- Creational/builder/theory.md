Class have 10 fields. and we need to set value in the class

## Approach 1 : Constructor Overloading
Create constructors which cause Constructor Overloading or chaining of constructors
```Java
class Student{
    int id;
    String name;
    String address;
    String grade;
    int age;
    String phoneNumber;
    String email;
    String university;

    Student(int id, String name, String grade, int age,String address, String phoneNumber, String email, String university) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.university = university;
        this.age = age;
        this.grade = grade;
    }

    Student(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    Student(int id){
        this.id = id;
    }
    Student(int id, String name, String grade, int age,String address){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.age = age;
        this.address = address;
    }
    Student(int id, String name, String grade, int age,String address, String phoneNumber){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
```

### Problem:
1. Too Many constructors, which is hard to maintain and understand.
2. Compile time polymorphism is not possible, if we want to create object with 5 fields then we need to create constructor with 5 fields and if we want to create object with 6 fields then we need to create constructor with 6 fields and so on.
3. Error Prone, by mistake we can pass wrong parameters in constructor and it will not give compile time error but it will give run time error.
4. Inconsistent state problem, if we forget to set any field which is mandate to set then it will be in inconsistent state ( give run time error)

## Approach 2. Use setter methods which cause mutability.
```java
class Students {
    int id;
    String name;
    String address;
    String grade;
    int age;
    String phoneNumber;
    String email;
    String university;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
```
```java
public class SetterMethod {

    static void main(String[] args) {
        Students s= new Students();
        s.setName("John");
        s.setName("abc");
    }
}
```

### Problem:
1. Object is always created first without having validation check.
2. If we forget to set any field which is mandate to set then it will be in inconsistent state ( give run time error)


## Approach 3: object will create before validation.
```java
public class SetterMethod {

    static void main(String[] args) {
        Students s= new Students();
        s.setName("John");
        System.out.println(s.getGrade().toUpperCase());
    }
}
```
### Problem:
1. Object created and then validation failed which cause run time error.
2. Waste of memory, because object created and then validation failed which cause run time error.

## Approach 4: Use Map in constructor to validate and set values in the class.
```java
public class UseMap {

    static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("Name", "John");
        map.put("Age", "20");

        Student s = new Student(map);
        System.out.println(s.name);
    }

}

class Student{
    int id;
    String name;
    String address;
    String grade;
    int age;
    String phoneNumber;
    String email;
    String university;

    public Student(Map<String,Object> map){
        this.id = (int) map.getOrDefault("id", 0);
        this.name = (String) map.getOrDefault("name", "Unknown");
        this.address = (String) map.getOrDefault("address", "Unknown");
        this.grade = (String) map.getOrDefault("grade", "Unknown");
        this.age = (int) map.getOrDefault("age", 0);
        this.phoneNumber = (String) map.getOrDefault("phoneNumber", "Unknown");
        this.email = (String) map.getOrDefault("email", "Unknown");
        this.university = (String) map.getOrDefault("university", "Unknown");
        //validation before creating object

        if (age<18)
            throw  new IllegalArgumentException("Age must be 18 years old");
    }
}
```

### Problem:
1. No compile time checks
2. Key value is error-prone like name != Name. 
3. Run time error, age is integer, but we're passing string in map.

## Builder Design Pattern:
Create a class having getter and setter and pass the class to the constructor of the main class and set the values in the main class using the getter and setter of the class which we passed in the constructor.

Step 1 : Create a class with all fields
```java
public class Student {

    int id;
    String name;
    String address;
    String grade;
    int age;
    String phoneNumber;
    String email;
    String university;
}
```

Step 2: Create a builder class which will have all fields of above class with setter and getter methods

```java
public class StudentBuilder {
//Fields are same as student class with getter and setter
        int id;
        String name;
        String address;
        String grade;
        int age;
        String phoneNumber;
        String email;
        String university;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}

```

Step 3: Create a constructor in main class and pass builder class object which validate the data ans set value of main class

```java
public Student(StudentBuilder sb){
        //validation
        if(sb.age<18)
            throw new IllegalArgumentException("Age must be 18 years old");

        this.id=sb.id;
        this.name=sb.name;
        this.address=sb.address;
        this.grade=sb.grade;
        this.age=sb.age;
        this.phoneNumber=sb.phoneNumber;
        this.email=sb.email;
        this.university=sb.university;
    }
```

Step 4. Create an object of builder class in client code and call main call constructor by passing builder class object and set values in builder class object using setter methods of builder class.

```java
static void main(String[] args) {
        StudentBuilder sb = new StudentBuilder();
        sb.setName("Kritica");
        sb.setAge(19);
        sb.setGrade("A");

        Student s = new Student(sb);
        System.out.println(s.name);
    }
```

## Production Ready code

1. Create a class with all fields 
```java
public class Student {
    int id;
    String name;
    String address;
    String grade;
    int age;
    String phoneNumber;
    String email;
    String university;
    }
```
2. Create an inner static class called Builder having all setters
```java
public static class StudentBuilder {
        //Fields are same as student class with getter and setter
        int id;
        String name;
        String address;
        String grade;
        int age;
        String phoneNumber;
        String email;
        String university;

        public StudentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public StudentBuilder setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder setUniversity(String university) {
            this.university = university;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
```
3. Create a private Constructor in main class and pass builder class object

4. Add Validations in constructor and set values in main class using builder class object
```java
private Student(StudentBuilder sb){
        //validation
        if(sb.age<18)
            throw new IllegalArgumentException("Age must be 18 years old");

        this.id=sb.id;
        this.name=sb.name;
        this.address=sb.address;
        this.grade=sb.grade;
        this.age=sb.age;
        this.phoneNumber=sb.phoneNumber;
        this.email=sb.email;
        this.university=sb.university;
    }
```
5. Create an object of builder class in main class using static method
```java
public static StudentBuilder getBuilder(){
        return new StudentBuilder();
    }
```
6. setter method of builder class return build class object instead of void
```java
 public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }
```
7. add build method in builder which return main class object and use constructor to set values
```java 
 public Student build(){
            return new Student(this);
        }
```
8. Client code
```java 
Student student =  Student.getBuilder().setName("Kritica").setAge(19).setGrade("A").build();
 
```

### Code
```java
public class Student {
    int id;
    String name;
    String address;
    String grade;
    int age;
    String phoneNumber;
    String email;
    String university;

    public static StudentBuilder getBuilder(){
        return new StudentBuilder();
    }

    private Student(StudentBuilder sb){
        //validation
        if(sb.age<18)
            throw new IllegalArgumentException("Age must be 18 years old");

        this.id=sb.id;
        this.name=sb.name;
        this.address=sb.address;
        this.grade=sb.grade;
        this.age=sb.age;
        this.phoneNumber=sb.phoneNumber;
        this.email=sb.email;
        this.university=sb.university;
    }

    public void enroll(){
        System.out.println("Student "+name+" enrolled successfully");
    }

    public static class StudentBuilder {
        //Fields are same as student class with getter and setter
        int id;
        String name;
        String address;
        String grade;
        int age;
        String phoneNumber;
        String email;
        String university;

        public StudentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public StudentBuilder setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder setUniversity(String university) {
            this.university = university;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
}
```
Client code
```java 
public class Client {

    static void main(String[] args) {
        Student student =  Student.getBuilder().setName("Kritica").setAge(19).setGrade("A").build();

        student.enroll();
    }
}

```

## Builder Design Pattern with Director:
Its useful when:
* Object creation has many optional fields
* Construction happens step by step
* You want to separate object construction from representation
* The same construction process can create different representations

### Think
Builder = How to build

Director = In what order to build

Product = Final object
