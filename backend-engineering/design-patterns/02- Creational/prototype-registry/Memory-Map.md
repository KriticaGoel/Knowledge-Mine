🧠 Memory Map

Prototype Pattern
│
├── Goal
│   └── Create objects by cloning instead of using new
│
├── Why?
│   ├── Object creation expensive
│   ├── Repeated similar objects
│   ├── Complex initialization
│   └── Runtime object creation
│
├── Flow
│   │
│   └── Client
│        ↓
│   Prototype Interface
│        ↓
│      clone()
│        ↓
│ Concrete Prototype
│
├── Steps
│   ├── Create Prototype interface
│   ├── Implement clone()
│   ├── Create copy constructor
│   └── Client requests clone
│
├── Copy Types
│   ├── Shallow Copy
│   └── Deep Copy ✅
│
├── Problem without Registry
│   ├── Client knows all types
│   ├── Duplicate setup
│   ├── Tight coupling
│   └── Hard to add templates
│
├── Registry Solution
│   │
│   ├── Prototype → clone itself
│   ├── Registry → stores templates
│   ├── Loader → initialize once
│   └── Client → asks copy only
│
└── Real use cases
├── Email templates
├── Resume templates
├── Payment workflow
├── Dashboard widgets
├── Game characters
└── ETL jobs