# Python Name Mangling Explained

## What is Name Mangling?

Name mangling is Python's mechanism to **rename attributes with double underscores** to make them harder to access accidentally from outside a class. It's **NOT true privacy**—the data is still accessible if you know the mangled name.

---

## The Basics

### Without Name Mangling (Single Underscore)
```python
class BankAccount:
    def __init__(self):
        self._balance = 1000  # Protected (by convention)

account = BankAccount()
print(account._balance)  # Output: 1000 ✓ Works (but violates convention)
```

### With Name Mangling (Double Underscore)
```python
class BankAccount:
    def __init__(self):
        self.__balance = 1000  # Private (with name mangling)

account = BankAccount()
print(account.__balance)  # ❌ AttributeError: 'BankAccount' object has no attribute '__balance'
```

---

## How Name Mangling Works

When Python encounters `self.__attribute_name` inside a class, it **internally renames it** to:
```
_ClassName__attribute_name
```

### Example:
```python
class User:
    def __init__(self):
        self.__id = 123

user = User()

# This FAILS:
print(user.__id)  # ❌ AttributeError

# But THIS WORKS (using the mangled name):
print(user._User__id)  # ✓ Output: 123
```

---

## Real-World Example

```python
class Employee:
    def __init__(self, name, salary):
        self.name = name              # Public
        self._dept = "Engineering"    # Protected (convention)
        self.__salary = salary        # Private (name mangled)
    
    def get_salary(self):
        """Only way to access salary safely"""
        return self.__salary

emp = Employee("Kritica", 50000)

# Public - works fine
print(emp.name)  # ✓ Output: Kritica

# Protected - works but violates convention
print(emp._dept)  # ✓ Output: Engineering (but you shouldn't do this)

# Private - doesn't work directly
print(emp.__salary)  # ❌ AttributeError

# But you CAN access it if you use the mangled name (not recommended)
print(emp._Employee__salary)  # ✓ Output: 50000 (HACKY - don't do this!)

# Proper way - use the getter method
print(emp.get_salary())  # ✓ Output: 50000 (CORRECT)
```

---

## Name Mangling in Methods

Double underscores also apply to methods:

```python
class Calculator:
    def add(self, a, b):
        return self.__internal_add(a, b)
    
    def __internal_add(self, a, b):  # Private method
        return a + b

calc = Calculator()

# This works:
print(calc.add(5, 3))  # ✓ Output: 8

# This FAILS:
calc.__internal_add(5, 3)  # ❌ AttributeError

# This works but is hacky:
calc._Calculator__internal_add(5, 3)  # ✓ Works but don't do this!
```

---

## Why Use Name Mangling?

### ✓ **Prevents Accidental Access**
```python
class LibraryBook:
    def __init__(self, title):
        self.__internal_id = 12345
    
    # User won't accidentally access __internal_id
    # They'll use the public methods instead
```

### ✓ **Subclass Safety**
```python
class Parent:
    def __init__(self):
        self.__secret = "parent secret"

class Child(Parent):
    def __init__(self):
        super().__init__()
        self.__secret = "child secret"  # Doesn't override parent's __secret

parent = Parent()
child = Child()

print(parent._Parent__secret)   # Output: parent secret
print(child._Child__secret)     # Output: child secret
# Notice they're different! Name mangling prevents accidental overrides
```

---

## Important Gotchas

### 1. It's NOT True Privacy
```python
class Secret:
    def __init__(self):
        self.__password = "secret123"

s = Secret()
print(s.__dict__)  # ✓ You can see all attributes!
# Output: {'_Secret__password': 'secret123'}
```

### 2. Only Works for Double Underscore PREFIX
```python
class Example:
    def __init__(self):
        self.__public__attr = "NOT mangled"  # Double underscores on BOTH sides
        self___triple = "NOT mangled"        # Triple underscore
        self._single = "NOT mangled"         # Single underscore

ex = Example()
print(ex.__public__attr)  # ✓ Works! (This is for magic methods)
print(ex.___triple)       # ✓ Works!
print(ex._single)         # ✓ Works!
```

### 3. Doesn't Exist Before Class Instantiation
```python
class MyClass:
    __var = "class variable"

print(MyClass.__var)  # ❌ AttributeError (name mangling only for instance attributes)
print(MyClass._MyClass__var)  # ✓ Works!
```

---

## Comparison: Java vs Python

| Feature | Java | Python |
|---------|------|--------|
| `private int id;` | **True privacy** - enforced by compiler | Cannot access from outside class at all |
| `self.__id = 123` | **NOT true privacy** - just naming convention | Can still access as `self._ClassName__id` |
| Enforcement | Compiler checks (compile error) | No enforcement (runtime only) |
| Intent | Prevent access | Discourage access |

---

## Best Practices

### ✓ DO: Use when you need genuine protection within inheritance
```python
class Parent:
    def __init__(self):
        self.__protected = "safe"

class Child(Parent):
    def __init__(self):
        super().__init__()
        self.__protected = "different"  # Doesn't conflict with parent
```

### ✗ DON'T: Don't use for simple privacy
```python
# BAD - Overcomplicating
class User:
    def __init__(self, password):
        self.__password = password  # Unnecessary for most cases

# BETTER - Use single underscore convention
class User:
    def __init__(self, password):
        self._password = password  # Clear intent, simpler
```

### ✓ DO: Use getter/setter methods for controlled access
```python
class BankAccount:
    def __init__(self, balance):
        self.__balance = balance
    
    def get_balance(self):
        return self.__balance
    
    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount

account = BankAccount(1000)
account.deposit(500)  # Safe deposit
print(account.get_balance())  # ✓ Output: 1500
```

---

## Summary Table

| Syntax | Visibility | Enforcement | Use Case |
|--------|-----------|------------|----------|
| `self.var` | Public | None | Public data, accessible anywhere |
| `self._var` | Protected | Convention only | Internal use, discourage external access |
| `self.__var` | Private | Name mangling | Prevent accidental access + inheritance conflicts |
| `self.__var__` | Magic/Special | None | Special methods like `__init__`, `__str__` |

---

## Key Takeaway

**Name mangling with `__` is NOT true privacy like in Java.** It's a **hint to developers** that the attribute shouldn't be accessed directly. It prevents:
- ✓ Accidental access
- ✓ Accidental overriding in subclasses

But it DOES NOT prevent:
- ✗ Intentional access via `_ClassName__attribute`
- ✗ Direct attribute modification
- ✗ Access through `__dict__`

If you need **real privacy**, use **methods and properties** instead!

