package solid.lsp;

import java.util.List;



public class LSPViolation {

    static void main(String[] args) throws Exception {
        LSPProduct product1 = new LSPProduct("AB", "500.00");
        LSPProduct product2 = new LSPProduct("Burger", "300.00");

        new SaveToSqlDatabase().processData(List.of(product1, product2));
        try {
            new SaveToMQLDatabase().processData(List.of(product1, product2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            new SaveToFlatFile().processData(List.of(product1, product2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

class LSPProduct {
    String name;
    String price;

    LSPProduct(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

//Contract between client and server.
interface LSPDBManager {
    void processData(List<LSPProduct> products) throws Exception;

}

class SaveToSqlDatabase implements LSPDBManager {
    @Override
    public void processData(List<LSPProduct> products) throws Exception {
        for (LSPProduct product : products) {
            if (product.getName().length() < 5) {
                System.out.println("Product name is not valid so data will not be saved");
            }
            System.out.println("Saving SQL Data... " + product.getName());
        }
    }
}

class SaveToMQLDatabase implements LSPDBManager {
    @Override
    public void processData(List<LSPProduct> products) throws Exception {
        for (LSPProduct product : products) {
            if (product.getName().length() < 5) {
                throw new Exception("Product name is not valid so data will not be saved");
            }
            System.out.println("Saving SQL Data... " + product.getName());
        }
    }
}

class SaveToFlatFile implements LSPDBManager {
    @Override
    public void processData(List<LSPProduct> products) throws Exception {
        for (LSPProduct product : products) {
            throw new Exception("File not found");
            //  System.out.println("Saving SQL Data... " + product.getName());
        }
    }
}
