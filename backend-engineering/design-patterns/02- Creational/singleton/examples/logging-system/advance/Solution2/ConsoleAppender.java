package designPattern.creational.singleton.loggerDemo.advance.Solution2;

public class ConsoleAppender implements LogAppender {

    @Override
    public void append(String message) {
        System.out.println("Print message on console "+message);
    }
}
