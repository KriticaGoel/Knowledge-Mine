## Agenda
* SELECT
* WHERE
* ORDER BY
* DISTINCT
* LIMIT / FETCH FIRST

## Problem 1:

Assume we have a employee table
| id | name    | department | salary |
| -- | ------- | ---------- | ------ |
| 1  | Alice   | IT         | 80000  |
| 2  | Bob     | HR         | 50000  |
| 3  | Charlie | IT         | 90000  |
| 4  | David   | Finance    | 70000  |
| 5  | Eva     | IT         | 80000  |

Q: Write a query to return all employees

A: select id,name,department,salary from employee;

Q: Write a query to return only employees from the IT department.

A: select id,name,department,salary from employee where department ='IT';

Q: What happens internally when this query executes?

A: Read the row and evaluate where clause if match keep the row otherwise discard.

Q: Suppose table have 100 million records will db always check all 100 millions records?

A: No, If an index exists on the department column, the database can jump directly to the matching rows instead of scanning the entire table.

Q: Employees from the IT department whose salary is greater than 80,000.

A: select id,name,department,salary from employee where department ='IT' and salary>80000;

Q:

Suppose I write:
```
WHERE department='IT'
AND salary>80000
OR name='Bob'
```
Will SQL evaluate it as:
```
(A AND B) OR C

or

A AND (B OR C)
```
A: (A AND B) OR C because && has higher precedence than || same like java

Q: ORDER BY 2; what does it mean?

A: It means order by name

Q: Write a query to return all unique departments.

A: select distinct department from employee;

Q: how distinct work?

A: 
```
Seen Set = {}

Read IT
↓

Seen = {IT}

Output IT

----------------

Read HR

Seen = {IT, HR}

Output HR

----------------

Read IT

Already seen

Ignore

----------------

Read Finance

Seen = {IT, HR, Finance}

Output Finance
```

---

## SQL Execution Order

Logical execution order 
```
1. FROM
      ↓
2. WHERE
      ↓
3. SELECT
```

The database first decides which table to read, then filters rows, and only then returns the requested columns.

---
## Interview tip:

Q : "When is SELECT * acceptable?"

A good answer is:

During quick exploration or debugging.
In ad-hoc scripts.
Not in production application code.

Q: Can we use ORDER BY 2?

A: Yes, SQL allows ordering by the position of the selected column, but I avoid it in production because it's less readable and can break if the SELECT column order changes. I prefer ordering by the explicit column name.

