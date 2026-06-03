package designPattern.behaviour.observer;

import java.util.ArrayList;
import java.util.List;

public class WithObservable {

    static void main(String[] args) {
        Observable observable = new InventoryObservable(new EmailIObserver());
        observable.addObserver(new WhatsAppIObserver());
        observable.addObserver(new InventoyServiceIObserver(new InventoryServiceImpl()));
        Inventory inventory = new Inventory(1, "Mobile", 10);
        observable.notifyObservers(inventory);

    }
}

class Inventory{
    int id;
    String productName;
    int soh;
    Inventory(int id, String productName, int soh){
        this.id = id;
        this.productName = productName;
        this.soh = soh;
    }
}

interface InventoryService{
    void updateInventory(Inventory inventory);
}

class InventoryServiceImpl implements InventoryService{
    @Override
    public void updateInventory(Inventory inventory) {
        System.out.println("inventory get updated");
    }
}

interface Observable{
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(Inventory inventory);
}

class InventoryObservable implements Observable{
    List<IObserver> observers = new ArrayList<>();

    public InventoryObservable(IObserver observable){
            addObserver(observable);
    }


    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Inventory inventory) {
        observers.forEach(observer -> observer.getupdate(inventory));

    }
}

interface IObserver{

    void getupdate(Inventory inventory);

}

class EmailIObserver implements IObserver{
    @Override
    public void getupdate(Inventory inventory) {
        System.out.println("Email sent Inventory get updated");
    }
}

class WhatsAppIObserver implements IObserver{
    @Override
    public void getupdate(Inventory inventory) {
        System.out.println("WhatsApp sent Inventory get updated");
    }
}

class InventoyServiceIObserver implements IObserver{

    private InventoryServiceImpl inventoryService;
    public InventoyServiceIObserver(InventoryServiceImpl inventoryService){
        this.inventoryService = inventoryService;
    }
    @Override
    public void getupdate(Inventory inventory) {
        inventoryService.updateInventory(inventory);
    }
}
