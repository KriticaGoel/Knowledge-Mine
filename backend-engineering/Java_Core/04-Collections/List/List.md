
                 Iterable (I)
                     │
                     ▼
                 Collection (I)
                     │
                     ▼
                    List (I)
                     │
                     ▼
             -----------------------------------------
            │                   │                     │  
            ▼                   ▼                     ▼
        ArrayList(C)          LinkedList(C)         Vector
                                                      ▲
                                                      │
                                                     Stack


## List

* It is an Interface extends Collection interface
* Ordered Collection: store and access element in a sequential manner
* Include duplicate elements
* Insertion order is preserved
* Element can be accessed by Index
* Full visibility and control over the ordering of elements
* Implementations include ArrayList, LinkedList, and Vector.

After java 21

                 Iterable (I)
                     │
                     ▼
                 Collection (I)
                     │
                     ▼
                 Sequence List (I)
                     │
                     ▼
                    List (I)
                     │
                     ▼
             -----------------------------------------
            │                   │                     │  
            ▼                   ▼                     ▼
        ArrayList(C)          LinkedList(C)         Vector
                                                      ▲
                                                      │
                                                     Stack

### Methods of Sequence list are
1. getFirst()
2. getLast()
3. addFirst()
4. addLast()
5. removeFirst()
6. removeLast()

### Methods of List Interface are


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
* **copyOf(Object)** -Returns an unmodifiable list containing n elements.
* ** replaceAll**:
* **subList(int startIndex, int endIndex)**: -integers.subList(3,6)
* sort(Comparator<? super E> c)
* replaceAll(UnaryOperator<E> operator)
* listIterator


### ArrayList-

    Dynamic arrays-The size of an ArrayList is increased automatically if the collection grows or shrinks if the objects are removed from the collection

### Vector

    Dynamic arrays -It is a thread-safe class.This is not recommended being used in a single-threaded environment as it might cause extra overheads

### LinkedList

    LinkedList is class is an implementation of a doubly-linked list data structure

### Stack

    Stack is a class is based on the basic principle of last-in-first-out. This is a legacy class. This inherits from a Vector class. It is also a thread-safe class. This is not recommended being used in a single-threaded environment as it might cause extra overheads. However, to overcome this in Vectors place one can readily use ArrayDeque.


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

## ArrayList

#### WHy Arraylist?
1. Once array created, the size will never be changes.
2. Array occupy ccontiguous block of memory.
3. This is a root problem arraylist resolved.

### how the problem resolved?

Old array int[] arr = new int[5];

Now 2 more element need to add in array.

Create new array of bigger size int arr1 = new int[7]

Copy everything from old array to new array

Discard old array

This is how array list grow.

ArrayList

|Operation| Method | time Complexity | how internally it work |
|Insertion |  ||| 
|Deletion |  |||
|fetching| get()   |O(1)|All objects stoes in contiguous slots (array stores reference to objects) because of which find 4 means base address+ 4* reference size. JVM directly calculate the location. no searching |
|update|    |||


### Why not linked list?

1. Linklist contain adrress of next node and its not contiguous block of memory. 
2. Linklist can grow very eaily

why invent ArrayList?

Because retrieval in linked list is slow. we need to traverse the link list node by node to get value the value. However Arraylist get(1000) means address + index*size. we can directly jump on index 10000. 




          




