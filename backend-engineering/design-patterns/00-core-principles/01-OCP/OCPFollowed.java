
import java.util.ArrayList;
import java.util.List;

public class OCPFollowed {
    static void main(String[] args) {
        Product1 product1 = new Product1("Pizza", 500.00);
        Product1 product2 = new Product1("Burger", 300.00);

        DiscountEngine discountEngine = new DiscountEngine();
        discountEngine.calculateDiscountAmount(product1);
        discountEngine.calculateDiscountAmount(product2);

        ShoppingCart1 cart = new ShoppingCart1();
        cart.addItem(product1);
        cart.addItem(product2);

        InventoryAdjustment inventoryAdjustment = new InventoryAdjustment();
        inventoryAdjustment.updateAvailableQty(product1);
        inventoryAdjustment.updateAvailableQty(product2);

        CartPricingService pricingService = new CartPricingService();
        double totalPrice = pricingService.calculateTotal(cart.getItems());
        System.out.println("Total price is " + totalPrice);

        new SaveToSqlDatabase().processData(cart.getItems());
        new SaveToMQLDatabase().processData(cart.getItems());
        new SaveToFlatFile().processData(cart.getItems());

    }
}

class Product1 {
    private final String name;
    private final double price;
    private double discountAmount;

    public Product1(String name, double price) {
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

class ShoppingCart1 {

    private final List<Product1> items = new ArrayList<>();

    public void addItem(Product1 item) {
        items.add(item);
        System.out.printf("Added product %s to shopping cart.\n", item.getName());
    }

    public List<Product1> getItems() {
        return new ArrayList<>(items);
    }


}

class CartPricingService {

    public double calculateTotal(List<Product1> items) {
        double total = 0;
        for (Product1 item : items) {
            total += item.getPrice() - item.getDiscountAmount();
        }
        return total;
    }
}

class DiscountEngine {

    public double calculateDiscountAmount(Product1 product) {
        double discount = product.getPrice() * 0.1;
        product.setDiscountAmount(product.getPrice() - discount);
        System.out.println("Discount for " + product.getName() + " is " + discount);
        return discount;
    }
}


class InventoryAdjustment {
    public void updateAvailableQty(Product1 product) {
        System.out.println("Update Available Qty for " + product.getName());
    }

    public void updateReservedQty(OcpProduct product) {
        System.out.println("Update Reserved Qty for " + product.getName());
    }
}


interface DBManager {
   public void processData(List<Product1> products);
}

class SaveToSqlDatabase implements DBManager {
    @Override
    public void processData(List<Product1> products) {
        for (Product1 product : products) {
            System.out.println("Saving SQL Data... " + product.getName());
        }
    }
}

class SaveToMQLDatabase implements DBManager {
    @Override
    public void processData(List<Product1> products) {
        for (Product1 product : products) {
            System.out.println("Saving SQL Data... " + product.getName());
        }
    }
}

class SaveToFlatFile implements DBManager {
    @Override
    public void processData(List<Product1> products) {
        for (Product1 product : products) {
            System.out.println("Saving SQL Data... " + product.getName());
        }
    }
}


