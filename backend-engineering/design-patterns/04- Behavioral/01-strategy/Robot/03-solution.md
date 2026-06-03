# Strategy Solution - Robot Design

## Problem Statement

We want to model different robot behaviors such as:
- talking
- walking
- flying

Not every robot can do all behaviors.  
For example:
- some robots can talk but cannot walk
- some robots can walk but cannot fly
- some robots cannot talk at all

Instead of hardcoding behavior in the `Robot` class, we use the **Strategy Pattern** so behaviors can be changed independently.

---

## Core Idea of Strategy Pattern

The Strategy Pattern says:
- define each behavior as a separate interface
- create multiple implementations for that behavior
- inject the required behavior into the object at runtime

This makes the robot design:
- flexible
- extensible
- easy to maintain

---
## UML Diagram
![StrategyDesignPrinciple.png](../../../../../Images/StrategyDesignPrinciple.png)

---

### Step 1 → Strategy Interfaces

#### Talkable
```java
interface Talkable{
   public void talk();
}

```
#### Walkable
```java
interface Walkable{
   public void walk();
}
```
#### Flyable
```java
interface Flyable{
   public void fly();
}
```

### Step 2 → Concrete Strategies ( define the behavior)

#### Talking Behaviors
```java
class NormalTalk implements Talkable{
    @Override
    public void talk() {
        System.out.println("Normal Talk");
    }
}
class NoTalk implements Talkable{
    @Override
    public void talk() {
        System.out.println("No Talk");
    }
}
```
#### Walking Behaviors
```java
class NormalWalk implements Walkable{
    @Override
    public void walk() {
        System.out.println("Normal Walk");
    }
}

class NoWalk implements Walkable{
    @Override
    public void walk() {
        System.out.println("No Walk");
    }
}
```
#### Flying Behaviors
```java
class NormalFly implements Flyable{
    @Override
    public void fly() {
        System.out.println("Normal Fly");
    }
}
class NoFly implements Flyable{
    @Override
    public void fly() {
        System.out.println("No Fly");
    }
}
```
### Step 3 → Context ( Calling Strategy at run time)

#### Robot Class
```java

abstract class Robot{
    Talkable talkBehavior;
    Walkable walkBehavior;
    Flyable flyBehavior;

    Robot(Talkable t, Walkable w, Flyable f){
        this.talkBehavior = t;
        this.walkBehavior = w;
        this.flyBehavior = f;
    }

    public void talk(){
        talkBehavior.talk();
    }
    public void walk(){
        walkBehavior.walk();
    }
    public void fly(){
        flyBehavior.fly();
    }

    public abstract void  display();// Abstract method for subclasses

}
```
#### Concrete Robots
```java
class Companion extends Robot{
    public Companion(Talkable t, Walkable w, Flyable f){
        super(t, w, f);
    }
    public void display(){
        System.out.println("Companion Robot");
    }
}

class Worker extends Robot{
    public Worker(Talkable t, Walkable w, Flyable f){
        super(t, w, f);
    }
    public void display(){
        System.out.println("Worker Robot");
    }
}

class Sparrow extends Robot{
    public Sparrow(Talkable t, Walkable w, Flyable f){
        super(t, w, f);
    }
    public void display(){
        System.out.println("Sparrow Robot");
    }
}

```

### Step 3 → Client Code ( Using the Robot)

#### Application
```java
public class RobotStrategyDesign {

    static void main(String[] args) {
        Robot companion = new Companion(new NormalTalk(), new NormalWalk(), new NoFly());
        companion.display();
        companion.talk();
        companion.walk();
        companion.fly();

        Robot worker = new Worker(new NormalTalk(), new NormalWalk(), new NormalFly());
        worker.display();
        worker.talk();
        worker.walk();
        worker.fly();

        Robot sparrow = new Sparrow(new NormalTalk(), new NoWalk(), new NormalFly());
        sparrow.display();
        sparrow.talk();
        sparrow.walk();
        sparrow.fly();
    }
}
```

<details> <summary><h3>Why This Is Strategy Pattern</h3></summary>
This design follows Strategy because:

* each behavior is separated into its own interface
* behavior can vary independently
* the Robot class delegates actions to behavior objects
* we can change behavior at runtime using setters
</details>

---

<details> <summary><h3>Advantages</h3></summary>

* Open/Closed Principle: add new behaviors without changing existing code
* Single Responsibility Principle: each behavior class has one job
* Liskov Substitution Principle: any implementation of Talkable, Walkable, or Flyable can be used safely
* Cleaner design: no if-else or switch for behavior selection
</details>

---

<details> <summary><h3>Important Concept</h3></summary>

The Strategy Pattern helps us replace inheritance-heavy designs with composition.
Instead of forcing every robot to inherit all behaviors, we assign only the behaviors it actually supports.

</details>