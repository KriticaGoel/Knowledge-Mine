package designPattern.creational.factory.FileParser;

public class FactoryMethod {
    static void main(String[] args) {

        FileFactory csvFactory = new CsvFileFactory();
        csvFactory.process();

        new PdfFileFactory().process();
    }
}

interface FileParser {
    void parse();
}

class CsvFileParser implements FileParser {
    @Override
    public void parse() {
        System.out.println("Parsing CSV file...");
    }
}

class PdfFileParser implements FileParser {
    @Override
    public void parse() {
        System.out.println("Parsing PDF file...");
    }
}

    abstract class FileFactory{
    abstract FileParser createFileParser();

    public void process(){
        FileParser fileParser = createFileParser();
        fileParser.parse();
    }
}

class CsvFileFactory extends FileFactory {
    @Override
    public FileParser createFileParser() {
        return new CsvFileParser();
    }
}

class PdfFileFactory extends FileFactory {
    @Override
    public FileParser createFileParser() {
        return new PdfFileParser();
    }
}