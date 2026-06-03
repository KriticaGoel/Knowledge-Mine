package designPattern.creational.builder;

public class ChainConstructor {

}

class Student1{
    int id;
    String name;
    String address;
    String grade;
    int age;
    String phoneNumber;
    String email;
    String university;

    Student1(int id, String name, String grade, int age,String address, String phoneNumber, String email, String university) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.university = university;
        this.age = age;
        this.grade = grade;
    }

    Student1(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    Student1(int id){
        this.id = id;
    }
    Student1(int id, String name, String grade, int age,String address){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.age = age;
        this.address = address;
    }
    Student1(int id, String name, String grade, int age,String address, String phoneNumber){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
