package readers;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.Range;
import structures.TransactionConfiguration;
import writers.IWriter;
import writers.JSONWriter;
import writers.XMLWriter;
import writers.YAMLWriter;


import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CmdParser {
    private final Logger cmdLogger = LogManager.getLogger(CmdParser.class);
    private Options options = new Options();

    public CmdParser() {
        options.addOption("customerIds", true, "Range for customerID");
        options.addOption("dateRange",  true, "Range for date");
        options.addOption("itemsFile", true, "Source for items");
        options.addOption("itemsCount",  true, "Range index in array of items");
        options.addOption("itemsQuantity", true, "Range for quantity");
        options.addOption("eventsCount",  true, "Number of transaction");
        options.addOption("outDir",  true, "Output destination");
        options.addOption("format",  true, "Output format");
    }

    public TransactionConfiguration parse(String[] args) throws Exception {
        cmdLogger.info("Start reading from CMD");
        Range<Integer> rangeOfCustomerId = new Range<>(1, 20);
//        int[] rangeOfCustomerId = {1, 20};
        String rangeOfDate ="";
        String fileWithItem = "";
        Range<Integer> rangeOfnumberOfItems =  new Range<>(1, 5);
        Range<Integer> rangeOfQuantities = new Range<>(1, 5);
//        int[] rangeOfnumberOfItems = {1, 5};
//        int[] rangeOfQuantities = {1, 5};
        int numberOfTrans = 100;
        String outDir = ".";
        String format = "JSON";
        IWriter writer = null;
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("customerIds")) {
                rangeOfCustomerId = checkRangeCorrectness(cmd.getOptionValue("customerIds"), "customerIds");
                cmdLogger.debug("Set customerIds");
                if(rangeOfCustomerId == null){
                    cmdLogger.warn("Wrong range for customerIds");
                    throw new Exception("Zły zakres dla parametru CustomerID");
                }
            }
            if (cmd.hasOption("dateRange")) {
                rangeOfDate = cmd.getOptionValue("dateRange");
                cmdLogger.debug("Set dateRange");
            } else {
                rangeOfDate = setDefaultDate();
                cmdLogger.debug("Set defaultDate");
            }
            if (cmd.hasOption("itemsFile")) {
                fileWithItem = cmd.getOptionValue("itemsFile");
                cmdLogger.debug("Set itemsFile");
            }
            if (cmd.hasOption("itemsCount")) {
                rangeOfnumberOfItems = checkRangeCorrectness(cmd.getOptionValue("itemsCount"), "itemsCount");
                cmdLogger.debug("Set itemsCount");
                if(rangeOfnumberOfItems == null){
                    cmdLogger.warn("Wrong range for itemsCount");
                    throw new Exception("Zły zakres dla parametru itemsCount");
                }
            }
            if (cmd.hasOption("itemsQuantity")) {
                rangeOfQuantities = checkRangeCorrectness(cmd.getOptionValue("itemsQuantity"), "itemsQuantity");
                cmdLogger.debug("Set itemsQuantity");
                if(rangeOfQuantities == null) {
                    cmdLogger.warn("Wrong range for itemsQuantity");
                    throw new Exception("Zły zakres dla parametru itemsQuantity");
                }
            }
            if (cmd.hasOption("eventsCount")) {
                numberOfTrans = Integer.valueOf(cmd.getOptionValue("eventsCount"));
                cmdLogger.debug("Set eventsCount");
            }
            if (cmd.hasOption("outDir")) {
                outDir = cmd.getOptionValue("outDir");
                cmdLogger.debug("Set outDir");
                File dir = new File(outDir);
                if(!dir.exists()) {
                    cmdLogger.error("outDir not exists!");
                    throw new Exception("Podany katalog nie istnieje!");
                }
            }
            if(cmd.hasOption("format")){
                format = cmd.getOptionValue("format");
                writer = setWriter(format, outDir);
                if(writer == null) {
                    throw new Exception("Wybrano niedozwolony format zapisu!");
                }
            }
        } catch (ParseException e) {
            cmdLogger.error("ParseException !!!");
        }
        cmdLogger.info("Create new structures.TransactionConfiguration");
        TransactionConfiguration transactionConfiguration = new TransactionConfiguration(rangeOfCustomerId, rangeOfDate,
                fileWithItem, rangeOfnumberOfItems, rangeOfQuantities, numberOfTrans, outDir, writer);
        return transactionConfiguration;
    }

    private String setDefaultDate() {
        cmdLogger.info("Set default date");
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String todayStart = LocalDate.now().toString() + "T" + LocalTime.of(0,0,0,0)
                .format(formatter) + "-0100";
        String todayEnd = LocalDate.now().toString() + "T" + LocalTime.of(23,59,59,999999999)
                .format(formatter) + "-0100";
        return todayStart + ":" + todayEnd.toString();
    }

    private Range<Integer> checkRangeCorrectness(String range, String paramName) {
        String[] tempString = range.split(":");
        int[] rangeInt = {Integer.valueOf(tempString[0]), Integer.valueOf(tempString[1])};
        if(rangeInt[0] <= rangeInt[1]){
            cmdLogger.info("Correct range for " + paramName);
            return new Range<>(rangeInt[0], rangeInt[1]);
        }
        else{
            cmdLogger.error("Wrong range for " + paramName);
            return null;
        }
    }

    private IWriter setWriter(String format, String dir) {
        switch (format.toLowerCase()) {
            case "json":
                cmdLogger.debug("Set JSONWriter");
                return new JSONWriter(dir);
            case "xml":
                cmdLogger.debug("Set XMLWriter");
                return new XMLWriter(dir);
            case "yaml":
                cmdLogger.debug("Set YAMLWriter");
                return new YAMLWriter(dir);
            default:
                cmdLogger.error("Set null writer!");
                return null;
        }
    }
}
