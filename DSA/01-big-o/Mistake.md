```java
for (int i = 1; i < n; i++) {
    for (int j = 1; j < i; j++) {
        System.out.println(i + j);
    }
}

```

Step 1: Outer Loop runs n-1 times.
Step 2: Inner Loop runs i-1 times for each i.
Total executions: (n-2)(n-1)/2

Step 3 : Total Execution Time

!= (n-1) × (n-1)(n-2)/2  -> Step 2 already represents the total work across all outer loop iterations.

The series:

0 + 1 + 2 + ... + (n-2)

already includes every iteration of the outer loop.

So Step 2 is actually the final total work.

= (n-1)(n-2)/2 =(n² - 3n + 2)/2

Step 4: Total Time Complexity O(n²) because n² is the dominant term.