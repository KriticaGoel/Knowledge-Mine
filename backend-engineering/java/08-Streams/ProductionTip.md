1. Filter Coding Tip

Many developers write:
```
.filter(emp -> emp.getSalary() > 50000 &&
               emp.getAge() > 30 &&
               emp.getDepartment().equals("IT"))
```
Better Way:
```
.filter(emp -> emp.getSalary() > 50000)
.filter(emp -> emp.getAge() > 30)
.filter(emp -> "IT".equals(emp.getDepartment()))
```

The stream becomes a readable pipeline.

2. Put the most selective filter first ( the condition removes the most records)

If only 5% of employees are in IT:
```
.filter(emp -> "IT".equals(emp.getDepartment()))
```
should probably come first.

This reduces work for later filters.

List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9,10);

