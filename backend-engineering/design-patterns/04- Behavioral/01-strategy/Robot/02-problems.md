# Problems in Bad Design

| Problem                | Explanation                                      |
|------------------------|--------------------------------------------------|
| Multiple Classess      | Wastes memory                                    |
| Duplicate Classes      | Same class define twice to inherit               |
| Modification Difficult | Adding Flyable behaviour need lot of changes     |
| OCP break              | Change hierarchy means changes in existing class |

---

# Real Production Impact

```text
Adding flyable and non flyable behaviour

need 4 new classes 

and changes in existing class.
```

Key Learning
We need:

✅ remove code duplicate

✅ solution of inheritance is not more inheritance

✅ OCP should not break


<details> <summary>Interview Insight</summary>

Strategy solves:
1. Code Duplication
2. Inheritance Problem
3. OCP Violation
4. Runtime Behavior Change
5. Flexibility and Extensibility
6. Testing and Maintenance
</details>