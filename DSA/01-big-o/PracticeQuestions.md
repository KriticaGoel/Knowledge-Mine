### Easy

Q1. Find Time Complexity
```java
for(int i = 0; i < n; i++) {
    System.out.println(i);
}
```

A1:

Step1: Loop runs from 0 to n-1, which means it executes n times.

Step2: Total Execution Time : N

Step3: Time Complexity : O(N)

Answer: O(N)

Q2. Find Time Complexity
```java
for(int i = 0; i < n; i++) {
    for(int j = 0; j < n; j++) {
        System.out.println(i + " " + j);
    }
}
```

A2: 

Step1: Outer loop : i goes from 0 to N-1, total execution: N times

Step2: Inner loop : j goes from 0 to N-1, total execution: N times

Step3 : Total Execution Time : N*N = N^2

Step4 : Time Complexity : O(N^2)

Answer: O(N^2)


### Medium

Q3. Find Time Complexity
```java
for (int i = 1; i < n; i *= 2) {
System.out.println(i);
}

```

A3:

Step1: i value is 1,2,4,8,16.........
```
2^k >= N
k >= log(n)
```

Step2: Total Execution Time: log2(n) 

Step3: Time Complexity :O(log N)

Answer: O(log N)

Explanation:

    N = 20
    
    1, 2, 4, 8, 16,32(loop stop)
    
    2^k==32 wrong
    
    2^k>=N (loop stop)

Q4. Find Time Complexity

```java
for (int i = 0; i < n; i++) {
    for (int j = 1; j < n; j *= 2) {
        System.out.println(i + " " + j);
    }
}
```

A4: 
Step1 : Outer Loop : i goes 0 to n-1, total execution: N times

Step2 : Inner Loop : j goes 1 ,2,4,8,16 for N value 20, total execution: log2(n) times
        
        total execution : 
            2^k>=N
            k>=log(n)

Step3 : Total Execution Time : N*log2(n)

Step4 : Time Complexity : O(N log N)

Answer: O(N log N)

Q5. Find Time Complexity

```java
for (int i = n; i > 0; i /= 2) {
    for (int j = 0; j < i; j++) {
        System.out.println(j);
    }
}
```

A5:

Step1 : Outer Loop : for N =13,i goes 13,6,3,1 total execution: log2(n) times

Step2 : Inner Loop : for i =13, j=0,1,2,3,4,5,6,7,8,9,10,11,12 total execution : N times
                
                for i =6, j=0,1,2,3,4,5 total execution : N/2 times
                
                for i =3, j=0,1,2 total execution : N/4 times
                
                for i =1, j=0 total execution : N/8 times
                total execution : n + n/2 + n/4 + n/8 + ...(Gp) =2N

Step3 : Total Execution Time : log2(n)* 2N

Step4 : Time Complexity : O(N log N)

Answer:  O(N log N)

## HARD
Q6. Find Time Complexity

```java
for (int i = 1; i < n; i++) {
    for (int j = 1; j < i; j++) {
        System.out.println(i + j);
    }
}
```

A6: 
Step 1 : Outer Loop : i goes 1 to n-1, total execution: N-1 times

Step 2: Inner Loop : for i=1, j=0 total execution: 0 times
             
             for i=2, j=1 total execution: 1 time
             
             for i=3, j=1,2 total execution: 2 times
             
             for i=4, j=1,2,3 total execution: 3 times
             
             for i=5, j=1,2,3,4 total execution: 4 times
             
             for i=n-1, j=1,2,...n-2 total execution: n-2 times
             
             total execution : 0 + 1 + 2 + 3 + ... + n-2 = (n-1)(n-2)/2 for each i

Step 3: Total Execution Time : (n-1)(n-2)/2 -> (n² - 3n + 2)/2

Step 4: Time Complexity : O(N^2)

Answer: O(N^2)

Q7. Find Time Complexity

```java
for (int i = 1; i < n; i *= 2) {
    for (int j = 0; j < n; j++) {
        System.out.println(i + j);
    }
}
```

A7:

Step1 : Outer Loop : i goes 1,2,4,8,16 for N value 20, total execution: log2(n) times

Step2 : Inner Loop : j goes 0 to n-1 for each value of i, total execution: N times

Step3 : Total Execution Time : log2(n) * N

Step4 : Time Complexity : O(N log N)

Answer:  O(N log N)

Q8. Find Time Complexity

```java
for (int i = n; i > 1; i /= 2) {
    for (int j = 0; j < i; j++) {
        for (int k = 0; k < j; k++) {
            System.out.println(k);
        }
    }
}
```

A8:

Step 1: Outer Loop : for N =13,i goes 13,6,3,1 total execution: log2(n) times

Step 2: Work done for ONE value of i
        for j=0 k=0
    
        for j=1 k=0
            
        for j=2 k=0,1
        
        for j=3 k=0,1,2
        
        for j=4 k=0,1,2,3
        
        for j=5 k=0,1,2,3,4
        
        for j=i-1 k=0,1,...i-1

Total work for one value of i = 0 + 1 + 2 + 3 + 4 + 5 + ... + i-1 = i(i-1)/2 =O(i^2)

Step 3: Work for ALL values of i
        
        i=n ->O(n^2)
        i=n/2 -> O((n/2)^2) = O(n^2/4)
        i=n/4 -> O((n/4)^2) = O(n^2/16)
        i=n/8 -> O((n/8)^2) = O(n^2/64)
        Total work for all values of i = O(n^2) + O(n^2/4) + O(n^2/16) + O(n^2/64) + ... (GP) = O(n^2)

Step 5 : Time Complexity : O(n^2)

Answer: O(N^2)

Q9. Find Time Complexity

```java
for (int i = 1; i <= n; i++) {
    for (int j = i; j <= n; j++) {
        for (int k = 1; k <= j; k *= 2) {
            System.out.println(k);
        }
    }
}
```
A9:

Step 1: Outer Loop : i goes 1 to n, total execution: N times

Step 2: Middle Loop : for i=1, j goes 1 to n total execution: N times

                    for i=2, j goes 2 to n total execution: N-1 times

                    for i=3, j goes 3 to n total execution: N-2 times

                    for i=n, j goes n to n total execution: 1 time

                    total execution : N + (N-1) + (N-2) + ... + 1 = N(N+1)/2
Step 3: Total Execution Time for i and j  : N(N+1)/2

Step 3: Inner Loop : for j=1, k goes 1... total execution: log2(j) times

                    for j=2, k goes 1,2 total execution: log2(j) times

                    for j=3, k goes 1,2 total execution: log2(j) times

                    for j=n, k goes 1,2,4,... total execution: log2(j) times

Step 4: total execution : N(N+1)/2 * log2(j) -> O(N^2 log N)

Step 5: Time Complexity : O(N^2 log N)

Answer:  O(N^2 log N)

Q10. Find Time Complexity

```java
for (int i = 1; i <= n; i *= 2) {
    for (int j = 1; j <= n; j *= 3) {
        for (int k = 0; k < n; k++) {
            System.out.println(k);
        }
    }
}
```

A10:

Step 1 : Outer Loop : i goes from 1,2,4,8 so Nth value is log2(N)

Step 2 : Middle Loop : j goes from 1,3,9,27 so Nth value is log3(N)

Step 3 : Inner Loop : k goes from 0 to n-1, total execution: N times

Step 4 : Total Execution Time : log2(N) * log3(N) * N

Step 5 : Time Complexity : O(N log^2 N)

Answer: O(N log^2 N)

Q11. Find Time Complexity

```java
int count = 0;

for (int i = n; i > 1; i /= 2) {
    for (int j = 0; j < n; j++) {
        count++;
    }
}

for (int i = 1; i < n; i *= 2) {
    count++;
}
```

Q12. Find Time Complexity

```java