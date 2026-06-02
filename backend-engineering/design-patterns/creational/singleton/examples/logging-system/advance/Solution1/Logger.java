package designPattern.creational.singleton.loggerDemo.advance.Solution1;

public class Logger {

    public static final Logger INSTANCE = new Logger();

    private LogAppender logAppender;

    private Logger(){

    }

    public static Logger getInstance(){
        return INSTANCE;
    }

    public void setAppender(LogAppender logAppender) {
        this.logAppender= logAppender;
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
