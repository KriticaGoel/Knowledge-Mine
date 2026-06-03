### SRP

#### Definition:
A class should have only one reason to change.

#### Violation:
One class handles multiple responsibilities.

#### Fix:
Move each responsibility into its own class.

#### Benefits:
* Maintainable
* Testable
* Reusable
* Low Coupling

#### Rule:
1 Class = 1 Job

---

### OCP

#### Definition:
Open for Extension

Closed for Modification

#### Violation:
Need to modify existing code for new features.

#### Fix:
Use Interface/Abstraction.

#### Rule:
Add New Class ✅

Change Existing Class ❌

#### Example:
Payment Methods
Storage Providers
Notification Channels

---

### LSP

#### Definition:

Child objects should be replaceable with Parent objects without breaking program behavior.

#### Violation:

Child changes behavior, assumptions, exceptions, or guarantees.

#### Fix:

Create correct abstractions and ensure all implementations honor the same contract.

#### Benefits:

* Predictable Behavior
* Safe Polymorphism
* Better Maintainability
* Strong Contracts
* Less Runtime Failure

#### Checks:
✓ Same or weaker preconditions
✓ Same or stronger postconditions
✓ Same or narrower exceptions
✓ Preserve parent invariants

#### Rule:

Child should behave like Parent.

Replace Child ↔ Parent without breaking code.

#### Question:
Can I replace Parent with Child without breaking the code?

If NO → LSP Violated
If YES → LSP Followed

---

### DIP

#### Definition:

High-Level Modules should not depend on Low-Level Modules.

Both should depend on Abstractions.

#### Violation:

Business logic directly depends on concrete implementations.

#### Fix:

Introduce Interface/Abstraction and depend on it.

#### Benefits:

* Loose Coupling
* Easy Testing
* Easy Extension
* Better Maintainability
* Supports Dependency Injection

#### Rule:

Depend on Interfaces, Not Implementations.

#### Question:
Can I replace the implementation
without changing business logic?

If NO → DIP Violated

If YES → DIP Followed

---
