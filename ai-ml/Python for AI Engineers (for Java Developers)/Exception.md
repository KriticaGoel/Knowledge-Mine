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