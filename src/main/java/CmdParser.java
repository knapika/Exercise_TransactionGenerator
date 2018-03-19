import org.apache.commons.cli.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CmdParser {
    private Options options = new Options();

    public CmdParser() {
        options.addOption("customerIds", true, "Range for customerID");
        options.addOption("dateRange",  true, "Range for date");
        options.addOption("itemsFile", true, "Source for items");
        options.addOption("itemsCount",  true, "Range index in array of items");
        options.addOption("itemsQuantity", true, "Range for quantity");
        options.addOption("eventsCount",  true, "Number of transaction");
        options.addOption("outDir",  true, "Output destination");
    }

    public TransactionConfiguration parse(String[] args) throws Exception {
        int[] rangeOfCustomerId = {1, 20};
        String rangeOfDate = setDefaultDate();
        String fileWithItem = "";
        int[] rangeOfnumberOfItems = {1, 5};
        int[] rangeOfQuantities = {1, 5};
        int numberOfTrans = 100;
        String outDir = ".";
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("customerIds")) {
                rangeOfCustomerId = checkRangeCorrectness(cmd.getOptionValue("customerIds"));
                if(rangeOfCustomerId == null){
                    throw new Exception("Zły zakres dla parametru CustomerID");
                }
            }
            if (cmd.hasOption("dateRange")) {
                rangeOfDate = cmd.getOptionValue("dateRange");
            }
            if (cmd.hasOption("itemsFile")) {
                fileWithItem = cmd.getOptionValue("itemsFile");
            }
            if (cmd.hasOption("itemsCount")) {
                rangeOfnumberOfItems = checkRangeCorrectness(cmd.getOptionValue("itemsCount"));
                if(rangeOfnumberOfItems == null){
                    throw new Exception("Zły zakres dla parametru itemsCount");
                }
            }
            if (cmd.hasOption("itemsQuantity")) {
                rangeOfQuantities = checkRangeCorrectness(cmd.getOptionValue("itemsQuantity"));
                if(rangeOfQuantities == null) {
                    throw new Exception("Zły zakres dla parametru itemsQuantity");
                }
            }
            if (cmd.hasOption("eventsCount")) {
                numberOfTrans = Integer.valueOf(cmd.getOptionValue("eventsCount"));
            }
            if (cmd.hasOption("outDir")) {
                outDir = cmd.getOptionValue("outDir");
                File dir = new File(outDir);
                if(!dir.exists()) {
                    throw new Exception("Podany katalog nie istnieje!");
                }
            }
        } catch (ParseException e) {
        }
        TransactionConfiguration transactionConfiguration = new TransactionConfiguration(rangeOfCustomerId, rangeOfDate,
                fileWithItem, rangeOfnumberOfItems, rangeOfQuantities, numberOfTrans, outDir);
        return transactionConfiguration;
    }

    private String setDefaultDate() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String todayStart = LocalDate.now().toString() + "T" + LocalTime.of(0,0,0,0)
                .format(formatter) + "-0100";
        String todayEnd = LocalDate.now().toString() + "T" + LocalTime.of(23,59,59,999999999)
                .format(formatter) + "-0100";
        return todayStart + ":" + todayEnd.toString();
    }

    private int[] checkRangeCorrectness(String range) {
        String[] tempString = range.split(":");
        int[] rangeInt = {Integer.valueOf(tempString[0]), Integer.valueOf(tempString[1])};
        if(rangeInt[0] <= rangeInt[1]) return rangeInt;
        else return null;
    }
}
