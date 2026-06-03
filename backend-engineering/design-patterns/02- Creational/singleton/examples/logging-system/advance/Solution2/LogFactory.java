package designPattern.creational.singleton.loggerDemo.advance.Solution2;

public interface LogFactory {
    LogAppender getAppender(String type);
}
