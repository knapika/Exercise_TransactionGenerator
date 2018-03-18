import java.time.LocalDateTime;

public class TransactionGenerator {
    private TransactionConfiguration transactionConfiguration;
    private final CSVReader reader;
    private final RandomsGenerator randomsGenerator;
    private Item[] availableItemsArray;
    private final TransactionJSONFileWriter JsonWriter;

    public TransactionGenerator(TransactionConfiguration transactionConfiguration) {
        this.transactionConfiguration = transactionConfiguration;
        this.reader = new CSVReader();
        this.randomsGenerator = new RandomsGenerator();
        this.JsonWriter = new TransactionJSONFileWriter(transactionConfiguration.getOurDir());
    }

    public void readItemsFromCSVFile () {
        availableItemsArray = reader.readItemsFromFile(transactionConfiguration.getFileWithItem());
    }

    public Boolean generateAndSaveTransactions () {
        int numberOfTrans = Integer.valueOf(transactionConfiguration.getNumberOfTrans());

        for(int i = 0; i < numberOfTrans; i++) {
            int customerId = randomsGenerator.getIntegerFromString(transactionConfiguration.getRangeOfCustomerId());
            int itemsCount = randomsGenerator.getIntegerFromString(transactionConfiguration.getRangeOfnumberOfItems());
            LocalDateTime timestemp = randomsGenerator.getTimestamp(transactionConfiguration.getRangeOfDate());
            Item[] itemsInTran = new Item[itemsCount];
            double sum = 0;
            for(int j = 0; j < itemsCount; j++){
                int randomItemIndex = randomsGenerator.getInteger(0, availableItemsArray.length);
                int quantity = randomsGenerator.getIntegerFromString(transactionConfiguration.getRangeOfQuantities());
                itemsInTran[j] =new Item(availableItemsArray[randomItemIndex].getName(), quantity,
                        availableItemsArray[randomItemIndex].getPrice());
                sum += itemsInTran[j].getPrice() * quantity;
            }
            Transaction tran = new Transaction(i, customerId, timestemp, itemsInTran, sum);

            JsonWriter.write(tran);
        }
        return true;
    }
}
