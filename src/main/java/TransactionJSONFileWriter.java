import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class TransactionJSONFileWriter {
    private final Logger TransactionJSONFileWriterLogger = LogManager.getLogger(TransactionJSONFileWriter.class);
    private final String dir;

    public TransactionJSONFileWriter(String dir) {
        this.dir = dir;
    }

    public boolean write(List<Transaction> transactionList, BufferedWriter writerBuff) {
        String path ="";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter writer = null;
        try {
            TransactionJSONFileWriterLogger.info("Start writing JSONs to file");
            for(Transaction tran : transactionList){
                path = dir + "/transaction" + tran.getId() + ".json";
                String jsonInString = gson.toJson(tran);
                if (writerBuff == null) {
                    writer = new BufferedWriter(new FileWriter(path));
                } else {
                    writer = writerBuff;
                }
                writer.write(jsonInString);
                writer.close();
            }
            TransactionJSONFileWriterLogger.info("JSONs have been saved to the file");
            return true;
        } catch (IOException e) {
            TransactionJSONFileWriterLogger.error("An error occurred during saving to the file");
            e.printStackTrace();
            return false;
        }
    }
}
