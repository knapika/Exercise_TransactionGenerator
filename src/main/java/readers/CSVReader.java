package readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private final Logger CSVReaderLogger = LogManager.getLogger(CSVReader.class);

    public Item[] readItemsFromFile(String path) throws IOException {
        String line = "";
        List<Item> itemsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"))) {
            CSVReaderLogger.info("Start reading from file " + path);
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] itemPara = line.split(",");
                Item item = new Item(itemPara[0], Double.valueOf(itemPara[1]));
                itemsList.add(item);
            }
            Item[] itemsArray = new Item[itemsList.size()];
            itemsList.toArray(itemsArray);
            CSVReaderLogger.info("Items have been read");
            return itemsArray;
        } catch (IOException e) {
            CSVReaderLogger.info("Reading items is not possible " + e.getClass());
            throw new IOException();
        }
    }
}
