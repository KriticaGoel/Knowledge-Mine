public class DoubleCheckedLocking {
    static void main(String[] args) {
        DoubleChecking s1 = DoubleChecking.getInstance();
        DoubleChecking s2 = DoubleChecking.getInstance();

        if(s1==s2)
            System.out.println("DoubleChecking instances are the same");
        else
            System.out.println("DoubleChecking instances are not the same");

    }
}

class DoubleChecking{
    public static DoubleChecking instance;

    private DoubleChecking(){
        System.out.println("DoubleChecking instance created");
    }
    public static DoubleChecking getInstance(){
        if(instance==null){
            synchronized (DoubleCheckedLocking.class){
                if(instance==null){
                    instance = new DoubleChecking();
                }
            }
        }
        return instance;
    }
}
