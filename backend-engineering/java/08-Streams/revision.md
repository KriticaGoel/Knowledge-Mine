Stream Pattern Detection

| Question                                       | Stream      |                               |
|------------------------------------------------|-------------|-------------------------------|
| Am I selecting data?                           | filter(...) | keeps or removes elements     |
| Am I transforming data?                        | map(...)    | transforms each element       |
| Am I changing order?                           | sorted(...) |                               |
| one value                                      |reduce()| many elements → single result |
| Top 3 Highest                                  |limit()|limit(3)|
| Ignore the first N elements and keep the rest. |skip||
| Highest Paid Employee                          |max|employes.stream().max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);|


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