## Exception handling

In Python, exceptions are errors that occur during the execution of a program. Exception handling allows you to gracefully handle these errors and prevent your program from crashing. The main components of exception handling in Python are `try`, `except`, `else`, and `finally` blocks.

|Block|	Purpose|
|----|---|
|try|	Code that may fail|
|except|	Handles an exception|
|else|	Runs when no exception occurs|
|finally|	Runs whether an exception occurs or not|

Example
```
try:
    price = float("100")
except ValueError:
    print("Invalid price")
else:
    print("Price accepted")
finally:
    print("Processing completed")
```
Output:
```
Price accepted
Processing completed
```

Important exceptions
- ValueError: Invalid value conversion.
- TypeError: Incompatible types.
- KeyError: Dictionary key missing.
- IndexError: Invalid list index.
- FileNotFoundError: File doesn't exist.
- ZeroDivisionError: Division by zero.

### Raising Exceptions

```
def check_age(age):
    if age < 0:
        raise ValueError(f'Age cannot be negative: {age}')
    if age > 150:
        raise ValueError(f'Age seems unrealistic: {age}')
    return f'Age {age} is valid'
    
    
try:
    print(check_age(-5))
except ValueError as e:
    print(e) # Age cannot be negative: -5
```

### Custom Exceptions
```
# Define a custom exception
class InsufficientFundsError(Exception):
    def __init__(self, balance, amount):
        super().__init__(f'Balance {balance} is less than {amount}')
        self.balance = balance
        self.amount = amount

# Use it
def withdraw(balance, amount):
    if amount > balance:
        raise InsufficientFundsError(balance, amount)
    return balance - amount

try:
    withdraw(100, 500)
except InsufficientFundsError as e:
    print(e) # Balance 100 is less than 500
```

