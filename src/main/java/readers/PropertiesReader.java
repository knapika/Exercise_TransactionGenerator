package readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.InputValues;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private final Logger PropertiesReaderLogger = LogManager.getLogger(PropertiesReader.class);
    public InputValues getInputValues() {
        String rangeOfCustomerId = "";
        String rangeOfDate ="";
        String fileWithItem = "";
        String rangeOfnumberOfItems = "";
        String rangeOfQuantities = "";
        String numberOfTrans = "";
        String outDir = "";
        String format = "";

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("/storage/generator.properties");
            prop.load(input);

            if(prop.getProperty("itemsFile") != null) {
                PropertiesReaderLogger.debug("Get itemsFile");
                fileWithItem = prop.getProperty("itemsFile");
            }
            if(prop.getProperty("customerIds") != null) {
                PropertiesReaderLogger.debug("Get customerIds");
                rangeOfCustomerId = prop.getProperty("customerIds");
            }
            if(prop.getProperty("dateRange") != null) {
                PropertiesReaderLogger.debug("Get dateRange");
                rangeOfDate = prop.getProperty("dateRange");
                rangeOfDate = rangeOfDate.replace("\"", "");
            }
            if(prop.getProperty("itemsCount") != null) {
                PropertiesReaderLogger.debug("Get itemsCount");
                rangeOfnumberOfItems = prop.getProperty("itemsCount");
            }
            if(prop.getProperty("itemsQuantity") != null) {
                PropertiesReaderLogger.debug("Get itemsQuantity");
                rangeOfQuantities = prop.getProperty("itemsQuantity");
            }
            if(prop.getProperty("outDir") != null) {
                PropertiesReaderLogger.debug("Get outDir");
                outDir = prop.getProperty("outDir");
            }
            if(prop.getProperty("eventsCount") != null) {
                PropertiesReaderLogger.debug("Get eventsCount");
                numberOfTrans = prop.getProperty("eventsCount");
            }
            if(prop.getProperty("format") != null) {
                PropertiesReaderLogger.debug("Get format");
                format = prop.getProperty("format");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new InputValues(rangeOfCustomerId, rangeOfDate, fileWithItem, rangeOfnumberOfItems, rangeOfQuantities,
                numberOfTrans, outDir, format, "", "", "", true);
    }
}
