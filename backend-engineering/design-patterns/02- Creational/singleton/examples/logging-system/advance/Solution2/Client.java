package designPattern.creational.singleton.loggerDemo.advance.Solution2;

public class Client {
// Hide appender and factory
    static void main(String[] args) {
        Logger log= Logger.getInstance("file");


        log.info("This is an info message from log object");

        Logger log1= Logger.getInstance("file");
        log1.info("This is an info message from log1 object");

        System.out.println(log.toString());
        System.out.println(log1.toString());

        if(log==log1){
            log.info("Both log and log1 objects are same");
        }

        Logger log2= Logger.getInstance("console");
        log2.info("This is an info message on log2 object");
        System.out.println(log2.toString());
        if(log==log2){
            log.info("Both objects are same");
        }

    }
}
