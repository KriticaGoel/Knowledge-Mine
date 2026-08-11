| Operation              |      ArrayList | LinkedList |
| ---------------------- | -------------: | ---------: |
| `get(index)`           |       **O(1)** |       O(n) |
| add at end             | O(1) amortized |       O(1) |
| remove last            |           O(1) |       O(1) |
| add at beginning       |           O(n) |       O(1) |
| remove beginning       |           O(n) |       O(1) |
| remove middle by index |           O(n) |      O(n)* |
| memory overhead        |        **Low** |       High |



## trade off

| Feature                | **Array**        | **ArrayList**              | **LinkedList**                                     | **Vector**                           | **Stack**                  |
|------------------------|------------------|----------------------------|----------------------------------------------------|--------------------------------------|----------------------------|
| Size                   | Fixed            | Dynamic                    | Dynamic                                            | Dynamic                              | Vector/backing array       |
| Internal structure     | Contiguous array | Resizable contiguous array | Doubly linked nodes                                | Resizable contiguous array           | Resizable contiguous array |
| Random access `get(i)` | **O(1)**         | **O(1)**                   | **O(n)**                                           | **O(1)**                             | **O(1)**                   |
| Add at end             | N/A if full      | **O(1) amortized**         | **O(1)**                                           | **O(1) amortized**                   | **O(1) amortized**         |
| Add at beginning       | O(n)             | O(n)                       | **O(1)**                                           | O(n)                                 |                            |
| Remove at beginning    | O(n)             | O(n)                       | **O(1)**                                           | O(n)                                 |                            |
| Remove at end          | O(1)             | **O(1)**                   | **O(1)**                                           | **O(1)**                             |                            |
| Insert/remove middle   | O(n)             | O(n)                       | **O(n) by index**, O(1) <br/>once node known       | O(n)                                 |                            |
| Memory overhead        | **Lowest**       | Low                        | **Highest**                                        |                                      |                            |
| Cache locality         | **Excellent**    | **Excellent**              | Poorer                                             |                                      |                            |
| Stores primitives      | **Yes**          | No, uses wrappers          | No, uses wrappers                                  |                                      |                            |
| Resize cost            | Cannot resize    | Occasionally copies array  | No array resize                                    |                                      |                            |
| `null` values          | Yes              | Yes                        | Yes                                                |                                      |                            |
| Best use               | Fixed-size data  | **General-purpose List**   | Frequent end operations <br/>/ node-based behavior |                                      |                            |
| Capacity               | Fixed-size data  | oldCapacity +oldCapacity/2 | Not Required                                       | if not given double the old capacity |                            |
| Thread-safe            | ❌                | ❌                          | ❌                                                  | ✅ synchronized  legacy               | ✅ synchronized   legacy    |

1. Array — Trade-off
   [10][20][30][40][50]
   Advantages

✅ Very memory efficient
✅ O(1) random access
✅ Excellent cache locality
✅ Can store primitives directly
✅ No resizing overhead

Disadvantages

❌ Fixed size

int[] arr = new int[100];

You cannot make it 101 elements.

You have to create a new array and copy.

Best when

Size is known/fixed and performance/memory matters.

2. ArrayList — Trade-off
   ArrayList
   ↓
   Object[]
   [10][20][30][40]
   Advantages

✅ Dynamic size
✅ O(1) random access
✅ Excellent cache locality
✅ Lower overhead than LinkedList
✅ Great for iteration
✅ Excellent general-purpose List

Disadvantages

❌ Insert/remove at beginning or middle → O(n)

[10][20][30][40][50]
↓ remove 20

[10][30][40][50]

Elements have to shift.

❌ Occasionally needs to resize its backing array.

For example:

old array
[10][20][30][40]

       ↓ grow

new larger array
[10][20][30][40][ ][ ][ ]

The existing references have to be copied.

3. LinkedList — Trade-off
   10 ⇄ 20 ⇄ 30 ⇄ 40
   Advantages

✅ Dynamic size
✅ Add/remove at beginning → O(1)
✅ Add/remove at end → O(1)
✅ No backing-array resizing
✅ Can operate as both List and Deque

Disadvantages

❌ get(index) → O(n)

list.get(5000);

Must traverse nodes.

❌ High memory overhead

Every node has:

prev
item
next

❌ Poorer cache locality

Nodes can be scattered across the heap.

❌ Often slower than ArrayList in real-world workloads despite some O(1) operations.