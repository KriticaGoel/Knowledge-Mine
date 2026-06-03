package designPattern.structural.facade;

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
