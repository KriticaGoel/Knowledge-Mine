package designPattern.creational.prototype.PrototypeRegistry;

public class RegistryLoad {

    public static StudentRegistry load(){
        StudentRegistry registry = new StudentRegistry();

        Student eng = new Student(1,"Rahul","Engineering");
        registry.register(Field.eng.name(), eng);
        Student med = eng.clone();
        med.id = 2;
        med.name = "Medic";
        med.field ="Medical";
        registry.register(Field.med.name(),med);
        Student mba = med.clone();
        mba.id = 3;
        mba.name = "Kritica";
        mba.field ="MBA";
        registry.register(Field.mba.name(),mba);

        return registry;
    }
}
