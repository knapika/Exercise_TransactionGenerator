package generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.Item;
import structures.Transaction;
import structures.TransactionConfiguration;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TransactionGenerator {
    private final Logger TransactionGeneratorLogger = LogManager.getLogger(TransactionGenerator.class);
    private final TransactionConfiguration transactionConfiguration;
    private final RandomsGenerator randomsGenerator;
    private final Item[] availableItemsArray;

    public TransactionGenerator(TransactionConfiguration transactionConfiguration, RandomsGenerator randomsGenerator, Item[] availableItemsArray) {
        this.transactionConfiguration = transactionConfiguration;
        this.randomsGenerator = randomsGenerator;
        this.availableItemsArray = availableItemsArray;
    }

    public List<Transaction> generateTransactions () {
        List<Transaction> listOfTransactions = new LinkedList<>();
        int numberOfTrans = Integer.valueOf(transactionConfiguration.getNumberOfTrans());

        for(int i = 0; i < numberOfTrans; i++) {
            TransactionGeneratorLogger.info("Get random customerId");
            int customerId = randomsGenerator.getInteger(transactionConfiguration.getRangeOfCustomerId().getFrom(),
                    transactionConfiguration.getRangeOfCustomerId().getTo());
            TransactionGeneratorLogger.info("Get random itemsCount");
            int itemsCount = randomsGenerator.getInteger(transactionConfiguration.getRangeOfnumberOfItems().getFrom(),
                    transactionConfiguration.getRangeOfnumberOfItems().getTo());
            LocalDateTime timestemp = randomsGenerator.getTimestamp(transactionConfiguration.getRangeOfDate());
            Item[] itemsInTran = new Item[itemsCount];
            double sum = 0;
            for(int j = 0; j < itemsCount; j++){
                TransactionGeneratorLogger.info("Get random structures.Item");
                int randomItemIndex = randomsGenerator.getInteger(0, availableItemsArray.length);
                TransactionGeneratorLogger.info("Get random quantity");
                int quantity = randomsGenerator.getInteger(transactionConfiguration.getRangeOfQuantities().getFrom(),
                        transactionConfiguration.getRangeOfQuantities().getTo());
                TransactionGeneratorLogger.info("Create new structures.Item in transaction");
                itemsInTran[j] =new Item(availableItemsArray[randomItemIndex].getName(), quantity,
                        availableItemsArray[randomItemIndex].getPrice());
                sum += itemsInTran[j].getPrice() * quantity;
            }
            TransactionGeneratorLogger.info("Create new transaction and add it to list");
            Transaction tran = new Transaction(i, customerId, timestemp, itemsInTran, sum);
            listOfTransactions.add(tran);
        }
        return listOfTransactions;
    }
}
