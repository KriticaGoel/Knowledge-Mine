Agenda:
```
✅ HashMap Fundamentals
Why HashMap exists
Bucket array
Lazy initialization
Capacity vs Size
Why initial capacity is 16
Why capacity is always a power of 2
✅ Hash Calculation
hashCode()
equals()
Bit mixing (h ^ (h >>> 16))
Bucket calculation (hash & (capacity - 1))
✅ Collision Handling
Linked List
Why collisions happen
Search complexity
✅ Performance
Load Factor
Threshold
Resize
Rehashing
Amortized O(1)
✅ Java 8 Optimizations
Treeification
Why threshold is 8
Why minimum capacity is 64
Untreeification
Why threshold is 6
✅ Advanced Concepts
Mutable keys
Immutable keys
modCount
Fail-Fast Iterator
ConcurrentModificationException
```
## Introduction
   
### What is HashMap?

HashMap is a data structure store value in key value pair.

### Why do we need HashMap?

Searching one by one Time complexity is O(n) very slow for large dataset.
The idea of HashMap, Convert the key into an address. so there is no need to search every element.
Jump directly to the location where it should be using key.

Time Complexity become O(1).

### ArrayList vs HashMap

Arraylist searching element is O(n)
HashMap searching element is O(1)

### Internal Structure

Default capacity = 16

0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15

Each index is called a bucket. that means we have 16 buckets

Node<K,V>[] table = new Node[16];

Each bucket can hold one or more node

### What is inside a Node?

```
static class Node<K,V>{

    final int hash;

    final K key;

    V value;

    Node<K,V> next;
}
```

Memory
```
+------------------+
hash = 102345
key = "A"
value = 5000
next ------>
+------------------+
```

This means buckets can store a linked list if multiple keys land in the same bucket.

### How does put() work?
#### Step 1: Declare HashMap
```
HashMap<String, Integer> map = new HashMap<>();
```
Heap Memory
```
               +----------------------+
               |    HashMap Object    |
               |----------------------|
               | size = 0             |
               | loadFactor = 0.75    |
               | threshold = 0        |
               | table = null         |
               +----------------------+
```
**There is NO bucket array yet.**

In java 8 and later, JVM immediately does not create 16 buckets on HashMap declare

```
map
 │
 ▼
table = null
size = 0
```

No bucket array is allocated yet. This is called lazy initialization and saves memory.

#### Step 2: When first element added in HashMap. 

```
map.put("Apple",100);
```
Java creates:
```
Node[] table = new Node[16];
```
Now the 16 buckets exist.

Suppose "Apple" maps to bucket 10.

Heap
```
                HashMap Object
                +--------------------+
                | table ------------+----------------------+
                +--------------------+                      |
                                                           |
                                     Node[] table (16 buckets)
                                     +-------------------------+
                                     |0| null                 |
                                     |1| null                 |
                                     |2| null                 |
                                     |...                    |
                                     |10| ------------------+ |
                                     |...                    |
                                     |15| null              |
                                     +-------------------------+
                                                       |
                                                       ▼
```
Now bucket 10 points to a Node.

#### Step 3: Node Object

Java creates a new node:

```
Node

hash = xxxx
key = Apple
value = 100
next = null
```

Then it stores the reference to that node in bucket 10.

The bucket does not store the data.

The bucket stores a reference to a Node object.

```
table

0 → null
1 → null
2 → null
...
10 ───────────► Node
                │
                ├── key = Apple
                ├── value = 100
                └── next = null
11 → null
```
#### Step 4: Collision
Now insert:

```
map.put("Orange", 200);
```

Suppose "Orange" also maps to bucket 10.

Java creates another node and links it using the next reference:

Memory became

```
Bucket Array

10
 |
 ▼
+-----------+
| Apple     |
| value=100 |
| next -----+------+
+-----------+      |
                   ▼
              +-----------+
              | Orange    |
              | value=200 |
              | next=null |
              +-----------+
```

This is a linked list inside that bucket.

#### Step 5: get("Orange")

HashMap computes the bucket.
```
Bucket 10
```
Then it walks the linked list.
```
Apple

↓

Orange
```
Is Apple?

❌ No

Move to next.

Orange?

✅ Yes

Return 200

#### Step 6: Two HashMaps

Every HashMap object has its own bucket array.

```
HashMap<String, Integer> map1 = new HashMap<>();
HashMap<String, Integer> map2 = new HashMap<>();
```
initially 
```
map1.table = null
map2.table = null
```
No buckets are created yet.

Now execute:
```
map1.put("Apple", 100);
```
Memory becomes:
```
Heap

map1
│
▼
table1 (16 buckets)

0
1
2
...
15

map2 is still:

map2
│
▼
table = null
```
Now execute:

map2.put("Orange", 200);

Now memory looks like:
```
Heap

map1
│
▼
table1 (16 buckets)

0
1
2
...
15


map2
│
▼
table2 (16 buckets)

0
1
2
...
15
```
Notice:

* map1 has its own bucket array.
* map2 has a different bucket array.
* They do not share buckets.
* These are completely independent. 
* Changing map never changes map2.

calculating the hash,
finding the bucket,
handling collisions,
resizing,
linking nodes.

Compute hashCode().
Mix the bits.
Calculate the bucket index.
Go directly to that bucket.
Compare hashes.
If hashes match, compare keys using equals().
Return the value when a matching key is found.
### How hashmap calculate the bucket

#### Step 1:  Calculate the hashCode()

A hashCode() is simply an integer (32-bit int) that Java uses to represent an object.

```
String s = "Apple";

System.out.println(s.hashCode());
```
Output: 
```
63476538
```

>hashCode() does not uniquely identify an object. Different objects can have the same hash code (called a collision).

HashMap uses this number to decide which bucket the key should go into.

##### Who generates the hash code?
1. Case 1: You don't override hashCode()
```
class Employee {
    int id;
    String name;
}
```

```
Employee e = new Employee();
System.out.println(e.hashCode());
```

The default implementation from Object is used.

Historically, it is related to the object's identity (not something you should rely on being the memory address).

So two different objects usually produce different hash codes:
```
Employee e1 = new Employee();
Employee e2 = new Employee();
```
```
e1.hashCode() → 356573597
e2.hashCode() → 1735600054
```
2. Case 2: The class overrides hashCode()

For String, Java overrides it.

For example:
```
"ABC".hashCode()
```
Java calculates it using this formula:
```
hash = 31 × hash + character
```
Let's calculate "ABC".

Initially:
```
hash = 0
```
Character 'A' = 65
```
hash = 31 × 0 + 65
= 65
```
Character 'B' = 66
```
hash = 31 × 65 + 66
= 2081
```
Character 'C' = 67
```
hash = 31 × 2081 + 67
= 64578
```
So:
```
"ABC".hashCode()
```
returns:
```
64578
```

Why multiply by 31?

Java's designers chose 31 because:

* It is a prime number.
* It gives a good distribution of hash values.
* Multiplication by 31 is very efficient for the JVM.

Why do we override hashCode() in our own classes?
```
Suppose you have:

class Employee {
int id;
String name;
}
```
You create:
```
Employee e1 = new Employee(101, "John");
Employee e2 = new Employee(101, "John");
```
Logically, they represent the same employee.

If you don't override equals() and hashCode(), HashMap treats them as different keys because they are different objects.

By overriding both methods based on id (or whatever defines equality), HashMap can correctly recognize that they represent the same key.

Different objects can legitimately have the same hash code.


hashCode()
```
map.put("ABC", 100);

"ABC".hashCode()

↓

64578
```
This is a huge number.

But we have only 16 buckets.

We cannot store something at:
```
Bucket 64578
```
because it doesn't exist.

So Java compresses it.

#### Step 2: Java mixes the bits:

Java spreads the bits:

```
hash = h ^ (h >>> 16)   
```

Why?
To reduce collisions by mixing high and low bits.

#####  What is >>> 16?

Suppose we have a simple binary number (using only 8 bits for illustration):
```
10110011
```
If we shift it right by 4 positions:
```
10110011
```
>>> 4
```
00001011
```

The rightmost bits were dropped, and zeros were added on the left.

>>> means "shift bits to the right."

#####  What is ^ (XOR)?

| Bit 1 | Bit 2 | Result |
| ----- | ----- | ------ |
| 0     | 0     | 0      |
| 0     | 1     | 1      |
| 1     | 0     | 1      |
| 1     | 1     | 0      |

Remember this simple rule:

>Same = 0, Different = 1

Example:
```
10110011
```
XOR
```
00001011
```
Result:
```
10111000
```

#####  Why Does HashMap Do This?

Suppose two keys have these hash codes:
```
A = 123456780
B = 123456781
```
The lower bits might be very similar.

HashMap does:

h ^ (h >>> 16) mixes the high-order bits into the low-order bits so that the bucket index is distributed more evenly, especially because HashMap later uses only the lower bits to compute the bucket index.

##### How mixes the high-order bits into the low-order bits?
1.Imagine a 32-bit hash

Suppose the hash is:
```
High 16 bits          Low 16 bits

AAAA BBBB CCCC DDDD   EEEE FFFF GGGG HHHH
```
Think of it as two halves:
```
+----------------+----------------+
| Higher 16 bits | Lower 16 bits  |
+----------------+----------------+
```
2. What does >>> 16 do?
```
h >>> 16
```
It shifts everything 16 positions to the right.

So:

Before:
```
AAAA BBBB CCCC DDDD   EEEE FFFF GGGG HHHH
```
After shifting:
```
0000 0000 0000 0000   AAAA BBBB CCCC DDDD
```

The higher 16 bits moved down into the lower 16-bit position.

3. Now do XOR (^)

Java performs:
```
hash = h ^ (h >>> 16);
```
Let's compare them:

Original:
```
AAAA BBBB CCCC DDDD   EEEE FFFF GGGG HHHH
```
Shifted:
```
0000 0000 0000 0000   AAAA BBBB CCCC DDDD
```
Now XOR:
```
Higher 16 bits
AAAA BBBB CCCC DDDD

Lower 16 bits
EEEE FFFF GGGG HHHH
XOR
AAAA BBBB CCCC DDDD
--------------------
Mixed Lower 16 bits
```
The upper 16 bits stay the same, but the lower 16 bits become a combination of the original lower bits and the original higher bits.

That's why we say the higher bits are "mixed into" the lower bits.

#### Step 3: Find bucket index:

##### Method 1 (Easy to Understand)

We could do:
```
index = hash % 16;
```
Example:
```
64578 % 16 = 2
```
So store it in:
```
Bucket 2
```
This works.

##### But Java Doesn't Use %

Instead it uses:
```
index = hash & (capacity - 1)
```

If the capacity is 16, the result is a bucket number between 0 and 15.

If capacity = 16:

```
capacity-1 = 15
Binary of 15 = 8 + 4 + 2 + 1
1111
```
Because 16 is a power of 2.

Now suppose the hash is:
```
29
```
Binary:
```
11101
```
```
index = hash & (capacity - 1)
index = 11101 & 1111
```

Now Java does:
```
 11101
001111
------
 01101
```
The result is:
```
13
```
So:
```
Bucket 13
```

For a capacity of 16, Java is effectively looking at the last 4 bits.

Why 4?

Because:
```
2⁴ = 16
```
For capacity 32, it looks at the last 5 bits.

Because:
```
2⁵ = 32
```
For capacity 64, it looks at the last 6 bits.

### Why use & instead of %
Method 1: 
```
hash % capacity
```
Java uses:
```
hash & (capacity-1)
```

Because the & (bitwise AND) operation is extremely fast for the CPU.

This works only when capacity is a power of two:
```
16
32
64
128
...
```

That's why HashMap capacities are powers of two.

When the capacity is always a power of two (16, 32, 64...), using:

hash & (capacity - 1)

produces the same bucket range as modulo but more efficiently.


Summary : 

1. Call key.hashCode() to get an integer.
2. Mix the hash bits using h ^ (h >>> 16) to improve distribution.
3. Compute the bucket index with: hash & (capacity - 1)
4. Since the capacity is always a power of two, the result is always a valid bucket index.


### ArrayList grows by 1.5×. Does HashMap double?

ArrayList Growth factor = 1.5×

Because it stores elements one after another, increasing by 50% balances memory use and copying cost.

HashMap Growth factor = 2× (double)

Why?

Because the bucket index calculation:
```
hash & (capacity - 1)
```
works correctly and efficiently only when the capacity is a power of two.

So HashMap always doubles its capacity.

Now Java can use 5 bits instead of 4 bits.

That spreads entries across more buckets, reducing collisions.

That's one reason HashMap doubles its capacity instead of growing by 1.5× like ArrayList.


### Why do we need both hashCode() and equals()

hashCode()  define in which bucket value is present:
```
Apple.hashCode()  ----> Bucket 10

Orange.hashCode() ----> Bucket 10
```
They end up in the same bucket.

To distinguish them, HashMap calls:
```
equals()
```
The rule is:

* hashCode() decides which bucket to search.
* equals() decides which key inside that bucket matches.

equals() finds the exact key
```
Bucket 10

Apple
↓
Orange
```
Searching for "Orange":
```
Apple.equals("Orange") ?

False ❌

↓

Orange.equals("Orange") ?

True ✅
```
Return:
```
200
```

If two objects are equal according to equals():
```
a.equals(b) == true
```
then:
```
a.hashCode() == b.hashCode()
```
must also be true.

The reverse is not required:
```
Same hashCode
≠
Same object
```

### get() Operation
Suppose:
```
map.get("Apple");
```
Steps:

1. Compute hashCode().
2. Mix the bits.
3. Calculate the bucket index.
4. Go directly to that bucket.
5. Compare hashes.
6. If hashes match, compare keys using equals().
7. Return the value when a matching key is found.

This is why both hashCode() and equals() are required.


### Collision

If the same bucket has two keys, for example "Orange", with different values, how HashMap handles it?

#### Case 1: Same key, different value
```
map.put("Orange", 100);
map.put("Orange", 500);
```
HashMap checks:

equals()

It sees the key "Orange" already exists.

It updates the value.

Result:
```
Orange → 500
```
Only one entry remains.


#### Case 2: Different keys in the same bucket

```
map.put("Apple", 100);
map.put("Orange", 200);
```

Suppose:

```
Apple

index 5
```

Now:

```
Orange

index 5
```

Two keys want the same bucket.

This is called a collision.

```
Bucket 5

Apple -> 100

↓

Orange -> 200
```
Each key has its own node.

During get("Orange"), HashMap:

1. Goes directly to bucket 5.
2. Checks "Apple".equals("Orange") → No.
3. Moves to the next node.
4. "Orange".equals("Orange") → Yes.
5. Returns 200.

### Collision Handling 

Before Java 8:
```
Bucket

Apple

↓

Orange

↓

Mango

↓

Banana
```
A linked list.

Searching becomes:
```
O(n)
```
Java 8 introduced an optimization.

If:

1. A bucket has more than 8 nodes, and
2. The table capacity is at least 64

then Java converts that linked list into a Red-Black Tree.

Java converts the linked list into a Red-Black Tree.
```
         Mango
        /     \
    Apple     Orange
         \
        Banana
```
Searching becomes:
```
O(log n)
```

This avoids very slow performance when many keys collide.

### Resize (Rehashing)

#### What is Capacity?

Capacity means:
```
How many buckets exist.
```
Initially:
```
Bucket 0
Bucket 1
...
Bucket 15
```
Total buckets = **16**


####  What is Size?

Size means:
```
How many key-value pairs are stored.
```
Example:
```
map.put("Apple",100);
map.put("Orange",200);
map.put("Mango",300);
```
Then:
```
Capacity = 16
Size = 3
```

#### What is Load Factor?

Load Factor tells HashMap:
```
Load Factor is the ratio that determines when a HashMap should resize. The default value is 0.75, meaning that when the number of stored entries reaches 75% of the current bucket capacity, the HashMap doubles its capacity to reduce collisions and maintain near O(1) performance.
```
Default:
```
Load Factor = 0.75
```
It means:
```
When the table is about 75% full, increase its capacity.
```

##### Why 75%?

Imagine a parking lot with 16 parking spaces.

Would you wait until all 16 are occupied before building another parking area?

No.

Because when the parking lot is almost full:

* More cars compete for nearby spaces.
* More congestion.
* More collisions.

HashMap works the same way.

#### Threshold

HashMap calculates:
```
Threshold = Capacity × Load Factor
```
Initially:
```
Capacity = 16
Load Factor = 0.75
```
So:
```
16 × 0.75 = 12
```
Therefore:
```
Threshold = 12
```
This means:

* Up to 12 entries → no resize.
* Inserting the 13th entry → resize.

##### But why resize when 3 buckets are still empty?

Because entries are not evenly distributed.

Example:
```
Bucket 0 → 2 entries
Bucket 1 → 0
Bucket 2 → 3 entries
Bucket 3 → 1
Bucket 4 → 0
...
```
Just because there are empty buckets **doesn't mean future keys will go there**.

Future keys may hash to buckets that are already crowded.

If Java waited until all 16 buckets were "used", collisions could become much worse.

So it resizes before performance starts degrading too much.

##### After Resize

When the 13th entry is inserted:
```
16 buckets
```
becomes
```
32 buckets
```
Then Java recalculates the bucket for every existing entry (rehashing), spreading them across more buckets and reducing collisions.

Default values:
```
Capacity = 16

Load Factor = 0.75
```
Threshold:
```
16 × 0.75

=12
```
When inserting the 13th entry:
```
Resize

16

↓

32
```
All entries are redistributed because the bucket calculation depends on the capacity.

This process is called rehashing.

### Why Load Factor = 0.75?

Think of two extremes:

Load factor = 1.0

* Fewer empty buckets.
* More collisions.
* Slower lookups.

Load factor = 0.25

* Very few collisions.
* Very large memory usage.

A load factor of 0.75 provides a practical balance between speed and memory usage.

#### What happens during resize?

##### Step 1: Create a new bucket array

Old:
```
16 Buckets
```
New:
```
32 Buckets
```
So Java allocates a completely new array.

#####  Step 2: Visit every existing entry

Suppose you have:

12 existing entries
```
Apple
Orange
Mango
Banana
...
```
HashMap must visit every one.
```
Apple ✔
Orange ✔
Mango ✔
...
12th Entry ✔
```
#####  Step 3: Recalculate the bucket

It does not recalculate hashCode().

Each Node already stores the hash.

```
class Node<K,V> {

    final int hash;   // Already stored

    K key;

    V value;

    Node next;
}
```
So during resize:

❌ It does NOT call:
```
key.hashCode()
```
again.

Instead, it reuses the stored hash and computes the new bucket index because the capacity has changed.

Example:

Old capacity:
```
16

Apple → Bucket 2
```
New capacity:
```
32

Apple → Bucket 18
```
The hash stays the same.

Only the **bucket index** changes.

###### Time Complexity

Suppose there are N entries.

HashMap must process every entry once.

Therefore:
```
Resize = O(n)
```
where n = number of entries in the map.

##### Then why put() is O(1)?

Because resizing doesn't happen on every insertion.

Example:
```
put() 1  → O(1)
put() 2  → O(1)
put() 3  → O(1)
...
put()12 → O(1)
put()13 → O(n)  ← Resize happens
put()14 → O(1)
put()15 → O(1)
...
put()24 → O(1)
put()25 → O(n)  ← Next resize
```
Most insertions are cheap.

Only a few are expensive.

This is called amortized O(1).

>After doubling from 16 to 32 buckets, some entries stay in the same bucket while others move exactly 16 positions ahead (for example, bucket 2 → bucket 18)?

**Before Resize**

Capacity = 16
```
capacity - 1 = 15

Binary:

1111
```
HashMap calculates:
```
index = hash & 15
```
Only the **last 4 bits** determine the bucket.

**After Resize**

Capacity = 32
```
capacity - 1 = 31

Binary:

11111
```
Now HashMap calculates:
```
index = hash & 31
```
Now it uses the **last 5 bits** instead of 4.

Example

Suppose the last 5 bits of a hash are:
```
10010
```
**Before resize (16 buckets)**

Mask:
```
01111
```
```
10010
01111
-----
00010
```
Bucket = 2

**After resize (32 buckets)**

Mask:
```
11111
```
```
10010
11111
-----
10010
```
Binary 10010 = 18

So the entry moves:
```
Bucket 2

↓

Bucket 18
```
Notice:
```
18 = 2 + 16
```

**Another Example**

Suppose the 5th bit is 0.

Hash:
```
00010
```
Before:
```
00010 & 01111 = 2
```
After:
```
00010 & 11111 = 2
```
It stays in Bucket 2.

**The Beautiful Optimization**

When HashMap doubles from 16 → 32, every entry has only two possibilities:

* Stay in the same bucket, or
* Move exactly oldCapacity positions ahead.
```
Example:

Old Capacity = 16

Bucket 3

↓

Either Bucket 3
or
Bucket 19 (3 + 16)
```
It will **never** move to Bucket 7 or Bucket 25 during that resize.

This is a brilliant optimization because Java doesn't need to perform a full modulo calculation for every entry—it only checks one additional bit.


### Time Complexity

| Operation | Average | Worst Case                                |
| --------- | ------- | ----------------------------------------- |
| put()     | O(1)    | O(n) (or O(log n) with treeified buckets) |
| get()     | O(1)    | O(n) (or O(log n) with treeified buckets) |
| remove()  | O(1)    | O(n) (or O(log n) with treeified buckets) |


### Treeification

Why does HashMap convert a Linked List into a Red-Black Tree?

This was introduced in Java 8.

#### Step 1: Initially

Suppose all these keys go to Bucket 5.
```
map.put("Apple",100);
map.put("Orange",200);
map.put("Mango",300);
```
Memory:
```
Bucket 5

Apple
│
▼
Orange
│
▼
Mango
│
▼
null
```
This is a Linked List.

#### Step 2: What happens if more keys collide?

Suppose 10 keys fall into Bucket 5.
```
Bucket 5

A
↓
B
↓
C
↓
D
↓
E
↓
F
↓
G
↓
H
↓
I
↓
J
```
Now imagine you search for J.

HashMap checks:
```
A ❌
B ❌
C ❌
...
I ❌
J ✅
```
Time Complexity:

O(n)

This becomes slow.

#### Step 3: Java 8 Solution

If a bucket has more than 8 nodes:
```
Linked List

↓

Red-Black Tree
```
Instead of:
```
A
↓
B
↓
C
↓
D
↓
E
```
It becomes something like:
```
          D
        /   \
      B       F
     / \     / \
    A   C   E   G
```
Now searching is much faster.

Instead of checking one by one:
```
A
↓
B
↓
C
↓
D
↓
...
```
You do:
```
Is it D?

No

Smaller?

Go Left

Larger?

Go Right
```
Time Complexity:
```
O(log n)
```
#### Why doesn't Java convert to a tree after just 2 or 3 nodes?

Because creating and maintaining a Red-Black Tree has overhead:

* More memory
* More complex insert/delete operations
* Tree balancing

For small lists (2–7 nodes), a linked list is actually faster.

That's why Java waits.

#### Why exactly 8?

This is called:
```
TREEIFY_THRESHOLD = 8
```
When a bucket grows beyond 8 nodes, Java considers treeification.

But there's another condition.

#### Second Condition (Very Important)

The table capacity must be at least:
```
64
```
Why?

Imagine:

Capacity = 16

Bucket 5 has 9 nodes.

Java has two choices:

Option 1

Convert to a tree.

Option 2

Resize:
```
16

↓

32

↓

64
```
After resizing, those 9 nodes might spread into different buckets:
```
Bucket 5 → 4 nodes
Bucket 21 → 5 nodes
```
Now there is no need for a tree!

So Java prefers resizing first when the table is still small.

Only when the table is already large (capacity ≥ 64) and collisions are still heavy does it convert the bucket into a Red-Black Tree.

#### SUMMARY:

| Bucket State              | Time Complexity                               |
| ------------------------- | --------------------------------------------- |
| 1–7 nodes                 | O(1) average, linked list traversal if needed |
| >8 nodes and capacity <64 | Resize the HashMap                            |
| >8 nodes and capacity ≥64 | Convert bucket to Red-Black Tree              |
| Red-Black Tree            | O(log n) search                               |

#### Why not 2?

Imagine every time a bucket gets:
```
Apple
↓
Orange
```
Java immediately creates a Red-Black Tree.

That would be a bad idea because:

* Creating a tree is expensive.
* Balancing the tree takes extra work.
* Tree nodes use more memory than linked list nodes.

For only 2 or 3 elements, a linked list is actually faster.

So:
```
2 nodes ❌ Too early
```

#### Why not 100?

Imagine a bucket has:
```
100 nodes
```
Searching would mean checking many nodes before finding the right one.

That's very slow.

Waiting until 100 defeats the purpose of treeification.

So:
```
100 nodes ❌ Too late
```
#### Why 8?
> Eight is an engineering trade-off chosen by the JDK designers. For small collision chains, a linked list is faster and uses less memory than a Red-Black Tree. Beyond roughly eight nodes, the cost of linear search begins to outweigh the overhead of maintaining a tree, so Java converts the bucket into a Red-Black Tree. If the table is still small (capacity < 64), Java prefers resizing first because that often reduces collisions without needing a tree

The JDK engineers found that:

* Buckets with more than 8 collisions are extremely rare when you have a good hash function.
* Up to about 8 nodes, a linked list is usually faster because it has very little overhead.
* Beyond that point, the cost of walking the linked list starts becoming more expensive than maintaining a tree.

So 8 is the crossover point where switching to a tree generally gives better performance.


## Cheat Sheet

```
put(key, value)

        │
        ▼
 hashCode()
        │
        ▼
 Bit Mixing
(h ^ (h >>> 16))
        │
        ▼
Bucket Index
hash & (capacity - 1)
        │
        ▼
Is Bucket Empty?
   │          │
  Yes        No
   │          │
 Store     Compare hash
             │
             ▼
         equals()
             │
      Update or Append
             │
   List grows beyond 8?
             │
     (and capacity ≥ 64)
             ▼
      Convert to Red-Black Tree

```
### Why are mutable keys dangerous in HashMap?

#### Step 1: Create a mutable class
```
class Employee {
int id;
String name;
}
```
Now create an object:
```
Employee e = new Employee();
e.id = 101;
e.name = "John";
```
Suppose hashCode() and equals() are based on id.

#### Step 2: Put it into HashMap
```
HashMap<Employee, String> map = new HashMap<>();

map.put(e, "Developer");
```
Suppose:
```
hashCode = 12345

↓

Bucket 5
```
Memory:
```
Bucket 5

Employee(id=101)
↓
"Developer"
```
Everything is fine.

#### Step 3: Now change the key
```
e.id = 999;
```
The object has changed.

Since hashCode() depends on id, the new hash code is different.

Suppose now:
```
hashCode = 67890

↓

Bucket 12
```
But did HashMap move the entry from Bucket 5 to Bucket 12?

❌ No!

The entry is still physically stored in Bucket 5.

#### Step 4: Now try to get the value
```
map.get(e);
```
HashMap computes the new hash:
```
Bucket 12
```
So it searches:
```
Bucket 12
```
Nothing ❌

Result:
```
null
```
Even though the entry is still inside the map!

##### What happened?

You changed the key after inserting it.

HashMap stored it using the old hash.

Now it's searching using the new hash.

The key has become "lost."


### Untreeification

Java converts Red-Black Tree back into a Linked List.

You already know this.
```
Linked List
│
▼
More than 8 nodes
│
▼
Red Black Tree
```
But what happens after resizing?

Suppose initially:
```
Bucket 5

A
│
B
│
C
│
D
│
E
│
F
│
G
│
H
│
I
```
9 nodes

Java converts it into a tree.
```
           D
         /   \
       B       G
      / \     / \
     A   C   F   I
              /
             H
```
Everything is good.

Now Resize Happens

Capacity
```
64

↓

128
```

Some nodes stay.

Some move.

After redistribution:

Bucket 5
```
A
B
C
```

Bucket 69
```
D
E
F
G
H
I
```
Now each bucket has fewer nodes.

There is no benefit of keeping a Red-Black Tree.

Tree operations have overhead.

### Threshold

Java uses
```
UNTREEIFY_THRESHOLD = 6
```
Notice something interesting.

Treeify
```
8
```
Untreeify
```
6
```
Not
```
8
```
Why?

#### Why 6 instead of 8?

Imagine Java used:
```
Treeify = 8

Untreeify = 8
```
Suppose bucket size changes like this:
```
8

↓

7

↓

8

↓

7

↓

8
```
Then Java would constantly do
```
Tree

↓

List

↓

Tree

↓

List
```
Huge waste.

Instead Java uses hysteresis.
```
Treeify

>8
```
Untreeify
```
<6
```
Between
```
6

7

8
```
Java keeps the current structure.

No unnecessary conversions.

This is a very common engineering technique.

### modCount

This is one of my favorite Java internals.

Imagine this code.
```
HashMap<Integer,String> map = new HashMap<>();

map.put(1,"A");
map.put(2,"B");
map.put(3,"C");
```
Now Java has
```
1=A

2=B

3=C
```
Suppose you start iterating.
```
for(Integer key : map.keySet()){

}
```
Internally Java creates an
```
Iterator
```
##### Iterator starts

Iterator remembers
```
modCount = 3
```
Think of modCount as a **version number**.

Every structural modification increases the version.

Initially
```
Version = 3
```
Now inside loop
```
map.put(4,"D");
```
HashMap updates
```
Version = 4
```
But Iterator still thinks
```
Version = 3
```
Next iteration

Iterator checks
```
Current Version

=

4
```
Expected Version
```
3
```
Mismatch

Java immediately throws
```
ConcurrentModificationException
```
#### Why is it called Fail-Fast?

Because Java doesn't continue iterating over a structure that changed unexpectedly.

Instead of returning incorrect results or silently skipping entries, it fails immediately.

That's why it's called fail-fast.

#### Where is modCount stored?

Inside HashMap.
```
transient int modCount;
```
Every structural modification does:
```
modCount++;
```

#### What is a structural modification?

These operations increment modCount:
```
put(newKey,newValue)

remove(key)

clear()

resize()
```
These change the structure of the map.

#### What does NOT increment modCount?

Suppose
```
map.put("Apple",100);
```
Then
```
map.put("Apple",500);
```
Did the number of nodes change?

No.

Only the value changed.

So
```
modCount

doesn't increase.
```
#### Why doesn't Iterator.remove() throw ConcurrentModificationException?


Suppose
```
Iterator<Integer> it = map.keySet().iterator();
```
Internally
```
Expected Version

3
```
Now
```
it.remove();
```
Instead of directly modifying the map,

Iterator updates
```
Expected Version = Current Version
```
Both stay synchronized.

No exception.

### Why is HashMap NOT Thread Safe?

Imagine we have one HashMap.
```
HashMap<Integer, String> map = new HashMap<>();
```
Now two threads are running simultaneously.
```
Thread-1
Thread-2
```
Both access the same HashMap.

#### Scenario 1: Two Threads Writing

Thread-1:
```
map.put(1, "Apple");
```
At exactly the same time...

Thread-2:
```
map.put(2, "Orange");
```
Both threads may try to modify:

* size
* bucket array
* linked list/tree
* modCount

at the same time.

 HashMap's internal data can become inconsistent.

#### Scenario 2: Resize (Very Dangerous)

Suppose:
```
Capacity = 16
Size = 12
```
Both threads execute:
```
map.put(...)
```
Both notice:

Threshold exceeded

Both start resizing.

Thread 1
```
16 → 32
```
At the same time:

Thread 2
```
16 → 32
```
Now both are moving nodes between bucket arrays simultaneously.

This can lead to:

* Lost entries
* Incorrect links
* Data corruption

(Older JDK versions could even end up with infinite loops during corruption.)

#### Why doesn't HashMap use synchronized?

Because synchronization has a cost.

Most applications use HashMap in single-threaded code.

If every operation were synchronized, HashMap would be slower for everyone.

So Java provides:

* HashMap → Fast, not thread-safe
* ConcurrentHashMap → Thread-safe with much better concurrency

You choose the right one.

#### What is ConcurrentHashMap?

It is designed for multiple threads.

Example:
```
ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
```
Now:
```
Thread-1 → put()
Thread-2 → get()
Thread-3 → remove()
```
can work safely without corrupting the map.

Internally, it uses much finer-grained synchronization and other concurrency techniques instead of locking the entire map for every operation.


## Interview Questions

* Explain HashMap internals.
* Why must equals() and hashCode() be implemented together?
* Why is the default capacity 16?
* Why is the load factor 0.75?
* What is rehashing?
* How does Java calculate the bucket index?
* Why are capacities powers of two?
* What happens when many keys have the same hash code?
* When does Java convert a linked list into a Red-Black Tree?
* Why are mutable keys dangerous in a HashMap?