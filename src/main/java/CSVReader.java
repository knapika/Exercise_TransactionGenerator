import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public Item[] readItemsFromFile(String path) {
        String line = "";
        List<Item> itemsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] itemPara = line.split(",");
                Item item = new Item(itemPara[0], Double.valueOf(itemPara[1]));
                itemsList.add(item);
            }
            Item[] itemsArray = new Item[itemsList.size()];
            itemsList.toArray(itemsArray);
            return itemsArray;
        } catch (AccessDeniedException e1) {
            e1.printStackTrace();;
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
