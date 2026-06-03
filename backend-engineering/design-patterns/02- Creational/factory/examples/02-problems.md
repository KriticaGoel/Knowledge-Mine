# Problems in Bad Design

| Problem                | Explanation                                           |
|------------------------|-------------------------------------------------------|
| Giant if and else      | Wastes memory                                         |
| Modification Difficult | Adding new type of file need change in main class too |
| OCP break              | Change hierarchy means changes in existing class      |
| tight coupling          | Client code is tightly coupled with concrete classes   |
| Client knows concrete classes | Client code needs to know about all the concrete classes to create objects |


---

# Real Production Impact

```text
Adding  new file type need modification in main class and need to create a concreate class.
```

Key Learning
We need:


✅ OCP should not break

Imagine a payment processor that needs to support multiple payment methods (Credit Card, PayPal, Apple Pay). Without Factory, every time a new payment method is added, the main payment controller needs modification. With Factory, you just register the new implementation.


<details> <summary>Interview Insight</summary>

Factory solves:
1. giant if and else
2. tight coupling
3. OCP Violation
4. Runtime selection of object
5. Flexibility and Extensibility
6. Testing and Maintenance
</details>