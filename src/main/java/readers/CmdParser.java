package readers;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.InputValues;
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
        options.addOption("broker",  true, "Broker's url");
        options.addOption("queue",  true, "Queue name");
        options.addOption("topic",  true, "topic");
    }

    public InputValues parse(String[] args) throws Exception {
        cmdLogger.info("Start reading from CMD");
        String rangeOfCustomerId = "";
        String rangeOfDate ="";
        String fileWithItem = "";
        String rangeOfnumberOfItems =  "";
        String rangeOfQuantities = "";
        String numberOfTrans = "";
        String outDir = "";
        String format = "";
        String broker = "";
        String topic = "";
        String queue = "";
        boolean ifOutDir = false;
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("customerIds")) {
                rangeOfCustomerId = cmd.getOptionValue("customerIds");
                cmdLogger.debug("Get customerIds");
            }
            if (cmd.hasOption("dateRange")) {
                rangeOfDate = cmd.getOptionValue("dateRange");
                cmdLogger.debug("Get dateRange");
            }
            if (cmd.hasOption("itemsFile")) {
                fileWithItem = cmd.getOptionValue("itemsFile");
                cmdLogger.debug("Get itemsFile");
            }
            if (cmd.hasOption("itemsCount")) {
                rangeOfnumberOfItems = cmd.getOptionValue("itemsCount");
                cmdLogger.debug("Get itemsCount");
            }
            if (cmd.hasOption("itemsQuantity")) {
                rangeOfQuantities = cmd.getOptionValue("itemsQuantity");
                cmdLogger.debug("Get itemsQuantity");
            }
            if (cmd.hasOption("eventsCount")) {
                numberOfTrans = cmd.getOptionValue("eventsCount");
                cmdLogger.debug("Get eventsCount");
            }
            if (cmd.hasOption("outDir")) {
                outDir = cmd.getOptionValue("outDir");
                cmdLogger.debug("Get outDir");
                ifOutDir = true;
            }
            if(cmd.hasOption("format")){
                format = cmd.getOptionValue("format");
                cmdLogger.debug("Get format type");
            }
            if(cmd.hasOption("broker")){
                broker = cmd.getOptionValue("broker");
                cmdLogger.debug("Get broker url");
            }
            if(cmd.hasOption("queue")){
                queue = cmd.getOptionValue("queue");
                cmdLogger.debug("Get queue name");
            }
            if(cmd.hasOption("topic")){
                topic = cmd.getOptionValue("topic");
                cmdLogger.debug("Get topic");
            }
        } catch (ParseException e) {
            cmdLogger.error("ParseException !!!");
        }
        cmdLogger.info("Create new structures.InputValues");
        InputValues inputValues = new InputValues(rangeOfCustomerId, rangeOfDate,
                fileWithItem, rangeOfnumberOfItems, rangeOfQuantities, numberOfTrans, outDir, format,
                broker, queue, topic, ifOutDir);
        return inputValues;
    }
}
