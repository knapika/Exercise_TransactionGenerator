import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class TransactionJSONFileWriter {
    private final String dir;

    public TransactionJSONFileWriter(String dir) {
        this.dir = dir;
    }

    public void write(Transaction tran, BufferedWriter writerBuff) {
        String path = dir + "/transaction" + tran.getId() + ".json";
        Gson gson = new Gson();
        BufferedWriter writer = null;
        try {
            String jsonInString = gson.toJson(tran);
            if(writerBuff == null){
                writer = new BufferedWriter(new FileWriter(path));
            } else {
                writer = writerBuff;
            }
            writer.write(jsonInString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
