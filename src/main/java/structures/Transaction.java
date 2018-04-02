package structures;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Transaction {
    private final int id;
    private final int customerId;
    private final String timestamp;
    private final Item[] items;
    private final BigDecimal sum;

    public Transaction(int id, int customerId, LocalDateTime date, Item[] listOfItems, double sum) {
        this.id = id;
        this.customerId = customerId;
        this.timestamp = date.toString().substring(0,23) + "-0100";
        this.items = listOfItems;
        this.sum = new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP);

    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDate() {
        return timestamp;
    }

    public Item[] getListOfItems() {
        return items;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
