> When an exception occurs, the JVM creates an exception object in the Heap, immediately captures the current stack trace, checks the method's Exception Table for a matching handler, performs stack unwinding if necessary, jumps to the appropriate catch block if found, and if no handler exists, the default exception handler prints the stored stack trace and terminates the thread.


## 1. Why Java Introduced Exceptions

Before exceptions, languages like C returned error codes.
```
if(result == ERROR){
// Handle error
}
```
Problems:

* Error handling mixed with business logic.
* Easy to forget checking return values.
* Bugs propagate silently.

Java separates **normal execution** from **error handling** using exceptions.

---

## 2. What is an Exception?

> An exception is an object that represents an abnormal condition during program execution.

Example:
```
int x = 10 / 0;
```
JVM internally creates:
```
new ArithmeticException("/ by zero");
```
Remember:

* Exception is **NOT** a keyword.
* Exception is an ordinary Java object.

---

## 3. Exception Hierarchy
```
   Object
     │
   Throwable
  ┌──├──────────────┐
  │                 │
 Error          Exception
                    │
             RuntimeException
                    │
            ArithmeticException
            NullPointerException
            ...
```   
   
### Throwable

Anything that can be thrown must extend Throwable.

-----


### Error

Examples:

* OutOfMemoryError
* StackOverflowError
* VirtualMachineError

Generally not meant to be recovered from.

---

### Exception

Represent conditions an application may handle.

Examples:

* IOException
* SQLException
* ArithmeticException

---

## 4. Checked vs Unchecked Exceptions

### Checked Exception

Compiler forces handling.

Examples:

* IOException
* SQLException
* FileNotFoundException

Need:
```
try-catch
```
or
```
throws
```
### Unchecked Exception

Compiler does not force handling.

Examples:

* NullPointerException
* ArithmeticException
* IndexOutOfBoundsException

Usually indicate programming mistakes.

---

## 5. Exception Lifecycle Inside JVM

Example
```
int x = 10 / 0;
```
Steps:
1. JVM executes bytecode.
2. idiv detects divide by zero.
3. JVM creates ArithmeticException object in Heap.
4. JVM captures current stack trace.
5. JVM checks Exception Table.
6. If handler found → jump to catch.
7. Otherwise → Stack Unwinding.
8. If nobody handles → Default Exception Handler prints stack trace.

## 6. Exception Object Memory

Heap
```
ArithmeticException

message = "/ by zero"

cause = null

stackTrace = ...
```
Exception object lives in Heap.

## 7. Stack Trace

Stack trace is captured **when the exception object is created, not** when printStackTrace() is called.

Reason:

During stack unwinding, stack frames disappear.

So JVM stores a snapshot before removing frames.

Think of it as taking a photograph of the stack.

## 8. Exception Table

Every compiled method contains an Exception Table.

Conceptually:
```
Start   End   CatchAt   Type

0        10      25    ArithmeticException
```
Meaning:

If an _ArithmeticException_ occurs between instructions _0–10_, jump to instruction 25 (catch block).

The JVM uses the Exception Table—not Java source—to locate handlers.

---

## 9. Stack Unwinding

> Removing stack frames one by one until a matching exception handler is found.

Example:
```
method2()
method1()
main()
```
Exception in method2():

No handler → pop method2()

Check method1()

No handler → pop method1()

Check main()

No handler → pop main()

Stack empty → JVM terminates the thread.

---

## 10. PC Register During Exception

Normally:
```
Instruction 10

↓

Instruction 11

↓

Instruction 12
```
During exception:
```
Instruction 12

↓

Exception

↓

Catch Block
```
The PC Register jumps directly to the first instruction of the matching catch block.

If no handler exists, the current frame is popped and the caller's PC Register is restored.

---

## 11. throw vs throws
    
### throw

Executable statement.

Generates athrow bytecode.

Example:
```
throw new RuntimeException();
```
Creates an exception object and starts exception handling.

### throws

Method declaration.

Example:
```
readFile() throws IOException
```

Used only by the compiler for checked exception checking.

Produces no executable bytecode.

---

## 12. finally

finally executes:

* After normal execution.
* After return.
* After exception.

Example:
```
try{
return 10;
}
finally{
System.out.println("Finally");
}
```
Output:
```
Finally
```
Then returns 10.

Never do:
```
finally{
return;
}
```
It can suppress exceptions and override return values.

## 13. Try-With-Resources

Compiler converts:
```
try(Resource r = ...)
```
into code similar to:
```
Resource r = ...

try{

}
finally{

    r.close();

}
```
No special JVM instruction exists.

---

## 14. Chained Exceptions

Example:
```
catch(SQLException e){

    throw new RuntimeException(
        "Unable to fetch product",
        e);

}
```
Heap
```
RuntimeException

↓

cause

↓

SQLException
```
Purpose:

* Preserve original exception.
* Add higher-level business context.
* Hide implementation details.

---

## 15. Suppressed Exceptions

Occurs in Try-With-Resources.

If:

* Main code throws Exception A.
* close() throws Exception B.

Result:
```
Exception A

Suppressed

↓

Exception B
```
Main exception is preserved.

---

## 16. Why Exceptions are Expensive

Creating an exception requires:

* Heap allocation.
* Stack trace capture.
* Exception Table lookup.
* Stack unwinding.
* Control flow jump.

Therefore:

Don't use exceptions for normal program flow.

## 17. Catch Block Ordering

Correct:
```
catch(ArithmeticException e)

catch(RuntimeException e)

catch(Exception e)
```
Wrong:
```
catch(Exception e)

catch(ArithmeticException e)
```
Compiler Error:
```
Unreachable catch block
```
Always catch **most specific** exceptions first.

---

## 18. Why catch(RuntimeException) is Usually Bad

Example:
```
catch(RuntimeException e)
```
It catches:

* ArithmeticException
* NullPointerException
* IllegalArgumentException
* IndexOutOfBoundsException
* ClassCastException

Unexpected programming bugs may be hidden.

Prefer:
```
catch(ArithmeticException e)
```
when you expect only that condition.

Use broad catches mainly at application boundaries (e.g., global exception handlers), where you log and convert failures rather than silently ignoring them.

---

## 19. Senior Interview Flow
```    
Exception occurs
     ↓
Exception object created
     ↓
Stack trace captured
     ↓
Exception Table lookup
     ↓
Handler found?
     ↓
    Yes
     ↓
Jump to catch

    OR
    ↓
Stack Unwinding
    ↓
  Caller
    ↓
  Caller
    ↓
No handler
    ↓
Default JVM Exception Handler
    ↓
Thread terminates
```

---

## Stack Unwinding (The Heart of Exception Handling)
```
method2()
      │
      ▼
Exception created in Heap
      │
      ▼
Check Exception Table
      │
      ▼
No handler?
      │
      ▼
Pop current frame
      │
      ▼
Check caller
      │
      ▼
Handler found?
      │
   Yes ─────────► Execute catch block
      │
      No
      │
      ▼
Stack becomes empty
      │
      ▼
Default JVM exception handler prints stack trace
      │
      ▼
Program terminates

```