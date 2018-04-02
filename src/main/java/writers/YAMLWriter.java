package writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.Transaction;

import java.io.*;
import java.util.List;
import java.util.Map;

public class YAMLWriter implements IWriter {
    private final Logger YAMLWriterLogger = LogManager.getLogger(YAMLWriter.class);
    private final String dir;

    public YAMLWriter(String dir) {
        this.dir = dir;
    }

    @Override
    public boolean write(List<Transaction> transactionList, BufferedWriter writerBuff) {
        String path = "";
        YAMLWriterLogger.info("Start writing XMLs to files");
        try {
            for (Transaction tran : transactionList) {
                path = dir + "/transaction" + tran.getId() + ".yml";
                YAMLWriterLogger.debug("Start writing transaction " + tran.getId());
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                mapper.writeValue(new File(path), tran);
                YAMLWriterLogger.debug("Transaction " + tran.getId() + " successfully written");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
