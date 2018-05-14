package readers;

import structures.Range;
import structures.TransactionConfiguration;
import writers.IWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public TransactionConfiguration getTransactionGenerationFromProperties() {
        Range<Integer> rangeOfCustomerId = new Range<>(1, 20);
        String rangeOfDate ="";
        String fileWithItem = "";
        Range<Integer> rangeOfnumberOfItems =  new Range<>(1, 5);
        Range<Integer> rangeOfQuantities = new Range<>(1, 5);
        int numberOfTrans = 100;
        String outDir = ".";
        String format = "JSON";
        IWriter writer = null;

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("generator.properties");

            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
