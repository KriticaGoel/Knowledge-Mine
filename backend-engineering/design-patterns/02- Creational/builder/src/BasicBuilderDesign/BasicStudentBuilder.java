package designPattern.creational.builder.BasicBuilderDesign;

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
