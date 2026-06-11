###  stream() ,  forEach()
1. List<Integer> nums = List.of(1,2,3,4,5);
2. Print all numbers
3. Print square of each number
4. Print cube of each number
5. Print numbers with prefix "Value:"
6. Print numbers doubled. 
7. Print numbers with "Number = " prefix.
8. Print only the last digit of each number.
9. Print whether each number is even or odd.

### filter() + forEach
1. Even numbers
2. Odd numbers
3. Greater than 10
4. Less than 50
5. Divisible by 3
6. Numbers between 15 and 40 (inclusive) and divisible by 5

###  map()
1. Multiply by 10
2. Convert Integer → String
3. Square every number
4. Create list of absolute values

### Sorting
1. Sort ascending List<Integer> nums = List.of(5, 1, 9, 3, 7);
2. Sort descending
3. Sort strings alphabetically List<String> names = List.of("Zara", "Aman", "Bob", "John");
4. Sort string reverse order
5. Sort by string length
6. Sort by last digit
7. Sort employees by salary
8. Sort employees by name
9. Sort employees by salary descending
10. Sort by salary, then name
Rule:
First salary ascending
If salary same → sort by name
11. Sort even first, then odd
12. Sort by absolute value

### limit() 
1. top 3 highest paying

### skip()
1. Second highest salary

### distinct()

1. Remove duplicates List.of(1,2,2,3,3,3,4,4,5)
2. Unique names count

### count()

1. Names starting A
2. Names length >5


### anyMatch()
### allMatch()
### noneMatch()

1. Any salary >2 lakh
2. All employees active
3. None retired

### max()
### min()
### average()
### sum()

1. Highest salary
2. Lowest salary
3. Average salary

### Reducing
1. Sum of numbers
2. Product of numbers
3. Maximum value
4. Minimum value
5. Minimum value
6. Join with space
7. Find the longest string using reduce
8. Sum of even numbers only (using reduce)
9. Count elements using reduce
10. Sum of squares
11. Find second-highest salary
12. Build comma-separated string
13. Find employee with the highest salary
14. Merge two lists using reduce

### collect(toList())
1. Even number list
2. Square list
3. Numbers >10 list
4. Get all employee names into a List.
5. Get unique departments.
6. **Convert employees into Map<id, Employee>.**
7. Create "A,B,C,D" from names.
8. **Group employees by department.** 
9. Split employees into salary > 5000 and salary <= 5000.


### List<String>
Names starting with A
Names ending with n
Names length >5

### groupingBy()
1. Group by department Kritica IT,   Sachin HR,   Neha IT,   Sai HR
2. Group by age
3. Group by city
4. **Group by even/odd** List<Integer> nums =    List.of(1,2,3,4,5,6,7,8);
5. Group Strings by Length List<String> words =   List.of("Java","API","Spring","SQL");
6. Number of employee in each department
7. Name of employee in each department 
8. Total Salary in each department



### partitioningBy()

1. Salary >50000
2. Age >30

### flatMap()

1. Get all orders
2. Get all order IDs
3. Get all unique products

### Nested objects
1. All employee names
2. Highest paid employee 
3. Unique departments



### Mix everything
1. Even numbers squared
2. Odd numbers multiplied by 10
3. Numbers >50 converted to String
4. Pending orders 
5. Orders by customer 
6. Revenue by city 
7. Top 5 orders
8. Failed transactions 
9. Total transaction amount
10. Top customers
11. Failed requests 
12. Average response time 
13. Top 10 slow requests 
14. Requests grouped by endpoint


### Custom Collectors
Collector.of()
groupingBy + mapping
groupingBy + reducing
teeing collector

Multithreading using Java Streams
- [Sequential Streams](#sequential-streams)
- [Parallel Streams](#parallel-streams)


1. flatMap() ⭐

Most important next topic.

Examples:
```
List<List<String>>
↓
flatMap()
↓
List<String>
```
2. reduce()

Examples:
```
sum
product
max
min
longest string
```
3. Advanced Collectors
```
   counting()
   summingInt()
   summingDouble()
   averagingInt()
   maxBy()
   minBy()
   summarizingInt()
   ```
4. Complex Employee Problems

Examples:
```
Highest paid employee per department
Count employees per department
Department with highest salary
Duplicate names
Top 3 salaries
Second highest salary
Department → average salary
```