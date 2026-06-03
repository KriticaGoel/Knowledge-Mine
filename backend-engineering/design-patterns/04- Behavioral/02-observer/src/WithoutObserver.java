package designPattern.behaviour.observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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