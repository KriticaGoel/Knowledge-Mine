ArrayList Master Memory Map

```
                                  ArrayList
                                      │
      ┌───────────────────────────────┼───────────────────────────────┐
      │                               │                               │
      ▼                               ▼                               ▼
 Internal Structure             Memory Layout                 Operations
      │                               │                               │
      ▼                               ▼                               ▼
Object[] elementData          Stack + Heap                    add()
size                          References                      get()
capacity                      Objects                         set()
modCount                      Backing Array                   remove()
transient                      Lazy Allocation                grow()

```

### 1. Purpose
```   
Resizable Array
```
Think:
```
Array
+

Automatic Growth
```
### 2. Internal Fields
```   
    transient Object[] elementData;

    private int size;

    protected transient int modCount;
```
Remember
```
elementData

↓

Actual Object[]
```
```
size

↓

Current elements
```
```
capacity

↓

Length of Object[]
```
```
modCount

↓

Version Number
```
### 3. Memory Layout
```
Stack

list
 │
 ▼

Heap

ArrayList Object

size

modCount

elementData
      │
      ▼

Object[]

+-----+-----+-----+
|ref1 |ref2 |null |
+-----+-----+-----+
   │      │
   ▼      ▼

Person   Student
```
Golden Rule
```
ArrayList NEVER stores objects.

It stores references.
```

### 4. Capacity vs Size
```
   Capacity

     ↓

 How many references can Object[] store?
``` 
```
    Size

     ↓

How many references currently exist?
```
Example
```
Capacity = 10

Size = 4
+----+----+----+----+------+------+
| A  | B  | C  | D  | null | null |
+----+----+----+----+------+------+
```
### 5. Constructors
Default
```
   new ArrayList<>();
```
Initial
```
capacity = 0
```
First add()
```
10
```
Explicit
```
new ArrayList<>(20);
```
Initial
```
capacity = 20
```
No lazy allocation.

### 6. add()
```
   User

    ↓
    
    add(E)
    
    ↓
    
    modCount++
    
    ↓
    
    private add()
    
    ↓
    
    capacity check
    
    ↓
    
    grow()
    
    ↓
    
    store reference
    
    ↓
    
    size++
```   
7. grow()
```
   Old Capacity
        
        ↓
        
      1.5x
        
        ↓
        
     New Array
        
        ↓
        
    Arrays.copyOf()
        
        ↓
        
    Old array becomes eligible for GC
```

Formula
```
old + old/2
```
Examples
```
10

↓

15

↓

22

↓

33
```
### 8. ensureCapacity()

Purpose
```
Reserve memory

Before insertion
```
```
ensureCapacity(100)
```
Result
```
Capacity =100

Size =0
```
No elements inserted.

### 9. trimToSize()

Purpose
```
Release unused memory
```
Example
```
Capacity =100

Size =15

After

trimToSize()

↓

Capacity =15
```
### 10. Time Complexity

| Operation     | Complexity     | Why                      |
| ------------- | -------------- | ------------------------ |
| get()         | O(1)           | Direct index calculation |
| set()         | O(1)           | Replace reference        |
| add() end     | Amortized O(1) | Occasional resize        |
| add(index)    | O(n)           | Shift elements           |
| remove(index) | O(n)           | Shift elements           |
| contains()    | O(n)           | Linear search            |


11. Growth Strategy

Why not 2x?
```
2x

↓

Memory Waste
```
Why 1.5x?
```
Less memory

+

Less GC

+

Better CPU Cache
```
### 12. Why Object[] ?

Because
```
Generics

↓

Type Erasure

↓

E disappears

↓

Everything becomes Object
```
13. Why transient?
```   
elementData

↓

Capacity

↓

Not serialized
```
Only actual elements
```
size

↓

Serialized
```
### 14. Serialization
```
RAM

↓

File

↓

Network

↓

Database
```
Custom serialization writes only

size elements

not

capacity
### 15. modCount

Think

Version Number

Not

Thread Safety

Iterator
```
expectedModCount

↓

Compare

↓

Current modCount

↓

Mismatch

↓

ConcurrentModificationException
```
### 16. Arrays.copyOf()
```    
Arrays.copyOf()

↓

System.arraycopy()

↓

Native Method

↓

Bulk Memory Copy

↓

Very Fast
```
Copies
```
References
```
NOT
```
Objects
```
### 17. Memory During Resize

Before
```
Object[]

↓

ref1
ref2
ref3
```
After
```
New Object[]

↓

Same ref1
Same ref2
Same ref3
```
Objects

Never Move

Only references move.

### 18. Complete add() Flow
```    
User

↓

list.add(A)

↓

modCount++

↓

size==capacity ?

      │

  Yes ▼ No

grow()

↓

Arrays.copyOf()

↓

System.arraycopy()

↓

Copy References

↓

Store New Reference

↓

size++

↓

Return true
```
19. Interview Traps

| Question                       | Correct Answer                                            |
| ------------------------------ | --------------------------------------------------------- |
| Does ArrayList store objects?  | ❌ No. Stores references.                                  |
| Does resize copy objects?      | ❌ No. Copies references only.                             |
| Is modCount for thread safety? | ❌ No. Version counter for fail-fast iterators.            |
| Why `Arrays.copyOf()`?         | Uses native `System.arraycopy()` for optimized bulk copy. |
| Why `transient`?               | Avoid serializing unused capacity.                        |
| Default capacity?              | 0 initially, first `add()` allocates 10.                  |
| Growth factor?                 | Approximately 1.5×.                                       |



### 🧠 One-Line Story (The Entire ArrayList in 30 Seconds)

```  
User calls add()

↓

ArrayList increments modCount

↓

Checks whether the backing Object[] has free space

↓

If full, allocates a new array about 1.5× larger

↓

Copies only object references using Arrays.copyOf() → System.arraycopy()

↓

Stores the new reference at the next index

↓

Increments size

↓

Old backing array becomes eligible for garbage collection
```