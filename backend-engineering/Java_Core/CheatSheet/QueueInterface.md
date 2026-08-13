What is Queue interface
what LinkedList is?
What problem does Queue solve?
FIFO
offer()
poll()
peek()

✅ Queue vs PriorityQueue
✅ FIFO vs priority-based ordering
✅ Array-backed binary heap
✅ Min-heap by default
✅ Max-heap using Comparator
✅ Heap property
✅ Backing array is not sorted
✅ offer() → sift up → O(log n)
✅ poll() → sift down → O(log n)
✅ peek() → O(1)
✅ remove(Object) → O(n)
✅ null not allowed
✅ Duplicates allowed
✅ Iterator doesn't guarantee sorted order
✅ Custom Comparator
✅ Not thread-safe


```
ArrayDeque
│
├── Implements → Deque
│
├── Internal → Resizable array
│              Circular-buffer design
│
├── Front
│   ├── addFirst()
│   ├── offerFirst()
│   ├── removeFirst()
│   ├── pollFirst()
│   └── peekFirst()
│
├── Rear
│   ├── addLast()
│   ├── offerLast()
│   ├── removeLast()
│   ├── pollLast()
│   └── peekLast()
│
├── addFirst/addLast → O(1) amortized
├── removeFirst/removeLast → O(1)
│
├── null → ❌
├── thread-safe → ❌
├── sorted → ❌
├── random access → ❌
│
├── Queue → ✅
├── Stack → ✅
│
└── Modern alternative to Stack → ✅

```