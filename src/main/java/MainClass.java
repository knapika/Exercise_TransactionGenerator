public class MainClass {
    public static void main(String[] args) {
        CmdParser cmdParser = new CmdParser();
        TransactionConfiguration configuration = cmdParser.parse(args);
        TransactionGenerator generator = new TransactionGenerator(configuration);
        generator.readItemsFromCSVFile();
        generator.generateAndSaveTransactions();
    }
}
