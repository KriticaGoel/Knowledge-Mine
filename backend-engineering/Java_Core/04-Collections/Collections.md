### Agenda

* [Collections](#collections)
    * [Common Methods](#common-methods-of-collections)
* [List](#list)
    * [Operation of List Interface](#operation-of-list-interface)
        * [Adding Elements](#1-adding-elements)
        * [Updating Elements](#2-updating-elements)
        * [Search Elements](#3-search-elements)
        * [Removing Elements](#4-removing-elements)
        * [Accessing Elements](#5-accessing-elements)
        * [Checking Elements](#6-checking-elements)
        * [Miscellaneous Method](#7-miscellaneous-method)

    * [Complexity of List Interface in Java](#complexity-of-list-interface-in-java)
    * [Implementation of List](#implementation-of-list)
    * [Comparison of List and Set](#comparison-of-list-and-set)

![Topics to be covered.png](..%2F..%2F..%2Fresources%2FTopics%20to%20be%20covered.png)
### Collections

![CollectionFramework.png](..%2F..%2F..%2Fresources%2FCollectionFramework.png)
#### Common methods of Collections:

* **add(E element)** - inserts the specified element to the collection
* **size()** - returns the size of the collection
* **isEmpty()** - returns true if this list contains the specified element
* **contains(Object o)** Returns an iterator over the elements in this list in a proper sequence.
* **remove(Object o)** - removes the specified element from the collection
* **iterator()** - returns an iterator to access elements of the collection
* **clear()** - removes all the elements of the collection
* **toArray()** - Returns an array containing all of the elements in this list in proper sequence
* **toArray(T[] arr)** - Inserts all of the elements in the specified collection into this list at the specified
  position
* **addAll(Collection c)** - adds all the elements of a specified collection to the collection
* **removeAll()** — removes all the elements of the specified collection from the collection
* **containAll(Collection c)** - Appends all the elements in the specified collection to the end of this list.Return
  true if this list changed as a result of the call
* **retainAll(Collection c)** - Replaces each element of this list with the result of applying the operator to that
  element (optional operation)
* **equal(Object o)** - Returns the hash code value for this collection. Any class that overrides the Object.equals
  method must also override the Object.hashCode method to satisfy the general contract for the Object.hashCode method.
  In particular, c1.equals(c2) implies that c1.hashCode()==c2.hashCode().
* **hashcode()**

### List

* It is an Interface extends Collection interface
* Ordered Collection: store and access element in a sequential manner
* Include duplicate elements
* Full visibility and control over the ordering of elements

#### Operation of List Interface

##### 1. Adding Elements:

* **add(Object)**: Collection — This method is used to add an element at the end of the List.
* **add(int index, Object o)**: Collection-This method is used to add an element at a specific index in the List
* **addAll(Collection c)**: Collection — This method is used to add a collection of elements at the end of the List.
* **addAll(int index,Collection c)**: Collection-This method is used to add a collection of elements at a specific
  index in the List
* **addFirst()**: List- Add element at 0 index
* **addLast()**: List- Add element at last index

##### 2. Updating Elements:

* **E set(int index, E element)**: List-**Override element** at particular index and return a previous element of
  that index

##### 3. Search Elements:

* **indexOf(element)**: List-Returns the index of the **first occurrence** of the specified element in the list, or -1
  if the element is not found
* **lastIndexOf(element)**: List-Returns the index of the **last occurrence** of the specified element in the list, or
  -1 if the element is not found

##### 4. Removing Elements:

* **remove(Object)**: Collection-This method is used to simply remove an object from the List. If there are multiple
  such objects, then the **first occurrence of the object is removed**.
* **remove(int index)**: List-Since a List is indexed, this method takes an integer value which simply removes the
  element present at that specific index in the List. After removing the element, all the elements are moved to the
  left to fill the space and the indices of the objects are updated.
* **removeFirst**: List-Remove 0 index from the list
* **removeLast**: List-Remove last element from the list
* **clear**: Collection - Remove all elements of a list

##### 5. Accessing Elements:

* **get(int index)**: List-This method returns the element at the specified index in the list.
* **getFirst**: List — Get first element of list
* **getLast**: List —Get lat element of the list

##### 6. Checking Elements:

* **contains(Object)**: This method takes a single parameter, the object to be checked if it is present in the list.

##### 7. Miscellaneous Method:

* **reversed()** -Return a reverse-ordered view of this collection
* **of(E e1,E e2)** -Returns an unmodifiable list containing n elements. List<Integer> integers = List.of(1, 2, 3, 4,
  5, 6);
  *** replaceAll**:
* **subList(int startIndex, int endIndex)**: -integers.subList(3,6)
* sort(Comparator<? super E> c)
* replaceAll(UnaryOperator<E> operator)
* listIterator

#### Complexity of List Interface in Java

| **Operation**                      | **Time <br/>Complexity** | **Space <br/>Complexity** |
|------------------------------------|--------------------------|---------------------------|
| Adding Element in List Interface   | O(1)                     | O(1)                      |                           
| Remove Element from List Interface | O(N)                     | O(N)                      |
| Replace Element in List Interface  | O(N)                     | O(N)                      |
| Traversing List Interface          | O(N)                     | O(N)                      |

#### Implementation of List

```java
public class ListClass {

    public static void main(String[] args) {

        List<Integer> listInteger1 = new ArrayList<>();
        listInteger1.add(1);
        listInteger1.add(3);
        listInteger1.add(4);


        List<Integer> listInteger = new ArrayList<>();
        listInteger.add(10);
        listInteger.add(30);
        listInteger.add(40);
        listInteger.add(1, 20);
        listInteger.add(4, 10);


        listInteger.addAll(new ArrayList<Integer>(listInteger1));
        listInteger.addAll(2, new ArrayList<Integer>(listInteger1));

        Integer abc = listInteger.set(1, 20);//override previous element and return it
        System.out.println("Old value at index 1 is " + abc);

        listInteger.addFirst(30);
        listInteger.addLast(100);
        for (int i = 0; i < listInteger.size(); i++) {
            System.out.println("Element at index " + i + " is " + listInteger.get(i));
        }
        System.out.println("TOTAL SIZE " + listInteger.size());
        System.out.println("The first occurrence of 10 is at index  " + listInteger.indexOf(10));
        System.out.println("The last occurrence of 10 is at index  " + listInteger.lastIndexOf(10));
        System.out.println("Removed element at index 3 is  " + listInteger.remove(3));
        System.out.println("The first occurrence of 10 is at index  " + listInteger.indexOf(10));
        System.out.println("The last occurrence of 10 is at index  " + listInteger.lastIndexOf(10));
        System.out.println("Remove element 10 first occurrence " + listInteger.remove((Integer) 10));
        System.out.println("0th element of the list " + listInteger.getFirst());
        System.out.println("Last element of the list " + listInteger.getLast());

        for (int i = 0; i < listInteger.size(); i++) {
            System.out.println("Element at index " + i + " is " + listInteger.get(i));
        }
        listInteger.clear();
        for (int i = 0; i < listInteger.size(); i++) {
            System.out.println(listInteger.get(i));
        }
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(integers.subList(3, 6));
        System.out.println(integers.reversed());
        System.out.println(List.of(1, 2, 3, 4, 5, 6));

        UnaryOperator<Integer> operator = x -> x * 2;
        System.out.println(operator.apply(8));

    }
}
```

#### Comparison of List and Set

| List                                 | Set                                        |
|--------------------------------------|--------------------------------------------|
| Ordered sequence                     | Unordered sequence                         |
| Allows duplicate elements            | Doesn’t allow duplicate elements.          |
| Element access by position           | Position access to elements is not allowed |
| Multiple null elements can be stored | Only once null value can stored            |

### ArrayList-

    Dynamic arrays-The size of an ArrayList is increased automatically if the collection grows or shrinks if the objects are removed from the collection

### Vector

    Dynamic arrays -It is a thread-safe class.This is not recommended being used in a single-threaded environment as it might cause extra overheads

### LinkedList

    LinkedList is class is an implementation of a doubly-linked list data structure

### Stack

    Stack is a class is based on the basic principle of last-in-first-out. This is a legacy class. This inherits from a Vector class. It is also a thread-safe class. This is not recommended being used in a single-threaded environment as it might cause extra overheads. However, to overcome this in Vectors place one can readily use ArrayDeque.

## SET

### HashSet

    HashSet is an inherent implementation of the hash table data structure or Hashing. The objects that we insert into the HashSet do not guarantee to be inserted in the same order. The objects are inserted based on their hash code.

[

## Agenda

- Intro to Collections
    - Common Collection Interfaces
    - List Interface
    - Queue Interface
    - Set Interface
    - Map Interface

- Intro to Iterators
    - Using Iterators
    - Iterator Methods

- Additonal Concepts
    - Using Custom Object as Key with Hashmap etc

## Introduction

Java Collections Framework provides a set of interfaces and classes to store and manipulate groups of objects.
Collections make it easier to work with groups of objects, such as lists, sets, and maps. In this beginner-friendly
tutorial, we'll explore the basics of Java Collections and how to use iterators to traverse through them.

### 1. Introduction to Java Collections

Java Collections provide a unified architecture for representing and manipulating groups of objects. The Collections
Framework includes interfaces, implementations, and algorithms that simplify the handling of groups of objects.

[Collection PlayList - Video Tutorial](https://drive.google.com/drive/folders/1lLcfZzmKSa0bq_1--OOya0Xk7-y5A9SN?usp=drive_link)

### 2. Common Collection Interfaces

There are several core interfaces in the Collections Framework:

![](https://miro.medium.com/v2/resize:fit:822/1*qgcrVwo8qzF4muOQ-kKB8A.jpeg)

**Collection:** The root interface for all collections. It represents a group of objects, and its subinterfaces include
List, Set, and Queue.

**List:** An ordered collection that allows duplicate elements. Implementations include ArrayList, LinkedList, and
Vector.

**Queue:**: The Queue interface in Java is part of the Java Collections Framework and extends the Collection interface.
Queues typically, but do not necessarily, order elements in a FIFO (first-in-first-out) manner. Among the exceptions are
priority queues, which order elements according to a supplied comparator, or the elements' natural ordering.
Implementations include ArrayDeque, LinkedList, PriorityQueue etc.

**Set:** An unordered collection that does not allow duplicate elements. Implementations include HashSet, LinkedHashSet,
and TreeSet.

**Map:** A collection that maps keys to values. Implementations include HashMap, LinkedHashMap, TreeMap, and Hashtable.

### Example-1  List Interface and ArrayList

The List interface extends the Collection interface and represents an ordered collection of elements. One of the common
implementations is ArrayList. Let's see a simple example:

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("Java");
        myList.add("Python");
        myList.add("C++");

        System.out.println("List elements: " + myList);
    }
}
```

### Example-2 Set Interface and HashSet

The Set interface represents an unordered collection of unique elements. One of the common implementations is HashSet.
Here's a simple example:

```java
import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<String> mySet = new HashSet<>();
        mySet.add("Apple");
        mySet.add("Banana");
        mySet.add("Orange");

        System.out.println("Set elements: " + mySet);
    }
}
```

### Example-3 Map Interface and HashMap

The Map interface represents a collection of key-value pairs. One of the common implementations is HashMap. Let's see an
example:

```java
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("Java", 20);
        myMap.put("Python", 15);
        myMap.put("C++", 10);

        System.out.println("Map elements: " + myMap);
    }
}
```

## Introduction to Iterators

An iterator is an interface that provides a way to access elements of a collection one at a time. The Iterator interface
includes methods for iterating over a collection and retrieving elements.

Let's see how to use iterators with a simple example using a List:

### Example - 1 List Iterator

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("Java");
        myList.add("Python");
        myList.add("C++");

        // Getting an iterator
        Iterator<String> iterator = myList.iterator();

        // Iterating through the elements
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Element: " + element);
        }
    }
}
```

### Example-2 Iterating Over Priority Queue

```java
public class PriorityQueueIteratorExample {
    public static void main(String[] args) {
        // Creating a PriorityQueue with Integer elements
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Adding elements to the PriorityQueue
        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(20);

        // Using Iterator to iterate over elements in PriorityQueue
        System.out.println("Elements in PriorityQueue using Iterator:");

        Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

n this example, we create a PriorityQueue of integers and add three elements to it. We then use an Iterator to iterate
over the elements and print them.

Keep in mind that when using a PriorityQueue, the order of retrieval is based on the natural order (if the elements are
comparable) or a provided comparator. The element with the highest priority comes out first.

It's important to note that the iterator does not guarantee any specific order when iterating over the elements of a
PriorityQueue.

#### Iterator Methods

The Iterator interface provides several methods, including:

- hasNext(): Returns true if the iteration has more elements.
- next(): Returns the next element in the iteration.
- remove(): Removes the last element returned by next() from the underlying collection (optional operation).

Here's an example demonstrating the use of these methods:

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorMethodsExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Iterator<Integer> iterator = numbers.iterator();

        // Using hasNext() and next() methods
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println("Number: " + number);

            // Using remove() method (optional operation)
            iterator.remove();
        }

        System.out.println("Updated List: " + numbers);
    }
}
```

## Additional Concepts

### Hashmap with Custom Objects

Using a HashMap with custom objects in Java involves a few steps. Let's go through the process step by step. Suppose you
have a custom object called Person with attributes like id, name, and age.

- Step 1: Create the Custom Object

```java
public class Person {
    private int id;
    private String name;
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and setters (not shown for brevity)

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "', age=" + age + '}';
    }
}
```

- Step 2: Use Person as a Key in HashMap
  Now, you can use Person objects as keys in a HashMap. For example:

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // Create a HashMap with Person objects as keys
        Map<Person, String> personMap = new HashMap<>();

        // Add entries
        Person person1 = new Person(1, "Alice", 25);
        Person person2 = new Person(2, "Bob", 30);

        personMap.put(person1, "Employee");
        personMap.put(person2, "Manager");

        // Retrieve values using Person objects as keys
        Person keyToLookup = new Person(1, "Alice", 25);
        String position = personMap.get(keyToLookup);

        System.out.println("Position for " + keyToLookup + ": " + position);
    }
}
```

In this example, Person objects are used as keys, and the associated values represent their positions. Note that for
keys to work correctly in a HashMap, the custom class (Person in this case) should override the hashCode() and equals()
methods.

- Step 3: Override hashCode() and equals()

```java
public class Person {
    // ... existing code

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;

        return id == person.id && age == person.age && Objects.equals(name, person.name);
    }
}
```

By overriding these methods, you ensure that the HashMap correctly handles collisions and identifies when two Person
objects are considered equal.

**Important Considerations**

**Immutability:**
It's often a good practice to make the custom objects used as keys immutable. This helps in maintaining the integrity of
the HashMap because the keys should not be modified after being used.

**Consistent hashCode():**

Ensure that the hashCode() method returns the same value for two objects that are considered equal according to the
equals() method. This ensures proper functioning of the HashMap.

**Performance:**
Consider the performance implications when using complex objects as keys. If the hashCode() and equals() methods are
computationally expensive, it might affect the performance of the HashMap.
By following these steps and considerations, you can effectively use custom objects as keys in a HashMap in Java.

### Summary

Java Collections and Iterators are fundamental concepts for handling groups of objects efficiently. Understanding the
different collection interfaces, implementing classes, and utilizing iterators will empower you to work with collections
effectively in your Java applications. Practice and explore the various methods available in the Collections Framework
to enhance your programming skills.

### List

Arraylist
LinkedList
Vector

### Queue

### Set

HashSet -

* Stores elements in hash table.
* Unordered
* Best performance

LinkedHasSet -

* Stores element in HashTable with linked list running through it
* Ordered (Insertion-order)

TreeSet -

* Stores elememt in red black tree(self balancing tree)
* Order the item based on value
* Slower than hashset (logN)

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