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

    public void write(Transaction tran) {
        String path = dir + "/transaction" + tran.getId() + ".json";
        Gson gson = new Gson();
        try {
            String jsonInString = gson.toJson(tran);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(jsonInString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
