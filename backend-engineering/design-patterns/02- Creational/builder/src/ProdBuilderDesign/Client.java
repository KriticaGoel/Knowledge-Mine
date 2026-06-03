package designPattern.creational.builder.ProdBuilderDesign;

public class Client {

    static void main(String[] args) {
        Student student =  Student.getBuilder().setName("Kritica").setAge(19).setGrade("A").build();

        student.enroll();
    }
}
