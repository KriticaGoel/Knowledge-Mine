### Agenda

- [Lambdas](#Definition)
- [Functional Interfaces](#Functional-Interfaces)
- [Lambdas Expressions](#Lambdas-Expressions)
- [Syntax](#Syntax)
- Implementation Examples 
        - Convert Anonymous Class to Lambda
        - Passing arguments to Lambdas
        - Lambda Expressions with Parameters
        - Lambdas in Collections
        - Sorting Example

#### 🔹 Key Concepts:
Lambda
Functional Interfaces

#### 🔹 Definition :
A lambda expression is a block of code that gets passed around, like an anonymous method. It is a way to pass behavior
as an argument to a method invocation and to define a method without a name.


#### 🔹 Functional Interfaces
A functional interface is an interface that contains one and only one abstract method. It is a way to define a contract
for behavior as an argument to a method invocation

### Lambda Expressions
It has also known as anonymous functions, which provide a way to write a code in more compact form.

The basic syntax of a lambda expression consists of the parameter list, the arrow (->), and the body. The body can be
either an expression or a block of statements.

#### 🔹 Syntax of Lambda Expressions:

```
(parameters) -> expression
(parameters) -> { statements }
```

**Parameter List**
This represents the parameters passed to the lambda expression. It can be empty or contain one or
more parameters enclosed in parentheses. If there's only one parameter and its type is inferred, you can omit the
parentheses.

**Arrow Operator (->):** 
This separates the parameter list from the body of the lambda expression.

**Lambda Body:** 
This contains the code that makes up the implementation of the abstract method of the functional
interface. The body can be a single expression or a block of code enclosed in curly braces.

Lambda expressions are most commonly used with functional interfaces, which are interfaces containing only one abstract
method. Java 8 introduced the @FunctionalInterface annotation to mark such interfaces.

#### 🔹 Syntax of Functional Interface:
```
@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod();
}
```

### Examples

Let's start with some simple examples to illustrate the basic syntax:

#### 1. Given a Runnable implemented using an anonymous class, rewrite it using a lambda expression.

To understand, the motivation behind lambdas, remember how we create a thread in Java. We create a class that implements
the Runnable interface and override the run() method. Then we create a new instance of the class and pass it to the
Thread constructor.

```java
// Traditional approach
Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, World!");
            }
        };
```

This is a lot of code to write just to print a simple message. Here, the Runnable interface is a functional interface.
It contains only one abstract method, run(). An interface with a single abstract method (SAM) is called a functional
interface. Such interfaces can be implemented using lambdas.

Using Lambda Expression.

```java
// Lambda expression
Runnable lambdaRunnable = () -> System.out.println("Hello, World!");
```

#### 2. Add Numbers

```java
// Traditional approach
MathOperation traditionalAddition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };
```

Using Lambda expression

```java
MathOperation lambdaAddition = (a, b) -> a + b;
```

#### 3. Lambda Expressions with Parameters

Lambda expressions can take parameters, making them versatile for various use cases.

```java
NumberChecker traditionalChecker = new NumberChecker() {
    @Override
    public boolean check(int number) {
        return number % 2 == 0;
    }
};
```

Using Lambda expression

```java
NumberChecker lambdaChecker = number -> number % 2 == 0;
```

#### 4. Lambda Expressions in Collections

Lambda expressions are commonly used with collections for concise iteration and processing.

Filtering a List Example (Traditonal Approach)

```java
List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango");

// Traditional approach
List<String> filteredTraditional = new ArrayList<>();
for(
String fruit :fruits){
        if(fruit.startsWith("A")){
        filteredTraditional.

add(fruit);
    }
            }
```

Using Lambda expression & Java Stream API -Replace Traditional Loop Filter
Using a List<Integer>, replace a for loop that collects even numbers with stream\(\).filter\(\) and lambda.


```java
List<String> filteredLambda = fruits.stream()
        .filter(fruit -> fruit.startsWith("A"))
        .collect(Collectors.toList());
```

### 4. Sort with Lambda

Sort a List<String> by:


natural order
length of string
using lambda comparators.

Method references to provide a shorthand notation for lambda expressions, making the code even more concise.

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Lambda expression for sorting
Collections.sort(names, (a, b) ->a.compareTo(b));

// Method reference for sorting
        Collections.sort(names, String::compareTo);
```
### 5. Build a Functional Interface
Create a @FunctionalInterface named StringTransformer with one method, then implement:


uppercase conversion
reverse string
using lambdas.


(5) Use forEach Instead of Loop
Print all elements of a list using forEach and lambda instead of a traditional for loop.


(6) Map and Transform
Given List<String> names, return a new list with all names in lowercase using stream\(\).map\(\).

(8) Function Chaining
Use Function<Integer, Integer> lambdas to:


square a number
add 10
Then chain using andThen.

(9) Custom Calculator
Create a MathOperation functional interface and implement add, subtract, multiply, divide using lambdas.

(10) Method Reference Refactor
Refactor lambda expressions to method references where possible (e.g., String::trim, Integer::parseInt).



#### Predicate Practice
Create lambdas for:
is even
is positive
is multiple of 5
Then test numbers using Predicate<Integer>.

### How to make lambda a habit (and reduce traditional style)
Prefer java.util.function types first: Predicate, Function, Consumer, Supplier.

For collection tasks, think in this order: stream -> filter/map/sorted -> collect.

Refactor one old loop daily into stream+lambda.

During code review, reject unnecessary anonymous classes for SAM interfaces.

Keep lambdas short (one responsibility).

Use method references when lambda only calls one method.

Practice with small katas (filtering, sorting, grouping) for 15 minutes daily.

Learn common patterns: filter, map, reduce, anyMatch, allMatch.

Avoid overuse: use traditional code when logic becomes unclear.

Set a personal rule: if interface has one abstract method, try lambda first.