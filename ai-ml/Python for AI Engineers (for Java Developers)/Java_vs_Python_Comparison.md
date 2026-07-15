# Java vs Python - Side by Side Comparison

This guide compares Java and Python with key programming concepts presented in parallel columns.

---

## 1. Variables

| Java | Python |
|------|--------|
| `String name = "Kritica";` | `name = "Kritica"` |
| `int age = 30;` | `age = 30` |
| `double salary = 1000.5;` | `salary = 1000.5` |
| **Explicit type declaration** | **No type declarations** |
| Types must be declared upfront | Python figures them out automatically |

---

## 2. Data Types

| Java | Python |
|------|--------|
| `String name = "Kritica";` | `name = "AI"` |
| `int age = 30;` | `age = 20` |
| `double price = 99.5;` | `price = 99.5` |
| `boolean active = true;` | `active = True` |
| **Static Typing** | **Dynamic Typing** |
| Type is fixed at compile time | Type can change at runtime |

---

## 3. Declaring Multiple Variables

| Java | Python |
|------|--------|
| `int x = 5, y = 10, z = 15;` | `x, y, z = 5, 10, 15` |
| One type, multiple declarations | Multiple assignments in one line |

---

## 4. Constants

| Java | Python |
|------|--------|
| `final int MAX_AGE = 100;` | `MAX_AGE = 100` |
| `static final String APP_NAME = "MyApp";` | `APP_NAME = "MyApp"` (by convention) |
| Enforced immutability with `final` | Convention: use UPPER_CASE naming |

---

## 5. Comments

| Java | Python |
|------|--------|
| `// Single line comment` | `# Single line comment` |
| `/* Multi-line comment */` | `''' Multi-line string/docstring '''` |
| `/** JavaDoc */` | `""" DocString """` |

---

## 6. String Basics

| Java | Python |
|------|--------|
| `String str = "Hello";` | `str = "Hello"` |
| `str.length();` | `len(str)` |
| `str.charAt(0);` | `str[0]` |
| `str.substring(0, 2);` | `str[0:2]` |
| `str.toUpperCase();` | `str.upper()` |
| `str.toLowerCase();` | `str.lower()` |
| String concatenation: `"Hello" + name` | `"Hello" + name` or f-strings: `f"Hello {name}"` |

---

## 7. Collections - List/Array

| Java | Python |
|------|--------|
| `int[] numbers = {1, 2, 3};` | `numbers = [1, 2, 3]` |
| `List<Integer> list = new ArrayList<>();` | `list = [1, 2, 3]` |
| `list.add(4);` | `list.append(4)` |
| `list.get(0);` | `list[0]` |
| `list.size();` | `len(list)` |
| `list.remove(0);` | `list.pop(0)` or `del list[0]` |
| Fixed size (for arrays) | Dynamic size |
| Type-safe: specify element type | No type restriction |

---

## 8. Collections - Dictionary/Map

| Java | Python |
|------|--------|
| `Map<String, Integer> map = new HashMap<>();` | `map = {}` |
| `map.put("age", 25);` | `map["age"] = 25` |
| `map.get("age");` | `map["age"]` or `map.get("age")` |
| `map.containsKey("age");` | `"age" in map` |
| `map.remove("age");` | `del map["age"]` |
| `map.size();` | `len(map)` |

---

## 9. If-Else Statements

| Java | Python |
|------|--------|
| `if (age > 18) { }` | `if age > 18:` |
| `else if (age == 18) { }` | `elif age == 18:` |
| `else { }` | `else:` |
| Braces `{}` required | **Indentation** required (no braces) |
| Parentheses optional in conditions | Parentheses optional in conditions |

---

## 10. Loops - For Loop

| Java | Python |
|------|--------|
| `for (int i = 0; i < 5; i++) { }` | `for i in range(5):` |
| `for (int num : list) { }` | `for num in list:` |
| Traditional C-style loop | Iterator-based by default |

---

## 11. Loops - While Loop

| Java | Python |
|------|--------|
| `while (count < 10) { count++; }` | `while count < 10: count += 1` |
| `do { } while (condition);` | No do-while loop |
| Braces required | Indentation required |

---

## 12. Functions/Methods

| Java | Python |
|------|--------|
| `public int add(int a, int b) { return a + b; }` | `def add(a, b): return a + b` |
| Must declare return type | Return type inferred |
| Must be inside a class | Can be standalone |
| `static` for class methods | No `static` keyword (use `@staticmethod`) |

---

## 13. Classes and Objects - Visibility Modifiers

| Java | Python |
|------|--------|
| `public class Person { }` | `class Person:` |
| `public Person(String name) { }` | `def __init__(self, name):` |
| `this.name = name;` | `self.name = name` |
| **Public/Private Modifiers** | **Convention-based (not enforced)** |
| `public int age;` | `self.age = 30` (public by default) |
| `private int salary;` | `self._salary = 50000` (single underscore = protected) |
| `private final int id;` | `self.__id = 123` (double underscore = name mangling) |
| Enforced by compiler | Violations are not prevented |
| Getter: `public int getAge() { return age; }` | `@property def age(self): return self._age` |

**Python Visibility Explanation:**
- **No prefix** (`self.variable`): Public - can be accessed anywhere
- **Single underscore** (`self._variable`): Protected - meant for internal use (convention only)
- **Double underscore** (`self.__variable`): Private with name mangling - harder to access from outside (but not truly private)
- **Double underscore both sides** (`__variable__`): Special/magic methods like `__init__`, `__str__` - NOT private!

---

## 14. Inheritance

| Java | Python |
|------|--------|
| `public class Dog extends Animal { }` | `class Dog(Animal):` |
| `super();` | `super().__init__()` |
| Single inheritance | Multiple inheritance supported |

---

## 15. Exception Handling

| Java | Python |
|------|--------|
| `try { } catch (Exception e) { } finally { }` | `try: except: else: finally:` |
| Must catch checked exceptions | All exceptions optional to catch |
| `throw new Exception();` | `raise Exception()` |
| Specific exception types required | Can raise any object (though not recommended) |

---

## 16. Null/None

| Java | Python |
|------|--------|
| `String value = null;` | `value = None` |
| `if (value == null)` | `if value is None:` |
| `NullPointerException` | `None` is a valid value |

---

## 17. Type Casting

| Java | Python |
|------|--------|
| `int num = (int) 5.5;` | `num = int(5.5)` |
| `String text = (String) obj;` | `text = str(obj)` |
| Explicit casting required | Mostly automatic, explicit when needed |

---

## 18. File I/O - Reading

| Java | Python |
|------|--------|
| `BufferedReader reader = new BufferedReader(new FileReader("file.txt"));` | `with open("file.txt") as file:` |
| `String line = reader.readLine();` | `line = file.read()` |
| Must handle `IOException` | Built-in file handling |

---

## 19. Package/Module Management

| Java | Python |
|------|--------|
| `import java.util.ArrayList;` | `from collections import deque` (list is built-in) |
| Packages organized in directories | Modules are .py files |
| JAR files for libraries | pip for package management |

---

## 20. Method Overloading

| Java | Python |
|------|--------|
| `void print(int x) { }` | No method overloading |
| `void print(String x) { }` | Use `*args`, `**kwargs`, or default parameters |
| Same method name, different signatures | Single method with flexible parameters |

---

## 21. Reading JSON

| Java | Python |
|------|--------|
| `import com.google.gson.*;` | `import json` |
| `Gson gson = new Gson();` | `data = json.loads(json_string)` |
| `User user = gson.fromJson(jsonString, User.class);` | `with open("file.json") as f: data = json.load(f)` |
| `String json = gson.toJson(user);` | `json.dumps(data)` |
| Requires external library (Gson, Jackson) | Built-in `json` module |
| Strongly typed objects | Flexible dict structure |

---

## 22. Calling APIs

| Java | Python |
|------|--------|
| `HttpClient client = HttpClient.newHttpClient();` | `import requests` |
| `HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.example.com")).GET().build();` | `response = requests.get("https://api.example.com")` |
| `HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());` | `data = response.json()` |
| `String body = response.body();` | `if response.status_code == 200: print(data)` |
| Verbose, requires manual parsing | Simple, concise, automatic JSON parsing |
| HttpClient (Java 11+), RestTemplate, Retrofit | requests library (most popular) |

---

## 23. Virtual Environment

| Java | Python |
|------|--------|
| Maven or Gradle project setup | `python -m venv venv` |
| `pom.xml` or `build.gradle` for dependencies | `source venv/bin/activate` (Linux/Mac) |
| Dependencies downloaded to `.m2` repository | `venv\Scripts\activate` (Windows) |
| JVM isolates projects | Isolates Python packages per project |
| Global JVM version | Python version can vary per venv |
| No activation needed | Must activate before using |

---

## 24. Package Manager (pip/Maven)

| Java | Python |
|------|--------|
| Maven: `pom.xml` config file | `pip install package_name` |
| `mvn install` or `mvn dependency:resolve` | `pip install -r requirements.txt` |
| Gradle: `build.gradle` config file | `pip freeze > requirements.txt` |
| Centralized Maven Repository | PyPI (Python Package Index) |
| `<dependency>` tags for packages | Simple text format for dependencies |
| Version management in config | Can use `uv` (faster alternative) |

---

## 25. Main Method

| Java | Python |
|------|--------|
| `public static void main(String[] args) { }` | `if __name__ == "__main__":` |
| Entry point of program | Entry point of script |
| Always required in executable class | Optional, used to prevent execution when imported |
| Arguments passed as String array | `import sys; args = sys.argv[1:]` |
| Example: `java MyApp arg1 arg2` | Example: `python script.py arg1 arg2` |
| Program: `public class MyApp { public static void main(String[] args) { System.out.println(args[0]); } }` | Script: `if __name__ == "__main__": print(sys.argv[1])` |

---

## 26. File I/O - Writing

| Java | Python |
|------|--------|
| `FileWriter writer = new FileWriter("file.txt");` | `with open("file.txt", "w") as f:` |
| `writer.write("Hello World");` | `f.write("Hello World")` |
| `writer.close();` | Auto-closes when exiting context |
| Must handle `IOException` | Built-in exception handling |
| `try-finally` or try-with-resources | Context manager (`with`) handles cleanup |
| Multiple writes: `writer.append("text");` | Multiple writes: `f.write(line1); f.write(line2)` |

---

## 27. List Comprehensions & Advanced Loops

| Java | Python |
|------|--------|
| `List<Integer> squares = new ArrayList<>(); for(int i: numbers) { squares.add(i*i); }` | `squares = [i*i for i in numbers]` |
| Verbose, multi-line | Concise, one-liner |
| No built-in comprehension syntax | List comprehensions native feature |
| Java 8+ Streams: `numbers.stream().map(x -> x*x).collect(Collectors.toList());` | `filtered = [x for x in numbers if x > 5]` |
| Functional approach with Streams | Natural, readable comprehensions |
| Dict creation: Manual loop | Dict comprehension: `{k: v*2 for k, v in dict.items()}` |

---

## 28. AI Code Pattern - Calling Ollama Models

| Java | Python |
|------|--------|
| Requires HTTP client library | `import requests` or `ollama` library |
| `HttpClient client = HttpClient.newHttpClient();` | `import ollama` |
| Manually construct JSON request | `response = ollama.generate(model="gemma:7b", prompt="Hello")` |
| `POST to http://localhost:11434/api/generate` | Uses Ollama Python library directly |
| `{"model":"gemma:7b","prompt":"Your question","stream":false}` | Simple function call |
| Parse response JSON manually | Get response object with properties |
| Example: `String response = client.send(request, HttpResponse.BodyHandlers.ofString());` | Example: `print(response["response"])` |
| More boilerplate code | More Pythonic, cleaner code |

---
## Quick Reference Table

| Feature | Java | Python |
|---------|------|--------|
| **Type System** | Static, Strongly Typed | Dynamic, Weakly Typed |
| **Syntax** | Verbose, Explicit | Concise, Clean |
| **Speed** | Fast (compiled) | Slower (interpreted) |
| **Use Cases** | Enterprise, Backend, Android | Data Science, ML, Scripting, Web |
| **Learning Curve** | Steeper | Gentler |
| **OOP** | Pure OOP | Multi-paradigm |
| **Execution** | Compiled to bytecode (.class) | Interpreted |
| **Platform** | JVM (Write once, run anywhere) | Cross-platform with Python installed |

---
| Python           | Java Equivalent                       |
| ---------------- | ------------------------------------- |
| `list`           | `ArrayList`                           |
| `dict`           | `HashMap`                             |
| `def`            | Method                                |
| `class`          | Class                                 |
| `import`         | `import`                              |
| `pip`            | Maven/Gradle                          |
| `venv`           | Project environment with dependencies |
| `requests`       | `RestTemplate` / `WebClient`          |
| `print()`        | `System.out.println()`                |
| `None`           | `null`                                |
| `True` / `False` | `true` / `false`                      |
