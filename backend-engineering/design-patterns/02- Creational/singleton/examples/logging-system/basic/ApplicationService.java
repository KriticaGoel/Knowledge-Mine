package designPattern.creational.singleton.loggerDemo.basic;


public class ApplicationService{

    static void main(String[] args) {
        LoggerDemo logger1 = LoggerDemo.getInstance();
        LoggerDemo logger2 = LoggerDemo.getInstance();

        logger1.info("This is an info message.");
        logger2.info("Processing request");
        logger1.debug("This is a debug message.");
        logger1.error("This is an error message.");
        logger1.warn("This is a warning message.");

        System.out.println(logger1.toString());
        System.out.println(logger2.toString());

        // Verify that both logger1 and logger2 are the same instance
        System.out.println("logger1 and logger2 are the same instance: " + (logger1 == logger2));
    }
}