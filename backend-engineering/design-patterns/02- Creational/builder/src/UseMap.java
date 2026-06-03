package designPattern.creational.builder;

import java.util.HashMap;
import java.util.Map;

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
        //validation

        if (age<18)
            throw  new IllegalArgumentException("Age must be 18 years old");
    }
}
