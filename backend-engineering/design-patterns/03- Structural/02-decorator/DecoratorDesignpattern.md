Decorator lets you add behavior to an object dynamically without modifying its original class.

### Problem: 

You have a class, and you want to add new behavior to it without changing its code.

Coffee
├── MilkCoffee
├── SugarCoffee
├── MilkSugarCoffee
├── MilkSugarCreamCoffee
└── ...

* inheritance grows uncontrollably
* difficult maintenance
* violates Open/Closed Principle
* adding combinations means creating more classes

Decorator solves this by composition over inheritance.

Without Decorator
```java
class Notification {}
class EmailNotification extends Notification {}
class SmsNotification extends Notification {}
class EmailSmsNotification extends Notification {}
class EmailSmsSlackNotification extends Notification {}
```

With Decorator
```java
interface Notification {
    void send(String message);
}
class EmailNotification implements Notification {
    public void send(String message) {
        // send email
    }
}
class SmsNotification implements Notification {
    public void send(String message) {
        // send sms
    }
}
class NotificationDecorator implements Notification {
    protected Notification decoratedNotification;
    public NotificationDecorator(Notification decoratedNotification) {
        this.decoratedNotification = decoratedNotification;
    }
    public void send(String message) {
        decoratedNotification.send(message);
    }
}
class EmailDecorator extends NotificationDecorator {
    public EmailDecorator(Notification decoratedNotification) {
        super(decoratedNotification);
    }
    public void send(String message) {
        super.send(message);
        // add email sending logic
    }
}
class SmsDecorator extends NotificationDecorator {
    public SmsDecorator(Notification decoratedNotification) {
        super(decoratedNotification);
    }
    public void send(String message) {
        super.send(message);
        // add sms sending logic
    }
}
```

#### Benefits
1. Add behavior at runtime

Choose dynamically:
```java
notification =
new SmsDecorator(
new EmailDecorator(
new BasicNotification()));
```
No code changes needed.

2. Avoid class explosion

Instead of:

PizzaWithCheese
PizzaWithCheeseAndOlives
PizzaWithCheeseOlivesMushroom

Use decorators.

3. Open/Closed Principle

Open for extension.

Closed for modification.

Existing code untouched.

4. Flexible combinations

Mix and match features.
```java
Coffee c =
new SugarDecorator(
new MilkDecorator(
new BasicCoffee()));
```

5. Follows composition over inheritance

Large inheritance trees become smaller.

### When to use

Use when:

✅ optional features

✅ runtime behavior changes

✅ feature combinations

✅ class explosion risk

✅ wrappers around existing code

Examples:

notification systems
caching
logging
encryption
compression
filters
UI components

### When NOT to use

Avoid if:

❌ behavior is fixed

❌ only one variation exists

❌ decorators become deeply nested

Bad:

new A(
new B(
new C(
new D(
new E()))))

Hard to debug.

❌ object identity matters

Because wrapper chain complicates things.


## Real-world Java/Spring examples
1. Java I/O (classic interview answer)
```
   InputStream in=
   new BufferedInputStream(
   new FileInputStream());
```
Decorator chain:

FileInputStream
↓
BufferedInputStream

Buffer behavior added.

2. Spring Security Filters

   Request
   ↓
   Authentication
   ↓
   Authorization
   ↓
   JWT Filter
   ↓
   Logging

Each wraps processing.

Decorator-like behavior.

3. HTTP Request Wrappers

   HttpServletRequestWrapper

Adds capabilities without changing original request.

4. Caching

   Service
   ↓
   CachingDecorator

Add cache without touching service.

5. Notification system
```
   Notification n =
   new SlackDecorator(
   new SmsDecorator(
   new EmailDecorator(
   new BasicNotification())));
```

Send through multiple channels.

### Brain shortcut for pattern detection

Ask:

"Do I need to add optional layers/features around an object without modifying it?"

If yes → Decorator should immediately come to mind.

For your Java/Spring projects, watch for words like:

add logging
add cache
add retry
add metrics
add compression
add security

Those are strong decorator signals.

### Smells

| Question                                | Example                                                                               |                      |
|-----------------------------------------|---------------------------------------------------------------------------------------|----------------------|
| Am I creating too many subclasses?      | BasicReport </br>PdfReport </br> PdfEncryptedReport </br>PdfEncryptedCompressedReport | Huge smell.          |
| Is behavior optional?                   | Mandatory behavior → inheritance may work.<br/>Optional → decorator.                  | Good candidate.      |
| Can features be combined independently? | If feature A + B + C can coexist.     <br/>   SMS + Email + Slack                     | Decorator            |
| Will behavior change dynamically?       | Runtime addition :</br> if(premium) </br> addDiscount();                              | Decorator candidate. |
| Will combinations grow?                 | A</br>B</br>C</br>AB</br>AC</br>BC</br>ABC                                            |                      |
| Do I need runtime flexibility           | Can user enable/disable features?                                                     |                      |

Code

```java

```java

public class IcecreamDecorator {

    static void main(String[] args) {

        IceCream ic = new ButterScotchScoop (new OrangeCone( new ChocolateSyrup( new OrangeCone())));
        System.out.println(ic.getCost());
        System.out.println(ic.getDescription());
    }
}

interface IceCream {
    Double getCost();
    String getDescription();
}


class OrangeCone implements IceCream {

    private IceCream icecream;
    public OrangeCone() {}
    public OrangeCone(IceCream icecream) {
        this.icecream = icecream;
    }
    @Override
    public Double getCost() {
        if (icecream!= null && icecream.getCost() != null) {
            return icecream.getCost()+10.00;
        }
        return 10.00;
    }

    @Override
    public String getDescription() {
        if (icecream!= null && icecream.getDescription() != null) {
            return icecream.getDescription()+ " Orange Cone";
        }
        return "Orange Cone";
    }
}

class ChocolateSyrup implements IceCream {

    private final IceCream icecream;

    public ChocolateSyrup(IceCream icecream) {
        this.icecream = icecream;
    }
    @Override
    public Double getCost() {
        return icecream.getCost() +20.00;
    }

    @Override
    public String getDescription() {
        return  icecream.getDescription() +" Chocolate Syrup";
    }
}

class ButterScotchScoop implements IceCream {
    private final IceCream icecream;
    public ButterScotchScoop(IceCream icecream) {
    this.icecream = icecream;
    }
    @Override
    public Double getCost() {
    return icecream.getCost() + 30.00;
    }
    @Override
    public String getDescription() {
        return icecream.getDescription() + " Butter Scotch Scoop";
    }
}
```
