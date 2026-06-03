package designPattern.creational.prototype.onlyPrototype;

public class Student implements Prototype {

    int id;
    String name;
    String address;
    int age;
    String university;

    public Student(int id, String name, String address, int age, String University) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.university = University;
    }

    //Deep Copy
    public Student(Student s){
        this.id=s.id;
        this.name=s.name;
        this.address=s.address;
        this.age=s.age;
        this.university=s.university;
    }


    @Override
    public Student clone() {
        return new Student(this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age + '\'' +
                ", University='" + university +
                '}';
    }
}
