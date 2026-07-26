1. Why does ArrayList use an array internally instead of a linked list?

Ans: ArrayList uses an array internally because most applications require fast random access. Arrays store references in contiguous memory, allowing the JVM to calculate an element's address directly using the index, so get(index) is O(1).

A LinkedList, on the other hand, stores elements as nodes connected by references. To access the nth element, it must traverse node by node, making random access O(n).

Although LinkedList can insert or delete a known node efficiently, finding the insertion point usually takes O(n), so in real-world applications ArrayList generally performs better. It also uses less memory and benefits from better CPU cache locality because array elements are stored together.

2. Why can't Java arrays grow?

Ans: A Java array has a fixed size because, when it is created, the JVM allocates one contiguous block of memory large enough to hold all its elements. Once allocated, its size cannot change. If we need more space later, the memory immediately after the array may already be occupied by other objects, so the JVM cannot extend the existing array in place. Instead, it must allocate a new, larger array, copy all the existing elements into it, and discard the old array. This is exactly the strategy used internally by ArrayList.

3. What is the difference between size and capacity?

Ans: Capacity is the number of elements the internal array can currently hold without resizing, whereas size is the number of elements actually stored in the ArrayList.

When size becomes equal to capacity and another element is added, the ArrayList implementation allocates a new internal array with approximately 1.5× the previous capacity, copies all existing elements into the new array using Arrays.copyOf(), updates the internal reference to point to the new array, and the old array becomes eligible for garbage collection.

4. Why is get() O(1)?
5. Why is remove(index) O(n)?
6. Why does ArrayList grow by about 1.5× instead of 2×?

Ans: ArrayList grows by approximately 1.5× because it provides a good balance between memory usage and performance. If it grew by a very small amount, it would need to resize and copy elements too frequently, which is expensive. If it doubled every time, it would reduce the number of resizes but waste a significant amount of unused memory. A growth factor of about 1.5× offers a practical compromise between fewer copy operations and efficient memory utilization.

7. What is the purpose of modCount?
8. What does "fail-fast iterator" mean?
9. Why is elementData marked transient?
10. What happens internally when add() is called and the array is full?
11. Why doesn't ArrayList shrink automatically when many elements are removed?

Ans: ArrayList does not shrink automatically because shrinking would require allocating a smaller array and copying all existing elements. If the list grows again shortly afterward, it would need to allocate and copy again, causing unnecessary overhead. The JDK favors performance and avoids frequent grow-shrink cycles. If the developer wants to reduce memory usage explicitly, they can call trimToSize().

12. What happens when you call clear()?

Ans: When clear() is called, the ArrayList sets its size to 0 and nulls out all references in the internal array. This allows the garbage collector to reclaim memory for the objects that were previously stored in the list. However, the capacity of the internal array remains unchanged, so if new elements are added afterward, they will be added to the existing array without resizing.

13. Why is elementData declared as transient Object[] elementData?

Ans:

14.What is the backing array of an ArrayList?

Ans: The backing array is the internal Object[] elementData used by ArrayList to store all its elements. The ArrayList object manages operations like add(), get(), and remove(), but the actual elements are physically stored in this internal array, which is why it is called the backing array.

When you write:
```
ArrayList<String> list = new ArrayList<>();

list.add("A");
list.add("B");
list.add("C");
```
Internally, the ArrayList object looks like this:
```
ArrayList object
-------------------------
size = 3
elementData ----------+
|
▼
+---------------------+
Index      | 0 | 1 | 2 | 3 | 4 |
+---------------------+
| A | B | C |   |   |
+---------------------+
```

The ArrayList object itself doesn't store "A", "B", "C".

It stores a reference to another object:
```
Object[] elementData;
```
That array is called the backing array because it is the actual storage backing the ArrayList.

>Why not call it just "array"?

Because users don't interact with it directly.

work with:
```
list.add("A");
list.get(0);
list.remove(1);
```

never do:
```
elementData[0]
```
The backing array is an implementation detail hidden behind the ArrayList API.

15. Does elementData store the actual String objects, or does it store references (addresses) to the String objects?

Ans: For non-primitive type( String) it store references. 
So when you add a String to an ArrayList, the backing array (elementData) holds a reference to that String object in memory, rather than a copy of the String itself.

16. Can an ArrayList store primitive data types?

Ans: No. ArrayList stores only objects because its backing storage is an Object[]. When you add a primitive like int, Java automatically boxes it into its wrapper class (Integer) before storing the reference in the backing array.
