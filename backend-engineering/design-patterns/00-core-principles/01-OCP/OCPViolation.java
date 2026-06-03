import java.util.ArrayList;
import java.util.List;

public class OCPViolation {

    static void main(String[] args) {
        OcpProduct product1 = new OcpProduct("Pizza", 500.00);
        OcpProduct product2 = new OcpProduct("Burger", 300.00);

        OcpDiscountEngine discountEngine = new OcpDiscountEngine();
        discountEngine.calculateDiscountAmount(product1);
        discountEngine.calculateDiscountAmount(product2);

        OcpShoppingCart cart = new OcpShoppingCart();
        cart.addItem(product1);
        cart.addItem(product2);

        OcpInventoryAdjustment inventoryAdjustment = new OcpInventoryAdjustment();
        inventoryAdjustment.updateAvailableQty(product1);
        inventoryAdjustment.updateAvailableQty(product2);

        OcpCartPricingService pricingService = new OcpCartPricingService();
        double totalPrice = pricingService.calculateTotal(cart.getItems());
        System.out.println("Total price is " + totalPrice);

        OcpDBManager databaseManager = new OcpDBManager();
        databaseManager.saveDb(java.util.List.of(product1, product2));
        databaseManager.saveMangoDb(java.util.List.of(product1, product2));
        databaseManager.saveFlatFile(java.util.List.of(product1, product2));

    }
}

class OcpProduct {
    private final String name;
    private final double price;
    private double discountAmount;

    public OcpProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}

class OcpShoppingCart {

    private final List<OcpProduct> items = new ArrayList<>();

    public void addItem(OcpProduct item) {
        items.add(item);
        System.out.printf("Added product %s to shopping cart.\n", item.getName());
    }

    public List<OcpProduct> getItems() {
        return new ArrayList<>(items);
    }


}

class OcpCartPricingService {

    public double calculateTotal(List<OcpProduct> items) {
        double total = 0;
        for (OcpProduct item : items) {
            total += item.getPrice() - item.getDiscountAmount();
        }
        return total;
    }
}

class OcpDiscountEngine {

    public double calculateDiscountAmount(OcpProduct product) {
        double discount = product.getPrice() * 0.1;
        product.setDiscountAmount(product.getPrice() - discount);
        System.out.println("Discount for " + product.getName() + " is " + discount);
        return discount;
    }
}


class OcpInventoryAdjustment {
    public void updateAvailableQty(OcpProduct product) {
        System.out.println("Update Available Qty for " + product.getName());
    }

    public void updateReservedQty(OcpProduct product) {
        System.out.println("Update Reserved Qty for " + product.getName());
    }
}


class OcpDBManager {
    public void saveDb(List<OcpProduct> products) {
        for (OcpProduct product : products) {
            System.out.println("Saving SQL database... " + product.getName());
        }
    }

    public void saveMangoDb(List<OcpProduct> products) {
        for (OcpProduct product : products) {
            System.out.println("Saving Mango database... " + product.getName());
        }
    }

    public void saveFlatFile(List<OcpProduct> products) {
        for (OcpProduct product : products) {
            System.out.println("Saving Flat File... " + product.getName());
        }
    }
}
