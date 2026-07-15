#### Complexity of a data type

| Data Type   |   Insert   | Remove  | Replace/Update | Size | getMin  |               Empty               |
|:------------|:----------:|:-------:|:---------------|------|:-------:|:---------------------------------:|
| Array List  | O(1) back  |  O(N)   |                | O(1) |  O(N)   |                                   |
| Linked List | O(1) front |  O(N)   |                | O(1) |  O(N)   |                                   |
| Vector      |    O(N)    |  O(N)   | O(N)           |      |         |               O(1)                |
| Queue       |    O(1)    |  O(N)   |                | O(1) |  O(N)   |                                   |
| Hash Map    |    O(1)    |  O(N)   |                | O(1) |  O(N)   |                                   |
| Tree Map    |  O(logN)   | O(logN) |                | O(1) |  O(N)   |                                   |
| Heap        |  O(logN)   | O(logN) | O(1)           | O(1) | O(logN) |                                   |
| Stack       |  O(logN)   | O(logN) | O(1)           | O(1) | O(logN) | O(N)<br/> java.util.Stack.empty() |

#### Complexity of List Interface in Java

| **Operation**                      | **Time <br/>Complexity** | **Space <br/>Complexity** |
|------------------------------------|--------------------------|---------------------------|
| Adding Element in List Interface   | O(1)                     | O(1)                      |                           
| Remove Element from List Interface | O(N)                     | O(N)                      |
| Replace Element in List Interface  | O(N)                     | O(N)                      |
| Traversing List Interface          | O(N)                     | O(N)                      |
