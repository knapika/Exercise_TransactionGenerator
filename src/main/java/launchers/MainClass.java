package launchers;

import generators.RandomsGenerator;
import generators.TransactionGenerator;
import readers.CSVReader;
import readers.CmdParser;
import readers.PropertiesReader;
import structures.InputValues;
import structures.Item;
import structures.TransactionConfiguration;
import writers.IWriter;

import java.util.List;


public class MainClass {
    private static CmdParser cmdParser = new CmdParser();
    private static PropertiesReader propertiesReader = new PropertiesReader();
    private static CSVReader csvReader = new CSVReader();
    private static RandomsGenerator  randomsGenerator = new RandomsGenerator();


  public static void main(String[] args) throws Exception {
        InputValues inputValues;
        if(args.length > 0) {
            inputValues = cmdParser.parse(args);
        } else {
            inputValues = propertiesReader.getInputValues();
        }
        TransactionConfiguration configuration = inputValues.validateInputAndGetTransactionConfiguration();
        Item[] items = csvReader.readItemsFromFile(configuration.getFileWithItem());
        TransactionGenerator generator = new TransactionGenerator(configuration, randomsGenerator, items);
        List<structures.Transaction> transactionList = generator.generateTransactions();

        if(configuration.isIfOutDir()) {
            IWriter writer = configuration.getWriter();
            writer.write(transactionList, null);
        }

    }
}
