## ArrayList Interview Questions & Answers

---

### 1. Why does ArrayList use an array internally instead of a linked list?

<details>
<summary>Answer</summary>

ArrayList uses an array internally because most applications require **fast random access**.

Arrays store references in **contiguous memory**, allowing the JVM to calculate an element's address directly using the index.

Therefore:

```java
list.get(index);
```

is **O(1)**.

A `LinkedList`, on the other hand, stores elements as nodes connected by references. To access the nth element, it must traverse node by node, making random access **O(n)**.

Although `LinkedList` can insert or delete a known node efficiently, finding the insertion point usually takes **O(n)**, so in real-world applications `ArrayList` generally performs better.

It also:

- Uses less memory.
- Has better CPU cache locality.
- Provides much faster random access.

</details>

---

### 2. Why can't Java arrays grow?

<details>
<summary>Answer</summary>

A Java array has a **fixed size** because, when it is created, the JVM allocates one **contiguous block of memory** large enough to hold all its elements.

Once allocated, its size cannot change.

If more space is needed later, the memory immediately after the array may already be occupied by other objects, so the JVM cannot extend the existing array.

Instead it must:

1. Allocate a new larger array.
2. Copy all existing elements.
3. Discard the old array.

This is exactly the strategy used internally by `ArrayList`.

</details>

---

### 3. What is the difference between size and capacity?

<details>
<summary>Answer</summary>

- **Capacity** = Number of elements the internal array can currently hold without resizing.
- **Size** = Number of elements actually stored.

When:

```
size == capacity
```

and another element is added:

- A new internal array (~1.5× larger) is created.
- Existing elements are copied using `Arrays.copyOf()`.
- `elementData` points to the new array.
- The old array becomes eligible for Garbage Collection.

</details>

---

### 4. Why is `get()` O(1)?

<details>
<summary>Answer</summary>

Because `ArrayList` stores elements in an array.

The JVM can directly calculate the memory location using:

```
baseAddress + (index × elementSize)
```

No traversal is required.

Therefore:

```java
list.get(index);
```

is **O(1)**.

</details>

---

### 5. Why is `remove(index)` O(n)?

<details>
<summary>Answer</summary>

Removing an element leaves a gap inside the array.

All elements after that index must shift one position left.

Example:

```
[A][B][C][D]
```

Remove index 1:

```
[A][C][D]
```

Internally:

```
C -> index 1
D -> index 2
```

The shifting makes the operation **O(n)**.

</details>

---

### 6. Why does ArrayList grow by about 1.5× instead of 2×?

<details>
<summary>Answer</summary>

A growth factor of approximately **1.5×** provides a good balance between memory usage and performance.

- Smaller growth → frequent resizing and copying.
- Double growth → wastes significant memory.

A 1.5× growth minimizes both memory waste and copy operations.

</details>

---

### 7. What is the purpose of `modCount`?

<details>
<summary>Answer</summary>

`modCount` is a **structural modification counter**.

It increments whenever the structure of the `ArrayList` changes, such as:

- add()
- remove()
- clear()
- trimToSize()

Iterators compare their saved `expectedModCount` with the current `modCount`.

If they differ, a `ConcurrentModificationException` is thrown.

Its purpose is to support the **fail-fast iterator** mechanism.

</details>

---

### 8. What does "fail-fast iterator" mean?

<details>
<summary>Answer</summary>

A fail-fast iterator immediately detects structural modification made outside the iterator during iteration.

Example:

```java
for(String s : list){
    list.add("X");
}
```

The iterator notices:

```
expectedModCount != modCount
```

and throws:

```
ConcurrentModificationException
```

This helps detect programming bugs early.

</details>

---

### 9. Why is `elementData` marked `transient`?

<details>
<summary>Answer</summary>

`elementData` is marked `transient` so Java's default serialization mechanism does not serialize the entire backing array.

Only the actual elements (`size`) are serialized.

This avoids writing unused capacity, making serialization more efficient.

</details>

---

### 10. What happens internally when `add()` is called and the array is full?

<details>
<summary>Answer</summary>

When the backing array is full:

1. Calculate new capacity (~1.5×).
2. Allocate a new array.
3. Copy existing elements.
4. Update `elementData`.
5. Insert the new element.
6. Old array becomes eligible for GC.

</details>

---

### 11. Why doesn't ArrayList shrink automatically when many elements are removed?

<details>
<summary>Answer</summary>

Shrinking requires:

- Allocating a smaller array.
- Copying existing elements.

If the list grows again shortly afterward, the JVM would repeat the entire process.

To avoid repeated grow-shrink cycles, the JDK does **not** shrink automatically.

Developers can explicitly call:

```java
trimToSize();
```

</details>

---

### 12. What happens when you call `clear()`?

<details>
<summary>Answer</summary>

When `clear()` is called:

- `size` becomes `0`.
- Every reference in the backing array is set to `null`.
- Objects become eligible for Garbage Collection.

The backing array capacity remains unchanged.

Future additions reuse the same array.

</details>

---

### 13. Why is `elementData` declared as `transient Object[] elementData`?

<details>
<summary>Answer</summary>

Because:

- `Object[]` allows storing references to any object type.
- `transient` prevents unused capacity from being serialized.

This keeps serialization efficient while still supporting generic collections.

</details>

---

### 14. What is the backing array of an ArrayList?

<details>
<summary>Answer</summary>

The backing array is the internal:

```java
Object[] elementData;
```

It stores the actual elements.

Example:

```java
ArrayList<String> list = new ArrayList<>();

list.add("A");
list.add("B");
list.add("C");
```

Internally:

```
ArrayList
---------------------
size = 3

elementData ---->

+-------------------------+
| A | B | C |   |   |
+-------------------------+
```

The `ArrayList` object itself does **not** store `"A"`, `"B"` and `"C"`.

It only stores a reference to the backing array.

Users interact through:

```java
list.add()
list.get()
list.remove()
```

They never directly access:

```java
elementData[0]
```

Hence it is called the **backing array**.

</details>

---

### 15. Does `elementData` store actual String objects or references?

<details>
<summary>Answer</summary>

It stores **references**, not the actual objects.

Example:

```java
list.add("Hello");
```

The backing array stores a reference to the `String` object in memory.

</details>

---

### 16. Can an ArrayList store primitive data types?

<details>
<summary>Answer</summary>

No.

`ArrayList` stores only objects because its backing storage is:

```java
Object[]
```

Primitive values are automatically boxed.

Example:

```java
ArrayList<Integer> list = new ArrayList<>();

list.add(10);
```

Internally:

```
10

↓

Integer.valueOf(10)
```

The backing array stores a reference to the `Integer` object.

</details>

---

### 17. Why did Oracle write `add(e, elementData, size)` instead of putting everything inside `add()`?

<details>
<summary>Answer</summary>

There are four reasons.

#### 1. Single Responsibility

```java
public boolean add(E e) {
    modCount++;
    add(e, elementData, size);
    return true;
}
```

The public method:

- increments `modCount`
- delegates work
- returns success

The private method performs insertion.

#### 2. Better JIT Optimization

HotSpot performs better when methods remain relatively small.

Smaller methods are easier for JVM inlining.

#### 3. Code Reusability

Insertion logic can be reused.

#### 4. Easier Maintenance

Only one method needs modification if insertion logic changes.

</details>

---

### 18. Why is `modCount` incremented before the insertion logic?

<details>
<summary>Answer</summary>

`modCount` is the structural modification version counter.

The public `add()` increments it before delegating work so every structural modification consistently updates the version.

Iterators compare:

```
expectedModCount

vs

modCount
```

If they differ:

```
ConcurrentModificationException
```

is thrown.

This is a **best-effort debugging mechanism**, **not** a thread-safety feature.

</details>

---

### 19. Is `modCount` a Thread Safety Feature?

<details>
<summary>Answer</summary>

**No.**

`modCount` does **not** provide:

- synchronization
- locking
- atomicity

It merely detects structural modifications during iteration.

Example:

Initial state:

```
size = 0
modCount = 0
```

Thread 1:

```java
modCount++;
```

Thread 2:

```java
modCount++;
```

Both threads still write into:

```
elementData[0]
```

One update is overwritten.

Final memory:

```
elementData[0] = "A"

size = 1

modCount = 2
```

`modCount` increased correctly, yet data became corrupted.

Therefore:

```
modCount ≠ Thread Safety
```

Use:

- external synchronization
- synchronized collections
- `CopyOnWriteArrayList`

for thread-safe access.

</details>

---

### 20. Why is it called "Best Effort"?

<details>
<summary>Answer</summary>

Fail-fast cannot be guaranteed.

Example:

CPU 1

```
expectedModCount = 5
```

CPU 2

```
modCount = 6
```

Due to CPU caches, CPU 1 may temporarily still observe:

```
modCount = 5
```

No exception is thrown immediately.

Another timing issue:

```
Thread A

checks modCount

↓

CPU switch

↓

Thread B modifies list

↓

CPU switch

↓

Thread A continues
```

The modification happened **after** the check.

Therefore the JDK documentation states:

> Fail-fast behavior cannot be guaranteed.

It is a **best-effort debugging mechanism**, not a correctness guarantee.

</details>

---

### 21. (Reserved)

<details>
<summary>Answer</summary>

_To be added._

</details>


### 22. (Reserved)

<details>
<summary>Answer</summary>

_To be added._

</details>