package designPattern.creational.builder.BasicBuilderDesign;

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
