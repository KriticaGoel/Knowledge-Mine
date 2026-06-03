# Factory Solution - File Parser

## Problem Statement

We want to parse different types of files (CSV, Excel, JSON, Flat file) using a File Parser. 
The File Parser should be able to switch between these behaviors at runtime.

All files need to be parsed, but the way they are parsed is different.

Instead of creating objects under if and else, we use the **factory Pattern** to create objects based on the file type.

---

## Core Idea of factory Pattern

The Factory Pattern says:
- define Factory as a separate class
- Factory creates objects based on input parameters (like file type)
- Client code calls Factory to get the object it needs


---
## UML Diagram

---

### Step 1 → FileParser Factory

```java
class FileParserFactory {

    private Map<String, FileParserStrategy> fileParserStrategyMap;
    public FileParserFactory() {
        this.fileParserStrategyMap = Map.of(
            "CSV", new CSVFileParser(),
            "JSON", new JSONFileParser(),
            "Excel", new ExcelFileParser()
        );
    }
    public FileParserStrategy getFileParserStrategy(String type) {
        return fileParserStrategyMap.get(type);
    }
}

```

### Step 3 → Client Code ( Using the Robot)

#### Application
```java
public class FileParserFactoryDesign {

    static void main(String[] args) {
        FileParserFactory fileParserFactory = new FileParserFactory();
        fileParserFactory.getFileParserStrategy("Excel").parse();

    }
}
```

<details> <summary><h3>Why This Is Strategy Pattern</h3></summary>
This design follows Strategy because:

* each behavior is separated into its own interface
* behavior can vary independently
* the Robot class delegates actions to behavior objects
* we can change behavior at runtime using setters
</details>

---

<details> <summary><h3>Advantages</h3></summary>

* Open/Closed Principle: add new behaviors without changing existing code
* Single Responsibility Principle: each behavior class has one job
* Liskov Substitution Principle: any implementation of Talkable, Walkable, or Flyable can be used safely
* Cleaner design: no if-else or switch for behavior selection
</details>

---

<details> <summary><h3>Important Concept</h3></summary>

The Strategy Pattern helps us replace inheritance-heavy designs with composition.
Instead of forcing every robot to inherit all behaviors, we assign only the behaviors it actually supports.

</details>