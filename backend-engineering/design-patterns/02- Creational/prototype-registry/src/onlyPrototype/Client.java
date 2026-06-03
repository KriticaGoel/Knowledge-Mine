package designPattern.creational.prototype.onlyPrototype;

public class Client {

    static void main(String[] args) {
        Student s1= new Student(1,"Kritica","India",18,"Student");
        Student s2=s1.clone();
        s2.name="ABC";
        System.out.println(s1.toString());
        System.out.println(s2.toString());

//        Engineering Student
        Student eng = s1.clone();
        eng.name="Engineering Student";
        eng.university = "Engineering";
        System.out.println(eng.toString());
//        Medical Student
        Student medical = s1.clone();
        medical.name="Medical Student";
        medical.university = "Medical";
        System.out.println(medical.toString());
//        MBA Student
        Student mba = s1.clone();
        mba.name="MBA Student";
        mba.university = "MBA";
        System.out.println(mba.toString());
//        School Student
    }
}
