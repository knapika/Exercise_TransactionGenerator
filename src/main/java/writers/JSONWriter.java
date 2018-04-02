package writers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONWriter implements IWriter {
    private final Logger JSONWriterLogger = LogManager.getLogger(JSONWriter.class);
    private final String dir;

    public JSONWriter(String dir) {
        this.dir = dir;
    }

    public boolean write(List<Transaction> transactionList, BufferedWriter writerBuff) {
        String path ="";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter writer = null;
        try {
            JSONWriterLogger.info("Start writing JSONs to file");
            for(Transaction tran : transactionList){
                path = dir + "/transaction" + tran.getId() + ".json";
                String jsonInString = gson.toJson(tran);
                if (writerBuff == null) {
                    writer = new BufferedWriter(new FileWriter(path));
                } else {
                    writer = writerBuff;
                }
                JSONWriterLogger.debug("Start writing transaction " + tran.getId());
                writer.write(jsonInString);
                writer.close();
                JSONWriterLogger.debug("Transaction " + tran.getId() + " successfully written");
            }
            JSONWriterLogger.info("JSONs have been saved to the file");
            return true;
        } catch (IOException e) {
            JSONWriterLogger.error("An error occurred during saving to the file");
            e.printStackTrace();
            return false;
        }
    }
}
