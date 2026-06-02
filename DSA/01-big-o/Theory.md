10⁷ sec ≈ 0.3 year

108 iteration takes 1 second

21 =2

22 =4

23 =8

24 =16

25 =32

26 =64

27 =128

Log Basis - Divide a number by 2 until you get 1. The number of times you divide is the log base 2 of that number.

Exponential form		logarithmic form
23 = 8	what power of 2 is 8 = 3	log2 8 = 3
33 = 27	what power of 3 is 27 = 3	log3 27 = 3
52 = 25	what power of 5 is 25 = 2	log5 25 = 2
Natural number starts from 1

Whole number starts from 0

Sum of N natural number n(n+1)/2

Sum of N odd number n^2

Sum of N even number n(n+1)

AP (Arithmetic Progression) =Common Difference= a, a+d, a+2d, a+3d, ...

Nth term of AP = a + (n-1)d

Sum of N term of AP = n/2 [2a + (n-1)d]

GP (Geometric Progression) = Common Ratio = a, ar, ar^2, ar^3, ...

Nth term of GP = ar^(n-1)

Sum of N term of GP = a(r^n - 1)/(r-1) if r != 1

HP (Harmonic Progression) = 1/a, 1/(a+d), 1/(a+2d), 1/(a+3d), ...

Lower to higher time complexity

log2N < log10N < √N< N < NlogN < N2 < 2N < N! < NN

Execution time depends on external factors like CPU, Memory, etc. so that not a correct way to compare two algorithms. We can compare them by their time complexity. Time complexity is the number of basic operations performed by an algorithm as a function of the input size (N). It gives us an idea of how the algorithm's performance scales with larger inputs.

Never count input and output of an algorithm when calculating space complexity.

Integer - 4 bytes

Long - 8 bytes

for (int j = 1; j < i; j++)  => 1 + 2 + 3 + ... + n =>n(n+1)/2 => O(n^2)

Case 1: Multiplication Works

Total Work = Outer × Inner

This works only when the inner loop executes the same number of times for every outer loop iteration.

```java
for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){
    }
}

```

j goes from 0 to n for every i

i goes from 0 to n

| i | j |
|---|---|
| 0 | n |
| 1 | n |
| 2 | n |
| n | n |

total work of inner loop = n + n + n + ... + n (n times) = n * n = n^2

Case 2: Multiplication Doesn't Work
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