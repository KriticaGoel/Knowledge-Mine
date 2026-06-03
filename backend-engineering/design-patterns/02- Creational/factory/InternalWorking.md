# ParserFactory Deep Dive (Spring + Factory + Strategy)

This is a common production pattern combining:

- Factory Pattern
- Strategy Pattern
- Dependency Injection
- Spring Bean Lifecycle

Code:

```java
@Component
@RequiredArgsConstructor
class ParserFactory {

    private final List<FileParser> parsers;

    private Map<String,FileParser> parserMap;

    @PostConstruct
    void init(){

        parserMap =
                parsers.stream()
                .collect(Collectors.toMap(
                    FileParser::getType,
                    Function.identity()
                ));
    }

    public FileParser get(String type){

        return parserMap.get(type);
    }
}
```

---

# High Level Flow

```text
Spring starts
      ↓
Finds all FileParser beans
      ↓
Injects List<FileParser>
      ↓
Creates ParserFactory bean
      ↓
Calls @PostConstruct
      ↓
Converts List → Map
      ↓
Runtime request arrives
      ↓
factory.get("CSV")
      ↓
Returns CsvParser
```

---

# Step 1: @Component

```java
@Component
```

Meaning:

> Create object and manage it as Spring bean

Spring internally does:

```java
ParserFactory factory =
        new ParserFactory(...);
```

You never write:

```java
new ParserFactory()
```

Spring handles creation.

---

# Step 2: @RequiredArgsConstructor

```java
@RequiredArgsConstructor
```

Lombok generates constructor automatically.

Generated code:

```java
public ParserFactory(
        List<FileParser> parsers){

    this.parsers=parsers;
}
```

Because:

```java
private final List<FileParser> parsers;
```

is final.

No boilerplate constructor needed.

---

# Important Spring Magic

Question:

Who injects:

```java
List<FileParser>
```

Suppose:

```java
@Component
class CsvParser
        implements FileParser{}
```

```java
@Component
class ExcelParser
        implements FileParser{}
```

```java
@Component
class JsonParser
        implements FileParser{}
```

Spring scans application.

Finds:

```text
CsvParser
ExcelParser
JsonParser
```

All implement:

```java
FileParser
```

Spring automatically injects:

```java
List.of(
    csvParser,
    excelParser,
    jsonParser
)
```

Internally:

```java
new ParserFactory(
      List.of(
          csvParser,
          excelParser,
          jsonParser
      )
)
```

---

# Memory State After Constructor

```text
ParserFactory

parsers
   ↓

[
 CsvParser,
 ExcelParser,
 JsonParser
]

parserMap = null
```

---

# Step 3: @PostConstruct

```java
@PostConstruct
void init()
```

Runs automatically after:

- bean creation
- dependency injection completed

Lifecycle:

```text
Create Bean
     ↓
Inject dependencies
     ↓
@PostConstruct
     ↓
Bean ready
```

Spring automatically calls:

```java
init();
```

You never call it manually.

---

# Step 4: Stream Processing

Code:

```java
parserMap =
        parsers.stream()
        .collect(Collectors.toMap(
                FileParser::getType,
                Function.identity()
        ));
```

Current list:

```java
[
 CsvParser,
 ExcelParser,
 JsonParser
]
```

---

## Part 1

```java
FileParser::getType
```

Creates key.

Example:

```java
CsvParser -> "CSV"

ExcelParser -> "EXCEL"

JsonParser -> "JSON"
```

Keys:

```text
CSV
EXCEL
JSON
```

---

## Part 2

```java
Function.identity()
```

Means:

```java
x -> x
```

Return same object.

Example:

```java
CsvParser → CsvParser

ExcelParser → ExcelParser
```

---

# Final Map

```java
{
   "CSV" : CsvParser,
   "EXCEL" : ExcelParser,
   "JSON" : JsonParser
}
```

Memory becomes:

```text
parserMap

CSV → CsvParser
EXCEL → ExcelParser
JSON → JsonParser
```

---

# Step 5: Runtime Request

Request:

```java
FileParser parser =
        factory.get("CSV");
```

Method:

```java
public FileParser get(String type){

    return parserMap.get(type);
}
```

Equivalent:

```java
return parserMap.get("CSV");
```

Returns:

```java
CsvParser object
```

Then:

```java
parser.parse();
```

CSV parser executes.

---

# Runtime Flow

```text
factory.get("CSV")
        ↓

parserMap.get("CSV")

        ↓

CsvParser

        ↓

parse()
```

---

# Why Better Than If Else

Without Factory:

```java
if(type=="CSV")

else if(type=="EXCEL")

else if(type=="JSON")
```

Adding XML:

```java
else if(type=="XML")
```

Need service modification.

Violates:

Open Closed Principle

---

# Add New Parser Using Factory

Create:

```java
@Component
class XmlParser
        implements FileParser{

    public String getType(){

        return "XML";
    }

    public void parse(){}
}
```

Nothing else changes.

Spring automatically injects:

```java
[
 CsvParser,
 ExcelParser,
 JsonParser,
 XmlParser
]
```

Map becomes:

```java
XML → XmlParser
```

No service modification.

No factory modification.

No if-else.

---

# Design Patterns Used

| Pattern | Where |
|----------|--------|
| Strategy | FileParser implementations |
| Factory | ParserFactory |
| Dependency Injection | Spring constructor injection |
| Open Closed Principle | Add parser without modifying service |

---

# Why Map Instead of If Else

Time Complexity:

If-Else:

```text
CSV → O(1)

JSON → O(3)

XML → O(n)
```

Map:

```text
Map.get()

O(1)
```

Fast lookup.

Cleaner code.

---

# Interview One-Liner

Spring creates and injects all parser implementations.

ParserFactory converts those implementations into a lookup map and returns the correct parser at runtime without using if-else.

---

# Fast Revision

```text
@Component
     ↓
Spring creates bean

@RequiredArgsConstructor
     ↓
Constructor Injection

List<FileParser>
     ↓
Inject all implementations

@PostConstruct
     ↓
Runs after bean creation

Collectors.toMap()
     ↓
List → Map

Function.identity()
     ↓
x → x

get(type)
     ↓
Returns matching parser

Factory + Strategy + DI
```