package designPattern.creational.builder.ProdBuilderDesign;

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
