import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class TransactionJSONFileWriter {
    private final String dir;

    public TransactionJSONFileWriter(String dir) {
        this.dir = dir;
    }

    public boolean write(List<Transaction> transactionList, BufferedWriter writerBuff) {
        String path ="";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter writer = null;
        try {
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
