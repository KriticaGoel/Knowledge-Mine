package designPattern.behaviour.strategy.robot;

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

// --- Strategy Interface for Talk ---
interface Talkable{
    public void talk();
}
// --- Strategy Interface for Walk ---
interface Walkable{
    public void walk();
}
// --- Strategy Interface for Fly ---
interface Flyable{
    public void fly();
}

// --- Concrete Strategies for talk ---
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

// --- Concrete Strategies for walk ---
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

// --- Concrete Strategies for fly ---
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

//--Context Robot Base Class ---
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

// --- Concrete Robot Types ---
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
