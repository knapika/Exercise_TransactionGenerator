import org.apache.commons.cli.*;

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

    public TransactionConfiguration parse(String[] args) {
        String rangeOfCustomerId = "1:20";
        String rangeOfDate = setDefaultDate();
        String fileWithItem = "";
        String rangeOfnumberOfItems = "1:5";
        String rangeOfQuantities = "1:5";
        String numberOfTrans = "100";
        String outDir = ".";
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("customerIds")) {
                rangeOfCustomerId = cmd.getOptionValue("customerIds");
            }
            if (cmd.hasOption("dateRange")) {
                rangeOfDate = cmd.getOptionValue("dateRange");
            }
            if (cmd.hasOption("itemsFile")) {
                fileWithItem = cmd.getOptionValue("itemsFile");
            }
            if (cmd.hasOption("itemsCount")) {
                rangeOfnumberOfItems = cmd.getOptionValue("itemsCount");
            }
            if (cmd.hasOption("itemsQuantity")) {
                rangeOfQuantities = cmd.getOptionValue("itemsQuantity");
            }
            if (cmd.hasOption("eventsCount")) {
                numberOfTrans = cmd.getOptionValue("eventsCount");
            }
            if (cmd.hasOption("outDir")) {
                outDir = cmd.getOptionValue("outDir");
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

}
