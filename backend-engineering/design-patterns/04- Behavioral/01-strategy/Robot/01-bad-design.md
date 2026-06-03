# Bad Design

## Problem Statement

Implement a Robot that can perform different tasks like walking, talking, and cooking. 
The robot should be able to switch between these behaviors at runtime.

### UML Diagram
![RobotInheritance2.png](../../../../../Images/RobotInheritance2.png)

---

# Bad Code

```java
public class RobotBadDesign {

    static void main(String[] args) {

        Robot campanion = new Campanion("Campanion");
        Robot worker = new Worker("Worker");
        Robot sparrow = new Sparrow("Sparrow");
    }

}

class Robot{
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}

class Talkable extends Robot{

    public void talk(){
        System.out.println("I am talking");
    }
}

class NonTalkable extends Robot{

    public void nonTalk(){
        System.out.println("I am not talking");
    }
}

class Walkable extends Talkable{
    public void walk(){
        System.out.println("I am walking");
    }
}

class NonWalkable extends Talkable{
    public void nonWalk(){
        System.out.println("I am not walking");
    }
}
class WalkableNonTalkable extends NonTalkable{
    public void walk(){
        System.out.println("I am walking");
    }
}

class NonWalkableNonTalkable extends NonTalkable{
    public void nonWalk(){
        System.out.println("I am not walking");
    }
}

class Campanion extends Walkable{
    public Campanion(String name){
        System.out.println("I am camping");
        setName(name);
        System.out.println("My name is "+getName());
        walk();
        talk();
    }

}
class Worker extends WalkableNonTalkable{
    public Worker(String name){
        System.out.println("I am working");
        setName(name);
        System.out.println("My name is "+getName());
        walk();
        nonTalk();
    }

}
class Sparrow extends NonWalkableNonTalkable{
    public Sparrow(String name){
        System.out.println("I am sparrowing");
        setName(name);
        System.out.println("My name is "+getName());
        nonWalk();
        nonTalk();
    }

}
```

<details> <summary>Why Beginner Developers Write This?</summary>
Easy to create objects
No restriction on instantiation
Lack of centralized control
</details> ```