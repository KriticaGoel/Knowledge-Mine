### Comparator in Java

Comparator<T> is an interface used to define custom sorting logic outside the class. 

It belongs to java.util package and contains the compare() method.

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

How compare() works:

```java
Comparator<Integer> comp = (a, b) -> a - b;
```

| Return Value   | Meaning            |
| -------------- | ------------------ |
| Negative (< 0) | o1 comes before o2 |
| Zero (0)       | Both are equal     |
| Positive (> 0) | o1 comes after o2  |

Example:

```java
Comparator<Integer> comp = (a, b) -> a - b;

System.out.println(comp.compare(10, 20)); // -10
System.out.println(comp.compare(20, 20)); // 0
System.out.println(comp.compare(30, 20)); // 10
```
### Modern Java 8+ Style

#### Instead of writing compare manually:
```java
employees.sort(Comparator.comparing(Employee::getSalary));
```

#### Descending:
```java
employees.sort(Comparator.comparing(Employee::getSalary).reversed());
```

#### Multiple fields:
```java
employees.sort(
Comparator.comparing(Employee::getDepartment)
.thenComparing(Employee::getSalary)
);
```

#### Compare .sort() vs .sorted():
| .sort() (List)                      | .sorted() (Stream)                     |
|-------------------------------------|----------------------------------------|
| In-place sorting                    | Returns new sorted stream              |
| Modifies original list              | Does not modify original list          |
| return void ; smae list get updated | return new stream with sorted elements |

### Use Cases of Comparator:

Employee class with fields: name, salary, department
```java
class Employee {
String name;
double salary;
String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
}
```
#### Sort employees by salary:

1. Using Comparator: Original list get changed
```java
List<Employee> employees = new ArrayList<>();

employees.add(new Employee("Kritica", 2000, "IT"));
employees.add(new Employee("Sachin", 3000, "IT"));
employees.add(new Employee("Karan", 4000, "IT"));
employees.add(new Employee("Abc", 1000, "HR"));

//Sort employees by salary:
Comparator<Employee> salaryComparator =
        (e1, e2) -> Integer.compare(e1.salary, e2.salary);
employees.sort(salaryComparator);
for (Employee employee : employees) {
    System.out.println(employee.getSalary());
}
```

2. Using Comparator.comparing() : **Original list get changed**
```java
 List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Kritica", 2000, "IT"));
        employees.add(new Employee("Sachin", 3000, "IT"));
        employees.add(new Employee("Karan", 4000, "IT"));
        employees.add(new Employee("Abc", 1000, "HR"));
        //Sort employees by salary:
        employees.sort(Comparator.comparing(Employee::getSalary));
        employees.forEach(employee -> System.out.println(employee.getSalary()));
        
```

3. Using Stream.sorted() : **Original list remains unchanged**
```java
List<Employee> employees = new ArrayList<>();

employees.add(new Employee("Kritica", 2000, "IT"));
employees.add(new Employee("Sachin", 3000, "IT"));
employees.add(new Employee("Karan", 4000, "IT"));
employees.add(new Employee("Abc", 1000, "HR"));
employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .forEach(employee -> System.out.println(employee.getSalary()));
```
#### Sort employees by salary and then by name in descending order:
```java
List<Employee> employees = new ArrayList<>();
employees.add(new Employee("Kritica", 2000, "IT"));
employees.add(new Employee("Sachin", 3000, "IT"));
employees.add(new Employee("Karan", 4000, "IT"));
employees.add(new Employee("Abc", 2000, "HR"));

employees.sort(Comparator.comparing(Employee:: getSalary)
         .thenComparing(Employee:: getName)
         .reversed());
employees.forEach(employee -> System.out.println(employee.getSalary() + " " + employee.getName()));

```
reversed() applies to the entire comparator chain, not just the last field. so name and salary both get reversed.

#### Sort employees by salary in ascending order and then by name in descending order:
```java
List<Employee> employees = new ArrayList<>();
employees.add(new Employee("Kritica", 2000, "IT"));
employees.add(new Employee("Sachin", 3000, "IT"));
employees.add(new Employee("Karan", 4000, "IT"));
employees.add(new Employee("Abc", 2000, "HR"));

employees.sort(Comparator.comparing(Employee:: getSalary)
        .thenComparing(Comparator.comparing(Employee:: getName).reversed()));
employees.forEach(employee -> System.out.println(employee.getSalary() + " " + employee.getName()));

//Another way to write the same thing:
employees.sort(Comparator.comparing(Employee:: getSalary).thenComparing(Employee:: getName,Comparator.reverseOrder()));
employees.forEach(employee -> System.out.println(employee.getSalary() + " " + employee.getName()));
```
Previously we apply reversed() to the entire comparator chain
 
here we apply reversed() only to the name field, so salary is sorted in ascending order and name is sorted in descending order.



### difference-between-comparable-and-comparator

Comparable
Comparator

1) **Comparable**-Comparable provides a single sorting sequence. In other words, we can sort the collection on the basis
   of a single element such as id, name, and price.

   **Comparator**-The Comparator provides multiple sorting sequences. In other words, we can sort the collection on the
   basis of multiple elements such as id, name, and price etc.

2) **Comparable**-Comparable affects the original class, i.e., the actual class is modified.

   **Comparator**-Comparator doesn't affect the original class, i.e., the actual class is not modified.

3) **Comparable**-Comparable provides compareTo() method to sort elements.

   **Comparator**-Comparator provides compare() method to sort elements.

4) **Comparable**-Comparable is present in java.lang package.

   **Comparator**-A Comparator is present in the java.util package.

5) **Comparable**-We can sort the list elements of Comparable type by Collections.sort(List) method.

   **Comparator**-We can sort the list elements of Comparator type by Collections.sort(List, Comparator) method.

```java
static class Car implements Comparable<Car> {
    private int speed;
    private int power;
    public Car(int speed, int power){
        this.speed = speed;
        this.power = power;
    }

    @Override
    public String toString() {
        return "[S=" + this.speed + ", P=" + 
        this.power + "]";
    }

    

   
    public int compareTo(Car a){
        return this.speed-a.speed;
    }

    
}
```