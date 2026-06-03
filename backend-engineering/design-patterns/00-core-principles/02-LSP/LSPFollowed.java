package solid.lsp;

import java.util.Optional;

public class LSPFollowed {

    static void main(String[] args) {
        Product product1 = new Product("AB", 500.00);
        Product product2 = new Product("Burger", 300.00);

        new SaveToSqlDBWithId().saveAndReturnId(product1);
        new SaveToMangoDB().findById(product1.getId());
        new SaveToFile().save(product1);

        InventoryAdj inventoryAdj = new InventoryAdj();
        inventoryAdj.updateAvailableQty(product1, -1);
        inventoryAdj.updateReservedQty(product1, 1);

        ShoppingCart cart = new ShoppingCart(new SaveToSqlDBWithId());
        cart.checkout(product1);
    }
}

class Product {
    private String id;
    private String name;
    private double price;

    // constructors
    public Product() {}

    public Product( String name, double price) {

        this.name = name;
        this.price = price;
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

class PersistenceException extends RuntimeException {
    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}

interface DataWriter<T> {
    void save(T data) throws PersistenceException;
}

interface IdGeneratingWriter<T> extends DataWriter<T> {
    String saveAndReturnId(T data) throws PersistenceException;
}

interface TransactionalWriter<T> extends DataWriter<T> {
    void begin();
    void commit();
    void rollback();
}

interface QueryableWriter<T, ID> extends DataWriter<T> {
    Optional<T> findById(ID id);
}

class SaveToSqlDBWithId implements IdGeneratingWriter<Product> {

    @Override
    public void save(Product product) throws PersistenceException {
        saveAndReturnId(product);
    }

    @Override
    public String saveAndReturnId(Product product) throws PersistenceException {
        // persist and return generated key
        System.out.println("Saving product to SQL DB and returning id");
        return "sql-generated-id";
    }
}

class SaveToMangoDB implements QueryableWriter<Product, String> {

    @Override
    public void save(Product product) throws PersistenceException {
        System.out.println("Saving product to MongoDB: " + product.getName());
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }
}

class SaveToFile implements DataWriter<Product> {

    @Override
    public void save(Product product) throws PersistenceException {
        // write to file
        System.out.println("Saving product to file: " + product.getName());
    }
}

class InventoryAdj {

    public void updateAvailableQty(Product product, int delta) {
        System.out.println("Updating available qty for " + product.getName());
    }

    public void updateReservedQty(Product product, int delta) {
        System.out.println("Updating reserved qty for " + product.getName());
    }
}

class ShoppingCart {

    private final IdGeneratingWriter<Product> productWriter;

    public ShoppingCart(IdGeneratingWriter<Product> productWriter) {
        this.productWriter = productWriter;
    }

    public String checkout(Product product) {
        return productWriter.saveAndReturnId(product);
    }
}