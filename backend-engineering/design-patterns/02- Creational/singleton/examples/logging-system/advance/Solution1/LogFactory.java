package designPattern.creational.singleton.loggerDemo.advance.Solution1;

public interface LogFactory {
    LogAppender getAppender(String type);
}
