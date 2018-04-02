package structures;

public class Item {
    private final String name;
    private final int quantity;
    private final double price;

    public Item(String name, double price) {
        this.name = name;
        this.quantity = 0;
        this.price = price;
    }

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "name: " + name  +
                ",quantity:"  + quantity +
                ",price:"  + price +
                "}";
    }
}
