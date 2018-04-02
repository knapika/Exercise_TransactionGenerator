package launchers;

import generators.TransactionGenerator;
import readers.CSVReader;
import readers.CmdParser;
import structures.Item;
import structures.Transaction;
import structures.TransactionConfiguration;
import writers.IWriter;
import writers.JSONWriter;
import writers.XMLWriter;
import writers.YAMLWriter;

import java.util.List;

public class MainClass {
    private static CmdParser cmdParser = new CmdParser();
    private static CSVReader csvReader = new CSVReader();


    public static void main(String[] args) throws Exception {
        TransactionConfiguration configuration = cmdParser.parse(args);
        Item[] items = csvReader.readItemsFromFile(configuration.getFileWithItem());
        TransactionGenerator generator = new TransactionGenerator(configuration, items);
        List<structures.Transaction> transactionList = generator.generateTransactions();
        IWriter writer = new YAMLWriter(configuration.getOurDir());
        writer.write(transactionList, null);
    }
}
