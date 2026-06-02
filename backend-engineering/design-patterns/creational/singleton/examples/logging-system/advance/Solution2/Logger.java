package designPattern.creational.singleton.loggerDemo.advance.Solution2;

public class Logger {

    public static final Logger INSTANCE = new Logger();

    private static LogAppender logAppender;

    private Logger(){

    }

    public static Logger getInstance(String type){
        //Mixing Responsibility
        //1. Logger Object return
        //2. Appender object create

        LogFactory factory = new LogFactoryImpl();
        logAppender= factory.getAppender(type);
        return INSTANCE;
    }


    // logging method
    public void info(String message) {
        logAppender.append("[Info] " + message);
    }
    public void debug(String message) {
        logAppender.append("[Debug] " + message);
    }
    public void error(String message) {
        logAppender.append("[Error] " + message);
    }
    public void warn(String message) {
        logAppender.append("[Warn] " + message);
    }

    public String toString(){
        return "LoggerDemo instance: " + this.hashCode();
    }


}
