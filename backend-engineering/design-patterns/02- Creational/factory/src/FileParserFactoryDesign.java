package designPattern.creational.factory.FileParser;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class FileParserFactoryDesign {

    public static void main(String[] args) {
        FileParserFactory fileParserFactory = new FileParserFactory();

        FileType fileType = FileType.EXCEL;
        if (args.length > 0) {
            try {
                fileType = FileType.valueOf(args[0].trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                System.out.println("Unsupported file type: " + args[0]);
                System.out.println("Supported types: CSV, JSON, EXCEL");
                return;
            }
        }

        fileParserFactory.getFileParserStrategy(fileType).parse();
    }
}

enum FileType {
    CSV,
    JSON,
    EXCEL
}

interface FileParserStrategy {
    void parse();
}

class CSVFileParser implements FileParserStrategy {

    @Override
    public void parse() {
        System.out.println("Parsing CSV file...");
    }
}

class JSONFileParser implements FileParserStrategy {

    @Override
    public void parse() {
        System.out.println("Parsing JSON file...");
    }
}

class ExcelFileParser implements FileParserStrategy {

    @Override
    public void parse() {
        System.out.println("Parsing Excel file...");
    }
}

class FileParserFactory {

    private final Map<FileType, FileParserStrategy> fileParserStrategyMap;

    public FileParserFactory() {
        this.fileParserStrategyMap = new EnumMap<>(FileType.class);
        fileParserStrategyMap.put(FileType.CSV, new CSVFileParser());
        fileParserStrategyMap.put(FileType.JSON, new JSONFileParser());
        fileParserStrategyMap.put(FileType.EXCEL, new ExcelFileParser());
    }

    public FileParserStrategy getFileParserStrategy(FileType type) {
        Objects.requireNonNull(type, "type must not be null");

        FileParserStrategy strategy = fileParserStrategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported file type: " + type);
        }
        return strategy;
    }
}
