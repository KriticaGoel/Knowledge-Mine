### ⏱️ Time Reference
10⁷ seconds ≈ 0.3 year

10⁸ iterations ≈ 1 second (approx CPU dependent)

### 🔢 Powers of 2

2¹ = 2
2² = 4
2³ = 8
2⁴ = 16
2⁵ = 32
2⁶ = 64
2⁷ = 128

### 📉 Logarithm Basics

Logarithm = number of times you divide by 2 until you reach 1

#### Example:

log₂(8) = 3  → because 2³ = 8

log₃(27) = 3 → because 3³ = 27

log₅(25) = 2 → because 5² = 25

#### Exponential ↔ Log Form

| Exponential | Meaning              | Log Form     |
| ----------- | -------------------- | ------------ |
| 2³ = 8      | power of 2 giving 8  | log₂(8) = 3  |
| 3³ = 27     | power of 3 giving 27 | log₃(27) = 3 |
| 5² = 25     | power of 5 giving 25 | log₅(25) = 2 |


### 🔢 Number Systems
Natural numbers → start from 1

Whole numbers → start from 0

### ➕ Summation Formulas

Sum of first N natural numbers = n(n+1)/2

Sum of first N odd number = n²

Sum of first N even number = n(n + 1)

### 📊 Progressions

#### Arithmetic Progression (AP)

Sequence = a, a + d, a + 2d, a + 3d, ...

Common difference = d

Nth term = a + (n - 1)d

Sum of N term = n/2 [2a + (n-1)d]

#### Geometric Progression (GP)

Sequence = a, ar, ar², ar³, ...

Common Ratio = r

Nth term = ar^(n-1)

Sum of N term = a(r^n - 1)/(r-1) if r != 1

#### Harmonic Progression (HP)

Sequence = 1/a, 1/(a + d), 1/(a + 2d), ...

### ⚡ Time Complexity Order

Lower to higher time complexity

log₂N < log₁₀N < √N < N < N log N < N² < 2ᴺ < N! < Nᴺ

### 🧠 Complexity Insight
* Execution time depends on CPU, memory, system load → not reliable for comparison
* We compare algorithms using Time Complexity
* Time Complexity = number of basic operations as function of input size (N)

### 💾 Space Complexity Rule
Do NOT count input/output space in space complexity

### 🧮 Data Sizes
Integer → 4 bytes

Long → 8 bytes

### 🔁 Nested Loop Complexity

#### Case 1: Fixed inner loop (Multiplication works)

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
    }
}
```

Inner loop runs n times for every i
Total = n × n = O(n²)

Total Work = Outer × Inner

#### Explanation

j goes from 0 to n for every i

i goes from 0 to n

| i | j |
|---|---|
| 0 | n |
| 1 | n |
| 2 | n |
| n | n |

total work of inner loop = n + n + n + ... + n (n times) = n * n = n^2

#### Case 2: Variable inner loop (Multiplication fails)
```java
for (int i = 1; i < n; i++) {
    for (int j = 1; j < i; j++) {
    }
}
```
| i   | j           | how many times inner loop executes for each i |
|-----|-------------|-----------------------------------------------|
| 1   | 0           | 0                                             |
| 2   | 1           | 1                                             |
| 3   | 1,2         | 2                                             |
| 4   | 1,2,3       | 3                                             |
| n-1 | 1,2,...,n-2 | n-2                                           |

Total work done in inner loop = 0 + 1 + 2 + 3 + ... + (n-2) = (n-2)(n-1)/2 = O(n^2)

The outer loop is already included in this table.

Each row corresponds to one outer-loop iteration.

### 🧾 Key Insight
* Each outer loop iteration contributes different inner loop cost
* Always sum individually when inner loop depends on outer loop