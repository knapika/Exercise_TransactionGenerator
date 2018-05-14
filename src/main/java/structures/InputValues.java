package structures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import readers.CmdParser;
import writers.IWriter;
import writers.JSONWriter;
import writers.XMLWriter;
import writers.YAMLWriter;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InputValues {
    private final Logger InputValuesLogger = LogManager.getLogger(InputValues.class);
    private final String rangeOfCustomerId;
    private final String rangeOfDate;
    private final String fileWithItem;
    private final String rangeOfnumberOfItems;
    private final String rangeOfQuantities;
    private final String numberOfTrans;
    private final String ourDir;
    private final String format;

    public InputValues(String rangeOfCustomerId, String rangeOfDate, String fileWithItem, String rangeOfnumberOfItems, 
                       String rangeOfQuantities, String numberOfTrans, String ourDir, String format) {
        this.rangeOfCustomerId = rangeOfCustomerId;
        this.rangeOfDate = rangeOfDate;
        this.fileWithItem = fileWithItem;
        this.rangeOfnumberOfItems = rangeOfnumberOfItems;
        this.rangeOfQuantities = rangeOfQuantities;
        this.numberOfTrans = numberOfTrans;
        this.ourDir = ourDir;
        this.format = format;
    }

    public TransactionConfiguration validateInputAndGetTransactionConfiguration() {
        Range<Integer> rangeForCustomerIDs = validateRangeAndGetRangeObject(rangeOfCustomerId, "CustomerIds",
                new Range<>(1,20));
        Range<Integer> rangeForItems = validateRangeAndGetRangeObject(rangeOfnumberOfItems, "ItemsRange",
                new Range<Integer>(1, 5));
        Range<Integer> rangeForQuantities = validateRangeAndGetRangeObject(rangeOfQuantities, "QuantitiesRange",
                new Range<>(1, 5));
        String dateRange = validateDate(rangeOfDate);
        int transCount = validateNumberOfTrans(numberOfTrans);
        String outDir = validateOutDir(ourDir);
        IWriter writer = getWriter(format, outDir);

        return new TransactionConfiguration(rangeForCustomerIDs, dateRange, fileWithItem, rangeForItems, rangeForQuantities,
                transCount, outDir, writer);
    }

    private IWriter getWriter(String format, String dir) {
        switch (format.toLowerCase()) {
            case "json":
                InputValuesLogger.debug("Set JSONWriter");
                return new JSONWriter(dir);
            case "xml":
                InputValuesLogger.debug("Set XMLWriter");
                return new XMLWriter(dir);
            case "yaml":
                InputValuesLogger.debug("Set YAMLWriter");
                return new YAMLWriter(dir);
            default:
                InputValuesLogger.error("Set null writer!");
                return new JSONWriter(dir);
        }
    }

    private String validateOutDir(String outDir) {
        String result = outDir;
        File dir = new File(outDir);
        if(!dir.exists()) {
            InputValuesLogger.error("outDir not exists!");
            if(dir.mkdir()) {
                InputValuesLogger.error("Create dir!");
            } else {
                InputValuesLogger.error("Cannot create dir used default!");
                result = ".";
            }
        }
        return result;
    }

    private int validateNumberOfTrans(String numberOfTrans) {
        int result;
        if(numberOfTrans.length() > 0) {
            result = Integer.valueOf(numberOfTrans);
        } else {
            result = 100;
            InputValuesLogger.debug("Used default amount for trans number");
        }
        return result;
    }


    private Range<Integer> validateRangeAndGetRangeObject(String range, String paramName, Range<Integer> defaultRange) {
        Range<Integer> result = defaultRange;
        if(range.length() > 0) {
            String[] tempString = range.split(":");
            result = new Range<>(Integer.valueOf(tempString[0]), Integer.valueOf(tempString[1]));
            if(result.getFrom() <= result.getTo()) {
                InputValuesLogger.debug("Correct range for " + paramName);
            } else {
                InputValuesLogger.debug("Wrong range for " + paramName + " used default!");
            }
        }
        return result;
    }

    private String validateDate(String range) {
        String dateResult = "";
        if(range.length() > 0) {
            dateResult = range;
        } else {
            dateResult = setDefaultDate();
        }
        return dateResult;
    }

    private String setDefaultDate() {
        InputValuesLogger.info("Set default date");
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String todayStart = LocalDate.now().toString() + "T" + LocalTime.of(0,0,0,0)
                .format(formatter) + "-0100";
        String todayEnd = LocalDate.now().toString() + "T" + LocalTime.of(23,59,59,999999999)
                .format(formatter) + "-0100";
        return todayStart + ":" + todayEnd.toString();
    }
}
