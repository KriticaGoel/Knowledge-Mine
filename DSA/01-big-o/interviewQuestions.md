Q1.

```java
for (int i = 1; i <= n; i++) {
    for (int j = i; j <= n; j++) {
        for (int k = 1; k <= j; k *= 2) {
            System.out.println(k);
        }
    }
}
```

Q2.

```java
for (int i = n; i > 1; i /= 2) {
    for (int j = 0; j < i; j++) {
        for (int k = 0; k < j; k++) {
            System.out.println(k);
        }
    }
}
```