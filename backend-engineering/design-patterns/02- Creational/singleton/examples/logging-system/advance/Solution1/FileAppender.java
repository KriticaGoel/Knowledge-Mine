package designPattern.creational.singleton.loggerDemo.advance.Solution1;

public class FileAppender implements LogAppender {
    private String fileName;

    public FileAppender(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void append(String message) {
        System.out.printf("Print log in file [%s] %s \n",fileName,message);
    }
}
