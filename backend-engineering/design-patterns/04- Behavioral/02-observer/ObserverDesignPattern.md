Observer Design Pattern is a behavioral design pattern that defines a one-to-many dependency between objects, allowing multiple observers to be notified and updated automatically when the state of the subject changes. 

It promotes loose coupling between the subject and its observers, enabling dynamic relationships and enhancing flexibility in software design.

### Key Components
1. **Subject**: The object that holds the state and notifies observers of any changes
2. **Observer**: The object that wants to be notified of changes in the subject
3. **Concrete Subject**: A specific implementation of the subject that maintains state and notifies observers
4. **Concrete Observer**: A specific implementation of the observer that reacts to changes in the subject.
5. **Client**: The entity that creates the subject and observers, and establishes their relationships.
  

### Problem

Create a system where if inventory get updated then other service get notified and update their data accordingly.
### Solution
```java

public class WithoutObserver {
    static void main(String[] args) {

                InventoryObservableWOImpl observable = new InventoryObservableWOImpl();
                InventoryObserverWO observer = new InventoryObserverWOImpl(observable);
                Scanner sc = new Scanner(System.in);

                while (true) {
                    try {
                        System.out.println("Enter the number of items in the inventory:");
                        int inventoryCount = sc.nextInt();
                        observable.update(inventoryCount);
                        observer.getUpdate();
                        Thread.sleep(2000);
                    } catch (Exception ex) {
                        System.out.println("Exception caught " + ex.getMessage());
                    }
                }
        }


}



interface InventoryObserverWO{
    void getUpdate();
}

class InventoryObserverWOImpl implements InventoryObserverWO {

    private final InventoryObservableWOImpl inventoryObservableWO;

    InventoryObserverWOImpl(InventoryObservableWOImpl inventoryObservableWO) {
        this.inventoryObservableWO = inventoryObservableWO;
    }

    @Override
    public void getUpdate() {

        System.out.println("Check for Inventory Update");
        InventoryWO inventory = inventoryObservableWO.notifyUpdate();
        if (inventory != null) {
            InventoryServiceWO inventoryServiceWO = new InventoryServiceImplWO();
            inventoryServiceWO.updateInventory(inventory);
        }
    }
}


class InventoryWO{
    int productId;
    String productName;
    int soh;
}

interface InventoryServiceWO{
    void updateInventory(InventoryWO inventoryWO);
}

class InventoryServiceImplWO implements InventoryServiceWO{

    @Override
    public void updateInventory(InventoryWO inventoryWO) {
        System.out.println("Inventory updated for product "+inventoryWO.productName);
    }
}



interface InventoryObserableWO{
    void update(int inventory);
    InventoryWO notifyUpdate();
}

class InventoryObservableWOImpl implements InventoryObserableWO{
    InventoryWO inventoryWO = null;

    @Override
    public void update(int inventory) {
        System.out.println("Inventory updated for product with inventory count "+inventory);
        if(inventory == 10){
            inventoryWO = new InventoryWO();
            inventoryWO.productId = 1;
            inventoryWO.productName = "Mobile";
            inventoryWO.soh = inventory;
            change(inventoryWO);

        }
    }
    List<InventoryWO> inventory = new ArrayList<>();
    void change(InventoryWO inventoryWO){
        inventory.add(inventoryWO);
    }
    @Override
    public InventoryWO notifyUpdate(){
        System.out.println("Notify Update Called " +inventory.size());
        for(InventoryWO inventoryWO : inventory){
            if(inventoryWO.soh == 10){
                return inventoryWO;
            }
        }
        return null;

    }
}
```
❌ Not Real Time
❌ Too Many network call
❌ Waste of resource

### Observer Design Pattern Solution

![ObserverDesignPattern.png](../../../../Images/ObserverDesignPattern.png)
```java

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

```



















































































































