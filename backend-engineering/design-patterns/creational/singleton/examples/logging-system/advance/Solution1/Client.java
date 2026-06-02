package designPattern.creational.singleton.loggerDemo.advance.Solution1;

public class Client {

    static void main(String[] args) {
        Logger log= Logger.getInstance();
        String[] fileNames={"app.log","error.log"};
        LogFactory factory = new LogFactoryImpl(fileNames);
        // we are sending multiple files still LogFactory can create one object of FileAppender so logs can be written in one log file.
        LogAppender appender= factory.getAppender("file");
        log.setAppender(appender);
        log.info("This is an info message");

        Logger log1= Logger.getInstance();
        log1.setAppender(appender);
        log1.info("This is an info message");

        System.out.println(log.toString());
        System.out.println(log1.toString());

        if(log==log1){
            log.info("Both objects are same");
        }

        Logger log2= Logger.getInstance();
        LogAppender appenderConsole= factory.getAppender("console");
        log2.setAppender(appenderConsole);
        log2.info("This is an info message on log2 object");
        System.out.println(log2.toString());
        if(log==log2){
            log.info("Both objects are same");
        }

    }
}
