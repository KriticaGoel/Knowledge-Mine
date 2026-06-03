SRP → One Responsibility

OCP → Extend, Don't Modify

LSP → Replace Child ↔ Parent Safely

DIP → Depend on Abstractions


| If You See               | SOLID Violation |
| ------------------------ | --------------- |
| God class                | SRP             |
| Large if-else            | OCP             |
| Child throwing exception | LSP             |
| Unused interface methods | ISP             |
| Hardcoded dependency     | DIP             |

| SOLID Principle             | Bad Design One-Liner                        | Typical Smell                     | Better Design One-Liner                        |
| --------------------------- | ------------------------------------------- | --------------------------------- | ---------------------------------------------- |
| SRP (Single Responsibility) | One class doing multiple jobs               | God class                         | Split responsibilities into focused classes    |
| OCP (Open Closed)           | New feature requires modifying old code     | Giant if-else / switch            | Extend using interfaces and polymorphism       |
| LSP (Liskov Substitution)   | Child class cannot fully behave like parent | UnsupportedOperationException     | Create proper abstractions                     |
| ISP (Interface Segregation) | Class forced to implement unused methods    | Fat interface                     | Create small focused interfaces                |
| DIP (Dependency Inversion)  | High-level class depends on concrete class  | `new EmailService()` inside class | Depend on abstractions and inject dependencies |

### Golden Lines
SRP

One class → one reason to change.

OCP

Extend behavior without modifying stable code.

LSP

Child should behave like parent.

ISP

Keep interfaces small and focused.

DIP

High-level modules should not depend on low-level modules.