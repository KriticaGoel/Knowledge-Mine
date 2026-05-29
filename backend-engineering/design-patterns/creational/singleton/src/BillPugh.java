package designPattern.creational.singleton;

public class BillPugh
{
    static void main(String[] args) {
        BillPughSignleton s1 = BillPughSignleton.getInstance();
        BillPughSignleton s2 = BillPughSignleton.getInstance();

        if(s1==s2)
            System.out.println("BillPughSignleton instances are the same");
        else
            System.out.println("BillPughSignleton instances are not the same");

    }
}

class BillPughSignleton{

    private BillPughSignleton(){
        System.out.println("BillPughSignleton constructor called");
    }

    private static class Helper{
        public static final BillPughSignleton instance=new BillPughSignleton();
    }

    public static BillPughSignleton getInstance(){
        return Helper.instance;
    }

}
