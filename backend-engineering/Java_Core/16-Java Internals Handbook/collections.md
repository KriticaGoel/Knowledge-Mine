
```
                    Collection
                        │
             ┌──────────┴──────────┐
             ↓                     ↓
        Array-based          Node-based
             │                     │
       ┌─────┴─────┐               ↓
       ↓           ↓           LinkedList
  ArrayList      Vector
       │           │
       │           │
       └─────┬─────┘
             ↓
      Dynamic backing array

ArrayList → not synchronized
Vector    → synchronized legacy collection
```