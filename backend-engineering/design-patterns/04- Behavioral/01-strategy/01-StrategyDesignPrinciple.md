## Problem Statement
To reuse the code we used inheritance. 

But when solution have a lot of permutation and combination of features, we end up with complex inheritance tree.

This is not a good design. We need to find a way to reuse code without creating complex tree.

1. Unable to resue the code effectively.
2. To Add a new feature modification of inheritance tree is required.
3. Breaking OCP


![RobotInheritance.png](../../../../Images/RobotInheritance.png)
Initially design Robot inheritance then new feature come non-walking which require modification of inheritance tree.
![RobotInheritance2.png](../../../../Images/RobotInheritance2.png)
## Solution
To solve this problem we can use strategy Design Pattern instead of inheritance.

```Defines a family of algorithms, put them into a seperate classs so that they can be chanegs at runtime.```
![StrategyDesignPrinciple.png](../../../../Images/StrategyDesignPrinciple.png)

Replace inheritance with composition. 

Create separate classes for each **behavior** and inject them into the main class. 

This way we can add new **behavior** without modifying existing code, adhering to OCP.

if we have small behavior (1-3 lines) we can use lambda strategy to avoid class explosion.


### Overloading vs LambdaStrategy vs Strategy vs Inheritance



| If you see this situation                   |                 Avoid |                     Prefer | Why                                      |
|---------------------------------------------|----------------------:|---------------------------:|------------------------------------------|
| Same operation, only parameter types differ |              Strategy |                Overloading | Simple compile-time dispatch             |
| 2–4 fixed cases only                        |              Strategy |                Overloading | Strategy becomes overengineering         |
| Behavior may keep increasing every month    |           Overloading | Lambda Strategy / Strategy | Avoid giant if-else or many methods      |
| Business team changes rules often           |           Overloading |                   Strategy | Plug new behavior without modifying code |
| Logic can change at runtime                 |           Overloading |                   Strategy | Runtime swapping needed                  |
| Logic is tiny (1–3 lines)                   | Full Strategy classes |            Lambda Strategy | Avoid class explosion                    |
| Logic becomes large (50–100 lines)          |       Lambda Strategy |              Full Strategy | Lambdas become unreadable                |
| Multiple algorithms share parent identity   |              Strategy |                Inheritance | Natural IS-A relationship                |
| Behavior changes independently from object  |           Inheritance |                   Strategy | HAS-A > IS-A                             |
| Need to combine behaviors                   |           Inheritance |                   Strategy | Composition easier                       |
| Deep inheritance chain growing              |           Inheritance |                   Strategy | Avoid rigid hierarchy                    |


```
    
Only few parameter types?
        |
       YES
        ↓
   Overloading


Behavior changing?
        |
       YES
        ↓
Logic small?
   /       \
 YES       NO
 ↓          ↓
Lambda   Strategy


Natural family?
      |
     YES
      ↓
Inheritance


Behavior changing independently?
      |
     YES
      ↓
Strategy

```
### Implementation and comparison of different approaches

1. Same operation, only parameter types differ using overloading, Strategy and lamda strategy and inheritance.

Requirements: need to find sum of two values having different parameter types.

<details>
<summary><h3> Implement using overloading - Compile time dispatch</h3></summary>

```java
public class Overloading {
    static void main(String[] args) {
        SumUtils sumUtils = new SumUtils();
        System.out.println(sumUtils.sum(10, 20));
        System.out.println(sumUtils.sum(10.5, 20.5));
        System.out.println(sumUtils.sum(10, 20.5));
        System.out.println(sumUtils.sum(new BigDecimal("10.5"), new BigDecimal("20.5")));

    }
}

//Overloading → Compile time → Static binding
//Overriding → Runtime → Dynamic binding

class SumUtils{

    int sum(int a, int b){
        return a+b;
    }

    double sum(double a, double b){
        return a+b;
    }

    double sum(int a, double b){
        return a+b;
    }

    BigDecimal sum(BigDecimal a, BigDecimal b){
        return a.add(b);
    }
}

```
</details>

---

<details>
<summary><h3>
Implement using Simple Lambda</h3></summary>

### Functional Interface

```java
@FunctionalInterface
interface SumFunction<T,U,R>{
    R apply(T t, U u);
}
```


### Traditional Class Implementation (Over Engineering)

```java
class IntegerSum implements SumFunction<Integer, Integer, Integer>{

    @Override
    public Integer apply(Integer a, Integer b){
        return a + b;
    }
}
```

### Lambda Implementation

```java
import java.math.BigDecimal;

public class SimpleLambda {

    public static void main(String[] args) {

        // Works with all Number subclasses
        SumFunction<Number, Number, Double> sumNumber =
                (a,b) -> a.doubleValue() + b.doubleValue();

        System.out.println(sumNumber.apply(3,4));
        System.out.println(sumNumber.apply(3.5,4.5));
        System.out.println(
                sumNumber.apply(
                        new BigDecimal("3.5"),
                        new BigDecimal("4.5")
                )
        );

        // Will not compile
        // sumNumber.apply("Hello","World");


        // Integer Sum
        SumFunction<Integer,Integer,Integer> sumInt =
                (a,b) -> a + b;

        System.out.printf(
                "Sum of %s and %s is %s%n",
                1,2,sumInt.apply(1,2)
        );


        // Double Sum
        SumFunction<Double,Double,Double> sumDouble =
                (a,b) -> a + b;

        System.out.printf(
                "Sum of %s and %s is %s%n",
                1.5,2.5,sumDouble.apply(1.5,2.5)
        );


        // BigDecimal Sum
        SumFunction<BigDecimal,BigDecimal,BigDecimal> sumBigDecimal =
                (a,b) -> a.add(b);

        System.out.println(
                "Sum of BigDecimal = " +
                sumBigDecimal.apply(
                        new BigDecimal("10.5"),
                        new BigDecimal("20.3")
                )
        );


        // String Concatenation
        SumFunction<String,String,String> sumString =
                (a,b) -> a.concat(b);

        System.out.printf(
                "String result = %s%n",
                sumString.apply("Hello ","World")
        );
    }
}
```

### Output

```text
7.0
8.0
8.0
Sum of 1 and 2 is 3
Sum of 1.5 and 2.5 is 4.0
Sum of BigDecimal = 30.8
String result = Hello World
```
</details>


<details>
<summary><h3>
Implement using Inheritance</h3></summary>

```java
public class InheritanceSum {

    static void main(String[] args) {
        BinarySum cal = new IntegerSums();
        System.out.println(cal.sum(1,2));

        BinarySum cal2 = new DoubleSums();
        System.out.println(cal2.sum(1.5,2.5));
    }
}

 class BinarySum{

    Object sum(Object a, Object b){
        return a;
    }

}

class IntegerSums extends BinarySum{
    @Override
    Object sum(Object a, Object b){
        if(a instanceof Integer && b instanceof Integer){
            return (Integer)a + (Integer)b;
        }
        throw new IllegalArgumentException("Invalid input type");
    }
}

class DoubleSums extends BinarySum{
    @Override
    Object sum(Object a, Object b){
        if(a instanceof Double && b instanceof Double){
            return (Double)a + (Double)b;
        }
        throw new IllegalArgumentException("Invalid input type");
    }
}

```
</details>

<details>
<summary><h3>
Implement using Strategy</h3></summary>

```java


public class Strategy {

    static void main(String[] args) {
        Context context = new Context(new SumOperation());
        System.out.println(context.apply(5, 10)); // Output: 15

        context = new Context(new SubstractionOperation());
        System.out.println(context.apply(5, 10)); // Output: -5

        context = new Context(new ProductOperation());
        System.out.println(context.apply(5, 10)); // Output: 50
    }
}

//1. Interface (Strategy)

//2. Implementation class ( define the behaviour)

//3. Context ( Calling Strategy at run time)

interface Operation<T,U,R>{
    R perform(T t, U u);
}

class SumOperation implements Operation<Integer, Integer, Integer> {
    @Override
    public Integer perform(Integer a, Integer b) {
        return a + b;
    }
}

class SumDoubleOperation implements Operation<Double, Double, Double> {
    @Override
    public Double perform(Double a, Double b) {
        return a + b;
    }
}

class SubstractionOperation implements Operation<Integer, Integer, Integer> {
    @Override
    public Integer perform(Integer a, Integer b) {
        return a-b;
    }
}

class ProductOperation implements Operation<Integer, Integer, Integer> {
    @Override
    public Integer perform(Integer a, Integer b) {
        return a*b;
    }
}

class Context{

    private Operation <Integer, Integer, Integer> operation;
    public Context(Operation<Integer, Integer, Integer> operation){
        this.operation = operation;
    }

    public Integer apply(Integer a, Integer b){
        return operation.perform(a,b);
    }

}

```
</details>

### Quick Summary

| Prefer                               | Memory Trick                | Use When                                                                                            | Think                           | Dont use when                                                                      | Example                                                |
|--------------------------------------|-----------------------------|-----------------------------------------------------------------------------------------------------|:--------------------------------|------------------------------------------------------------------------------------|--------------------------------------------------------|
| OVERLOADING                          | same name, different inputs | Same operation name<br/>Only parameter types differ<br/>Behavior is identical                       | Same logic, different inputs                                                                        | behavior changes<br/>logic growsruntime flexibility needed                         | sum(int,int)<br/>sum(double,double)<br/>sum(long,long) |
| INHERITANCE      | is-a relationship           | Strong IS-A relationship exists<br/>Behavior is fixed per type<br/>Natural hierarchy exists         | Identity is the variation                                                     | behavior changes frequently<br/>class explosion happens<br/>need runtime switching | Car IS-A Vehicle                                       |
| STRATEGY PATTERN  | interchangeable behavior (HAS-A behavior swap)   | Behavior changes independently of object<br/>Multiple algorithms exist<br/>Runtime switching needed | Same object, different behavior                                                             |                                                                                    | Payment → UPI / Card / Wallet                          |
| LAMBDA STRATEGY  | Lightweight Strategy        | Behavior is small (1-3 lines)<br/>Behavior changes independently<br/>Runtime switching needed       | Same object, different behavior                                                                     | Behavior becomes large<br/>Need to share state between behaviors                   | Sorting → Comparator with lambda                       |


