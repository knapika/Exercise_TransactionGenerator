import java.io.IOException;
import java.util.List;

public class MainClass {
    private static CmdParser cmdParser = new CmdParser();
    private static CSVReader csvReader = new CSVReader();
 //   private static TransactionJSONFileWriter writer = new TransactionJSONFileWriter();

    public static void main(String[] args) throws Exception {
//        CmdParser cmdParser = new CmdParser();
        TransactionConfiguration configuration = cmdParser.parse(args);
        Item[] items = csvReader.readItemsFromFile(configuration.getFileWithItem());
        TransactionGenerator generator = new TransactionGenerator(configuration, items);
        List<Transaction> transactionList = generator.generateTransactions();
        TransactionJSONFileWriter writer = new TransactionJSONFileWriter(configuration.getOurDir());
        writer.write(transactionList, null);
    }
}
