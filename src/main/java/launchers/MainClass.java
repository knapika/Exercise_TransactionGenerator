package launchers;

import generators.RandomsGenerator;
import generators.TransactionGenerator;
import readers.CSVReader;
import readers.CmdParser;
import structures.InputValues;
import structures.Item;
import structures.TransactionConfiguration;
import writers.IWriter;

import java.util.List;

public class MainClass {
    private static CmdParser cmdParser = new CmdParser();
    private static CSVReader csvReader = new CSVReader();
    private static RandomsGenerator  randomsGenerator = new RandomsGenerator();

    public static void main(String[] args) throws Exception {
        InputValues inputValues = cmdParser.parse(args);
        TransactionConfiguration configuration = inputValues.validateInputAndGetTransactionConfiguration();
        Item[] items = csvReader.readItemsFromFile(configuration.getFileWithItem());
        TransactionGenerator generator = new TransactionGenerator(configuration, randomsGenerator, items);
        List<structures.Transaction> transactionList = generator.generateTransactions();
        IWriter writer = configuration.getWriter();
        writer.write(transactionList, null);
    }
}
