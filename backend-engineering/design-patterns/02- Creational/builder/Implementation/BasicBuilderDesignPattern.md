
Student Class
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
}
```

Student Builder Class
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

Client Code
```java
public class BasicStudentBuilder {

    static void main(String[] args) {
        StudentBuilder sb = new StudentBuilder();
        sb.setName("Kritica");
        sb.setAge(19);
        sb.setGrade("A");

        Student s = new Student(sb);
        System.out.println(s.name);
    }
}
```