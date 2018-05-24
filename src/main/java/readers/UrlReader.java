package readers;

import org.w3c.dom.Document;
import structures.Item;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class UrlReader {

    public Item[] getItemsList(String pathToData) {
        String itemsString = getItemsString(pathToData);
        String[] arrayWithItemsString = transformStringToArrayItems(itemsString);
        Item[] itemsArray = createItemsFromStringsArray(arrayWithItemsString);
        return itemsArray;
    }

    private Item[] createItemsFromStringsArray(String[] arrayWithItemsString) {
        Item[] items = new Item[arrayWithItemsString.length];
        for(int i = 0; i < items.length; i++) {
            items[i] = createItem(arrayWithItemsString[i]);
        }
        return items;
    }

    private Item createItem(String s) {
        s = s.replace("name", "");
        s = s.replace("price", "");
        s = s.replace(":", "");
        s = s.replace("\"","");
        String[] itemParam = s.split(",");
        return new Item(itemParam[0], Double.valueOf(itemParam[1]));
    }

    private String[] transformStringToArrayItems(String itemsString) {
        itemsString = itemsString.replace("[","");
        itemsString = itemsString.replace("]","");
        String[] array = itemsString.split("}");
        for(int i = 0; i < array.length; i++) {
            array[i] = processOneCell(array[i]);
        }
        return array;
    }

    private String processOneCell(String s) {
        s = s.replace("[{","");
        s = s.replace(",{", "");
        return s;
    }

    private String getItemsString(String pathToData) {
        URL url = null;
        String output = "";
        try {
            url = new URL(pathToData);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            output = br.readLine();
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
