import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class TransactionGenerator {
    private TransactionConfiguration transactionConfiguration;
    private final CSVReader reader;
    private final RandomsGenerator randomsGenerator;
    private Item[] availableItemsArray;
    private final TransactionJSONFileWriter JsonWriter;
    private final BufferedWriter writer;

    public TransactionGenerator(TransactionConfiguration transactionConfiguration) {
        this.transactionConfiguration = transactionConfiguration;
        this.reader = new CSVReader();
        this.randomsGenerator = new RandomsGenerator();
        this.JsonWriter = new TransactionJSONFileWriter(transactionConfiguration.getOurDir());
        this.writer = null;
    }

    public void readItemsFromCSVFile () throws IOException {
        availableItemsArray = reader.readItemsFromFile(transactionConfiguration.getFileWithItem());
    }

    public Boolean generateAndSaveTransactions () {
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

            JsonWriter.write(tran, writer);
        }
        return true;
    }
}
