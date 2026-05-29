package designPattern.creational.singleton;

public class SynchronisedSingleton {

    static void main(String[] args) {
        SyncSingleton s1 = SyncSingleton.getInstance();
        SyncSingleton s2 = SyncSingleton.getInstance();

        if(s1==s2)
            System.out.println("SyncSingleton instances are the same");
        else
            System.out.println("SyncSingleton instances are not the same");

    }
}

class SyncSingleton{

    public static SyncSingleton instance;

    private SyncSingleton(){
        System.out.println("SyncSingleton instance created");
    }
    public static synchronized  SyncSingleton getInstance(){
        if(instance==null)
            instance=new SyncSingleton();
        return instance;
    }

}
