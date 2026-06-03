
import java.util.ArrayList;
import java.util.List;

public class SRPViolation {

    public static void main(String[] args) {
        Product product1 = new Product("Pizza",500.00);
        Product product2 = new Product("Burger",300.00);

        ShoppingCart cart = new ShoppingCart();
        cart.calculateDiscountOnProduct(product1);
        cart.calculateDiscountOnProduct(product2);
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.getTotalPrice();
        cart.inventoryAdjustment(product1);
        cart.inventoryAdjustment(product2);
        cart.saveDataInDB();
    }

}

class Product {
    private final String name;
    private final double price;
    private double discount;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

class ShoppingCart {
    double totalPrice = 0;
    double discountedPrice = 0;
    List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.printf("Added product %s to shopping cart.\n", product.getName());
    }

    public double getTotalPrice() {
       for(Product product: products) {
           totalPrice = totalPrice + product.getDiscount();
       }
        System.out.println("Total price is " + totalPrice);
       return totalPrice;
    }

    public void calculateDiscountOnProduct(Product product) {
        discountedPrice = product.getPrice() - (product.getPrice() * 0.1);
        product.setDiscount(discountedPrice);
        System.out.printf("Discounted Price of %s is %s \n",product.getName(),discountedPrice);
    }

    public void saveDataInDB() {
        System.out.println("Saving data into database...");
    }

    public void inventoryAdjustment(Product product) {
        System.out.println("update Reserve Qty is " + product.getName());
    }

}
