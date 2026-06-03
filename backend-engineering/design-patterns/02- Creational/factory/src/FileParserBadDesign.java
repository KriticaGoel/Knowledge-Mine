package designPattern.creational.factory.FileParser;

import java.util.Scanner;

public class FileParserBadDesign {

    static void main(String[] args) {
        System.out.println("Enter the file name: ");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();

        if(fileName.endsWith(".txt")){
            new FlatFileParserIn().parse();
        }else if (fileName.endsWith(".csv")){
            new CSVParserIn().parse();
        }else if (fileName.endsWith(".json")){
            new JSONParserIn().parse();
        }else if (fileName.endsWith(".xlxm")){
            new ExcelParserIn().parse();
        }


    }
}

interface FileParserIn {
    void parse();
}

class CSVParserIn implements FileParserIn {
    @Override
    public void parse() {
        System.out.println("CSV Parsing");
    }
}

class ExcelParserIn implements FileParserIn {
    @Override
    public void parse() {
        System.out.println("Excel Parsing");
    }
}

class JSONParserIn implements FileParserIn {
    @Override
    public void parse() {
        System.out.println("JSON Parsing");
    }
}

class FlatFileParserIn implements FileParserIn {
    @Override
    public void parse() {
        System.out.println("Flat Parsing");
    }
}




