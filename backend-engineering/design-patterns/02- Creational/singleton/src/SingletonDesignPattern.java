public class SingletonDesignPattern {

    static void main(String[] args) {
        Singleton s1= Singleton.getInstance();
        Singleton s2= Singleton.getInstance();

        if(s1==s2)
            System.out.println("Both are same instance");
        else
            System.out.println("Both are different instance");
    }
}

class Singleton{

    static Singleton instance;

    private Singleton(){
        System.out.println("Singleton instance created");
    }
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}