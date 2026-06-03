1. For nested loops:

Total Work = Outer × Inner

This works only when the inner loop executes the same number of times for every outer loop iteration.


2. For sequential loops:

Total Work = Loop1 + Loop2

1,2,4,8,16  which means i is multiplied by 2 in each iteration  - LogN


3. for (int j = 1; j < n; j *= 2)

the exact number of executions is:log₂(n)

4. for (int j = 1; j < i; j++)

1 + 2 + 3 + ... + n = n(n+1)/2 => O(n^2)

5. for (i = n; i > 1; i /= 2)

and inside it costs: O(i)

then:

n + n/2 + n/4 + ... = O(n)

If inside costs: O(i²)

then:

n² + n²/4 + n²/16 + ... = O(n²)

If inside costs:O(i³)

then:

n³ + n³/8 + n³/64 + ...  = O(n³)