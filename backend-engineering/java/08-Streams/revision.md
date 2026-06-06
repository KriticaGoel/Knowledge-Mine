Stream Pattern Detection

| Concept            | Purpose                       | Use When                          | Avoid When                                       | Method                              | Time Complexity | Space Complexity |
|--------------------|-------------------------------|-----------------------------------|--------------------------------------------------|-------------------------------------|-----------------|------------------|
| `filter()`         | Remove elements               | Apply conditions                  | Condition depends on external mutable state      | stream.filter(x -> x > 10)          | O(n)            | O(1)             |
| `map()`            | Transform each element        | Entity → DTO, value conversion    | Side effects, logging, mutation                  | stream.map(Employee::getName)       | O(n)            | O(1)             |
| `flatMap()`        | Flatten nested structures     | `List<List<T>>`, Optional nesting | Simple one-to-one mapping                        | list.stream().flatMap(List::stream) |                 |                  |
| `sorted()`         | Order elements                | Need sorted output                | Very large datasets where sorting is unnecessary | stream.sorted()                     | O(n log n)      | O(n)             |
| `distinct()`       | Remove duplicates             | Deduplication                     | Poor `equals()`/`hashCode()` implementations     | stream.distinct()                   | O(n)            | O(n)             |
| `limit(n)`         | Keep first N elements         | Top N results, pagination         | Need all records                                 |                                     |                 |                  |
| `skip(n)`          | Ignore first N elements       | Pagination, offset processing     | Small datasets where offset isn't needed         |                                     |                 |                  |
| `peek()`           | Debug/observe pipeline        | Debugging, tracing execution      | Business logic or mutations                      |                                     |                 |                  |
| `max()`            | Find largest element          | Highest salary, latest date       | Need multiple top results                        |                                     |                 |                  |
| `min()`            | Find smallest element         | Lowest price, earliest date       | Need ranking or sorting                          |                                     |                 |                  |
| `count()`          | Count elements                | Need total matching records       | Need actual elements                             |                                     | O(n)            | O(1)             |
| `findFirst()`      | Return first matching element | Order matters                     | Parallel optimization required                   |                                     |                 |                  |
| `findAny()`        | Return any matching element   | Parallel streams                  | When encounter order matters                     |                                     |                 |                  |
| `reduce()`         | Aggregate into one value      | Sum, product, custom aggregation  | Grouping or collection creation                  | reduce(0,Integer::sum)              | O(n)            | O(1)             |
| `collect()`        | Materialize result            | List, Set, Map, Grouping          | Simple aggregation like count/sum                | collect(Collectors.toList())        |                 |                  |
| `groupingBy()`     | Group elements                | Department-wise employees         | Simple filtering                                 |                                     |                 |                  |
| `partitioningBy()` | Split into true/false groups  | Active vs Inactive                | More than 2 groups needed                        |                                     |                 |                  |
| `forEach()`        | Consume elements              | Printing, sending notifications   | Building collections                             |                                     |                 |                  |
| `parallelStream()` | Parallel execution            | Large CPU-bound workloads         | Small datasets, I/O operations                   |                                     |                 |                  |



Streams use stable sort that means A stable sort preserves the original order.

Sort even first, then odd

Each element moves through the pipeline stage by stage. If a stage rejects it, the remaining stages are never executed for that element.


Which one is better

Approach 1:
`stream()
.sorted(reversed())
.limit(1)`

Approach 2:

`stream()
.max(...)`

Answer Approach 2 Approach 1 time complexity O(n log n) (Sorting) and Approach 2- O(n)

