## Agenda

* [Generics](#Generics)
* [Generic Method](#generic-method)
* [Generic Static Method](#generic-static-method)
* [Extends keyword in Generics](#extends-keyword-in-generics)
* [Function Interface](#function-interface)

#### 🔹 Quick Revision :

1. what is generics?
2. How many types?
3. what problem its resolved?
4. Real use case of generics?

#### 🔹 Definition :

Generics allow us to define a class with ***parametrize datatype*** (datatypes are not fixed)

#### 🔹 Key Concepts:

- Generic class
- Generic Method
- Generic Static Method
- Wildcard and bound ( upper and lower)
- Generic interface

#### 🔹 Why needed:
Reuse same code for multiple purposes.

Problem: for pair we need to create separate classes. which increase code size and impact on memory

Pair of latitude and longitude

```java

Pair{
    int latitude;
    int longitude;
}
```
Pair of Students name and Id

```java
Students{
    String name;
    int id;
}
```        
💡 <span style="background-color:orange;padding:0.1em 0.3em;border-radius:3px">Solution 1:</span> Create a class and use objects

```java
class Pair{

    Object firstObj;

    Object secondObj;

    getFirstObj(){
    }

}

Double latitude = obj.getFirst(); //compile time error
Double latitude= (Double)obj.getFirst(); //Forcefully typecast and complier check skip
```
✅ Remove duplicate code

❌ To access the data we need to do typecasting and if wrong data is provide then we get error in run time. **Compile time check skip**.

⚠️ Hard to debug


💡 <span style="background-color:orange;padding:0.1em 0.3em;border-radius:3px">Solution 2:</span>  Create Pair as a Generic class

✅ Generics were added to Java to provide compile-time type checking

✅ Removing the risk of ClassCastException that was common while working with collection classes

#### 🔹 Syntax/Example:

### Generic Class  
Allow us to define a class with parameterized data type

```java
//CREATE a GENERIC CLASS

//F nad S are parametrize datatype and firstand second is a variable
class Pair<F,S>{
	F first;
	S second;
	
	Pair(F first, S second){
		this.first=first;
		this.second=second;}
		
	F getFirst(){
	return first;}
}

//CREATE OBJECT OF GENERIC CLASS
Pair<Double,Double> p = new Pair(1.0,3.0);
Pair<String,Integer> p = new Pair("Kritica",123);

Double latitude = p.getFirst();

```



### Generic Method

```java
public <K, V> void doSomething(K key, V value) {
    System.out.println("key: " + key + " value: " + value);
}

GenericMethod gm= new GenericMethod();
        gm.doSomething(123,"ABC");
        gm.doSomething("Hello", 456);
```

### Generic Static Method

```java
<T> T methodName(T input) {    return input;}

public static <K, V, Z> Z doSomething(K key, V value) {
    System.out.println("key: " + key + " value: " + value);
    return (Z) BigInteger.ONE;
}


```

### Extends keyword in Generics

```java
public class Instructor extends Users {

    double avgRating;

    public Instructor(String FirstName, String LastName, int Age, double avgRating) {
        super(FirstName, LastName, Age);
        this.avgRating = avgRating;
    }

    public void scheduleSession() {
        System.out.println("Session scheduled");
    }
}
```

```java
public class Users {

    String FirstName;
    String LastName;
    int Age;

    public Users(String FirstName, String LastName, int Age) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void login() {
        System.out.println("Login");
    }

}
```

Without generics got error while calling printUser method having argument of parent class with child class argument
instructors)

```java
public class App {

    public void initilaize() {
        List<Instructor> instructors = new ArrayList<Instructor>();
        instructors.add(new Instructor("Kritica", "Goel", 27, 5));
        instructors.add(new Instructor("Sachin", "Goel", 30, 4.7));

        //instroctor is a child class of Users
        printUser(instructors);
    }

    public void printUser(List<Users> users) {
        for (Users user : users) {
            System.out.println(user.getFirstName());
            user.login();
        }
    }
}
```

![withoutGeneric.png](..%2F..%2F..%2Fresources%2FwithoutGeneric.png)
Solution:
Add generic and use extends Child class to access all methods of parent and child

```java
public <E extends Instructor> void printUser(List<E> users) {
    for (E user : users) {
        System.out.println(user.getFirstName());
        user.login();
        user.scheduleSession();
    }
}
```

```java
public class App {

    public void initilaize() {
        List<Instructor> instructors = new ArrayList<Instructor>();
        instructors.add(new Instructor("Kritica", "Goel", 27, 5));
        instructors.add(new Instructor("Sachin", "Goel", 30, 4.7));

        //instroctor is a child class of Users
        printUser(instructors);
    }

    public <E extends Instructor> void printUser(List<E> users) {
        for (E user : users) {
            System.out.println(user.getFirstName());
            user.login();
            user.scheduleSession();
        }
    }
}
```

* Arguments: Accept class and its child class
* Method Calling: Child class method can't be called. same as inheritance

```java
public class App {

    public void initialize() {
        List<TemporaryInstructor> temporaryInstructors = new ArrayList<TemporaryInstructor>();
        temporaryInstructors.add(new TemporaryInstructor("Kritica", "Goel", 27, 5, true));
        temporaryInstructors.add(new TemporaryInstructor("Sachin", "Goel", 30, 4.7, false));

        List<Users> users = new ArrayList<Users>();
        users.add(new Users("Kritica", "Goel", 27));
        users.add(new Users("Sachin", "Goel", 30));


        List<Instructor> instructors = new ArrayList<Instructor>();
        instructors.add(new Instructor("Kritica", "Goel", 27, 5));
        instructors.add(new Instructor("Sachin", "Goel", 30, 4.7));

        //Users is a paerent class of Instructor
        //printUser(users);-- not allow

        // Instructor is a clas defined in printUser argument
        printUser(instructors);
        //TemporaryInstructor is a child class of Instructor
        printUser(temporaryInstructors);// child class can be pass as argument temporaryInstructors is a child class of Instructor
    }

    public <E extends Instructor> void printUser(List<E> users) {
        for (E user : users) {
            System.out.println(user.getFirstName());
            user.login(); // Define in parent class of Instructor
            user.scheduleSession(); // Define in Instructor class
            //user.isTemp(); Define in child class of Instructor. Child class method can't be called
        }
    }
}
```

#### 🔹 Real-world Use:

❌ Before:

```java
class UserRepository {
    void save(User user) {}
}
```

❌ Again:

```java
class OrderRepository {
    void save(Order order) {}
}
```

✅ After (Generic Thinking):

```java
class Repository<T> {
    void save(T entity) {}
}
```

👉 Generic API Response

```java
class ApiResponse<T> {
    private T data;
    private String status;
}
```

**👉 Generic Service Layer**

```java
class ApiService<T> {
    T process(T input) {
        return input;
    }
}
```


#### 🔹 Quick Revision Line:

1. **The Mental Trigger (Train this reflex)** - Will this logic work for more than one type? if Yes, use generic
2. **Rewire Your Brain (3-Step Habit)** -

   ### Step 1: Write normal code (don’t stop yourself)

   ### Step 2: Ask:

    - Can this work for another type?
    - Am I repeating same logic?

   ### Step 3: Refactor to generic


Generic Class
without generic

```java
public class WIthoutGeneric {

    public static void main(String[] args) {

        sum(10,20);
        sumDouble(10.20,20.10);

    }

    static void sum(int a, int b){
        System.out.println("The sum of two integers is: " + (a + b));

    }

    static void sumDouble(Double a, Double b){
        System.out.println("The sum of two double is: " + (a + b));
    }
}
```

With generic

```java
public class Generic101 {

    public static void main(String[] args) {
        SumGeneric<Integer,Integer> sumInteger = new SumGeneric<>(10,20);
        sumInteger.sum();
       // System.out.printf("The sum of integer %s,%s is %s ",sumInteger.getFirstObject(),sumInteger.getSecondObject(),sumInteger.getSecondObject()+sumInteger.getFirstObject());
        SumGeneric<Double,Double> sumDouble = new SumGeneric<>(10.20,20.10);
        sumDouble.sum();
    }
}

public class SumGeneric<F,S> {
    F firstObject;
    S secondObject;

    public SumGeneric(F firstObject, S secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public F getFirstObject() {
        return firstObject;
    }

    public S getSecondObject(){
        return secondObject;
    }

    public void sum(){
        if(firstObject instanceof Integer && secondObject instanceof Integer){
            System.out.println("The sum of two integers is: " + ((Integer)firstObject + (Integer)secondObject));
        }else if(firstObject instanceof Double && secondObject instanceof Double){
            System.out.println("The sum of two double is: " + ((Double)firstObject + (Double)secondObject));
        }else{
            System.out.println("Unsupported types for summation.");
        }
    }
}
```
GENEtic Method

```java
```java
public static void main(String[] args) {
        // calling Generic method defined in GenericMethod class
        GenericMethod gm = new GenericMethod();
        gm.display(123, "ABC");
        gm.display("Hello", 456);
        
         dosomething("Kritica");
        dosomething(100);

    }
    
    public static <T> T dosomething(T input){
        System.out.printf("The value of input is: %s %n",input);
        return input;
    }
```

```java
public class GenericMethod {

    public <K, V> void display(K key, V value) {
        System.out.println("Key: " + key + ", Value: " + value);
    }
    
    //With generic return type
     public <K, V,T> T display(K key, V value) {
        System.out.println("Key: " + key + ", Value: " + value);
        return null; // Just for demonstration, you can return any type as needed
    }
}

```

Give me 10 practise qustions on generics:
Give me 10 practice questions on Generics:

1\. Create a generic class `Box<T>` with methods `set(T value)` and `get()`. Demonstrate usage with `Integer` and `String`.

2\. Write a generic method `<T> void printArray(T[] arr)` that prints elements of any array type.

3\. Build a generic `Pair<K, V>` class and write a program to store and print student name\-id pairs.

4\. Create a method `<T extends Number> double add(T a, T b)` and test it with `Integer`, `Double`, and `Float`.

5\. Write a function that accepts `List<? extends Users>` and prints all user names. Explain why `List<Instructor>` works here.

6\. Write a function that accepts `List<? super Instructor>` and adds new `Instructor` objects. Explain why this is useful.

7\. Refactor a non\-generic repository class into `Repository<T>` with `save(T entity)` and `findById(int id)` methods.

8\. Create a generic static utility method `<T> T getFirst(List<T> list)` that returns the first element safely.

9\. Implement a generic interface `Converter<I, O>` with method `O convert(I input)`, then create two implementations.

10\. Compare `List<Object>`, `List<?>`, and `List<? extends Number>` using small examples and write what operations are allowed in each.