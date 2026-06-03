### Facade Design Pattern

It's a structural design pattern which provides a simplified interface to a complex subsystem. 

It hides the complexities of the subsystem and provides an easy-to-use interface for clients to interact with.


### Example:
```java
public class FacadeDesignPattern {  

    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.startComputer();
    }
}

```
```java
public class ComputerFacade {

    public void startComputer() {

        open();
        create();
        update();
        save();
        close();

    }

    public void open(){
        System.out.println("Opening the computer...");
    }
    public void create(){
        System.out.println("Creating a new file...");
    }

    public void update(){
        System.out.println("Updating the file...");
    }

    public void save(){
        System.out.println("Saving the file...");
    }

    public void close(){
        System.out.println("Closing the computer...");
    }
}

```
