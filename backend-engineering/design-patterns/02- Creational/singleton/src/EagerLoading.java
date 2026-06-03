
public class EagerLoading {
    static void main(String[] args) {
        EagerSingleton s1 = EagerSingleton.getInstance();
        EagerSingleton s2 = EagerSingleton.getInstance();

        if(s1==s2)
            System.out.println("EagerSingleton instances are the same");
        else
            System.out.println("EagerSingleton instances are not the same");

    }
}

class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        // Private constructor to prevent instantiation
        System.out.println("EagerSingleton instance created");
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}