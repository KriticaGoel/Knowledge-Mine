1. HashMap at a Glance

```
HashMap
│
├── Key → Value
├── Unordered
├── Allows one null key
├── Multiple null values
├── Average O(1)
├── Not Thread Safe
└── Backed by Bucket Array
```

2. Internal Structure

```
HashMap
     │
     ▼
Node<K,V>[] table
     │
 ┌───┼───────────────┐
 │   │   │   │       │
 ▼   ▼   ▼   ▼       ▼
null Node null Tree List

Node
------------
hash
key
value
next
``` 

3. put() Flow

```
put(key,value)

     │
     ▼
hashCode()

     │
     ▼
h ^ (h >>>16)

     │
     ▼
hash & (capacity-1)

     │
     ▼
Bucket Empty?

YES ─────► Insert

NO
 │
 ▼
equals()

 │
 ├── Same Key → Update
 │
 └── Different Key → Collision
                     │
                     ▼
               Linked List
                     │
        >8 nodes & capacity≥64
                     │
                     ▼
              Red Black Tree
```


4. get() Flow

```
hashCode()

↓

Bit Mixing

↓

Bucket Index

↓

Traverse Bucket

↓

equals()

↓

Return Value
```

5. Interview Numbers

| Property              | Value                 |
| --------------------- | --------------------- |
| Initial Capacity      | 16                    |
| Load Factor           | 0.75                  |
| Threshold             | capacity × loadFactor |
| Resize                | 2×                    |
| Treeify               | >8                    |
| Untreeify             | <6                    |
| Min Capacity for Tree | 64                    |


6. Formula Box

```
hashCode()

↓

hash = h ^ (h >>>16)

↓

index = hash & (capacity-1)

threshold = capacity × loadFactor
```

7. Complexity

| Operation | Average | Worst           |
| --------- | ------- | --------------- |
| put       | O(1)    | O(n)            |
| get       | O(1)    | O(n) / O(log n) |
| remove    | O(1)    | O(n) / O(log n) |
| resize    | -       | O(n)            |


8. Collision
```
Bucket

Apple
  │
Orange
  │
Mango

↓

Java 8

        Mango
       /    \
 Apple      Orange
```
9. Resize

```
Capacity 16

Threshold =12

13th Insert

↓

32 Buckets

↓

Rehash

↓

Less Collision
```

10. Mutable Key Problem
```
put(Employee id=1)

↓

Bucket 5

↓

id becomes 100

↓

hash changes

↓

Search Bucket 12

↓

NOT FOUND
```

Rule

> Never use mutable keys.

11. modCount
```
Iterator

↓

Expected modCount

↓

Map Modified?

YES

↓

ConcurrentModificationException
```
12. HashMap vs ConcurrentHashMap

    | HashMap         | ConcurrentHashMap          |
    | --------------- | -------------------------- |
    | Not Thread Safe | Thread Safe                |
    | Fail Fast       | Weakly Consistent Iterator |
    | Fast            | Concurrent Reads/Writes    |

13. Frequently Asked Interview Questions
    * Why capacity is 16?
    * Why power of 2?
    * Why load factor 0.75?
    * Why tree after 8?
    * Why minimum capacity 64?
    * Why untreeify at 6?
    * Why h ^ (h>>>16)?
    * Why use & instead of %?
    * Why override equals() and hashCode() together?
    * Why mutable keys are dangerous?
    * Why resize doubles capacity?
    * Explain put() internally.
    * Explain get() internally.
    * Explain collision handling.
    * Explain fail-fast iterator.