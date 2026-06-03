package designPattern.creational.prototype.PrototypeRegistry;

public class Student implements Prototype<Student> {

    int id;
    String name;
    String field;

    Student(int id, String name, String field) {
        this.id = id;
        this.name = name;
        this.field = field;
    }

    Student(Student s){
        this.id = s.id;
        this.name = s.name;
        this.field = s.field;
    }

    @Override
    public Student clone(){
        return new Student(this);
    }

    @Override
    public String toString(){
        return "Student [id=" + id + ", name=" + name + ", field=" + field + "]";
    }

}
