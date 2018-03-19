import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TransactionGenerator {
    private TransactionConfiguration transactionConfiguration;
    private final RandomsGenerator randomsGenerator;
    private Item[] availableItemsArray;


    public TransactionGenerator(TransactionConfiguration transactionConfiguration, Item[] items) {
        this.transactionConfiguration = transactionConfiguration;
        this.availableItemsArray = items;
        this.randomsGenerator = new RandomsGenerator();
    }

    public List<Transaction> generateTransactions () {
        List<Transaction> listOfTransactions = new LinkedList<>();
        int numberOfTrans = Integer.valueOf(transactionConfiguration.getNumberOfTrans());

        for(int i = 0; i < numberOfTrans; i++) {
            int customerId = randomsGenerator.getInteger(transactionConfiguration.getRangeOfCustomerId()[0],
                    transactionConfiguration.getRangeOfCustomerId()[1]);
            int itemsCount = randomsGenerator.getInteger(transactionConfiguration.getRangeOfnumberOfItems()[0],
                    transactionConfiguration.getRangeOfnumberOfItems()[1]);
            LocalDateTime timestemp = randomsGenerator.getTimestamp(transactionConfiguration.getRangeOfDate());
            Item[] itemsInTran = new Item[itemsCount];
            double sum = 0;
            for(int j = 0; j < itemsCount; j++){
                int randomItemIndex = randomsGenerator.getInteger(0, availableItemsArray.length);
                int quantity = randomsGenerator.getInteger(transactionConfiguration.getRangeOfQuantities()[0],
                        transactionConfiguration.getRangeOfQuantities()[1]);
                itemsInTran[j] =new Item(availableItemsArray[randomItemIndex].getName(), quantity,
                        availableItemsArray[randomItemIndex].getPrice());
                sum += itemsInTran[j].getPrice() * quantity;
            }
            Transaction tran = new Transaction(i, customerId, timestemp, itemsInTran, sum);
            listOfTransactions.add(tran);
        }
        return listOfTransactions;
    }
}
