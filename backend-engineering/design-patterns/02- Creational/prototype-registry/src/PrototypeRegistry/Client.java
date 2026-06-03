package designPattern.creational.prototype.PrototypeRegistry;

public class Client {

    static void main(String[] args) {
        StudentRegistry registry = RegistryLoad.load();

        Student eng1=registry.get(Field.eng.name());
        eng1.name="Eng1";
        System.out.println(eng1.toString());
        Student med1=registry.get(Field.med.name());
        med1.name="Med1";
        System.out.println(med1.toString());
    }
}
