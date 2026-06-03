package designPattern.creational.singleton.loggerDemo.basic;

public class LoggerDemo {

    // 1. Instance is created at class loading time (eager initialization)
    private static final  LoggerDemo INSTANCE  = new LoggerDemo();
    // 2. Private constructor prevents external instantiation
    private LoggerDemo() {
        System.out.println("LoggerDemo instance created.");
    }
    // 3. Global access point
    public static LoggerDemo getInstance() {
        return INSTANCE;
    }

    // logging method
    public void info(String message) {
        System.out.println("[Info] " + message);
    }
    public void debug(String message) {
        System.out.println("[Debug] " + message);
    }
    public void error(String message) {
        System.out.println("[Error] " + message);
    }
    public void warn(String message) {
        System.out.println("[Warn] " + message);
    }

    public String toString(){
        return "LoggerDemo instance: " + this.hashCode();
    }

}