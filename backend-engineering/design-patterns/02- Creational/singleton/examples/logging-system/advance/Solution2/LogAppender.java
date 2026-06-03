package designPattern.creational.singleton.loggerDemo.advance.Solution2;

//This is strategy design pattern that give flexibility to client to print log in console or file
public interface LogAppender {
    void append(String message);
}
