# Problems in Bad Design

| Problem | Explanation |
|---|---|
| Multiple Objects | Wastes memory |
| Inconsistent State | Different instances maintain different data |
| Difficult Debugging | Logs scattered |
| Resource Waste | Heavy objects repeatedly created |

---

# Real Production Impact

```text
1000 requests
→ 1000 logger objects
→ unnecessary memory usage
```

Key Learning
We need:

✅ Only one object
✅ Shared globally
✅ Controlled access


<details> <summary>Interview Insight</summary>

Singleton solves:

centralized object management
memory optimization
shared configuration
</details> ```