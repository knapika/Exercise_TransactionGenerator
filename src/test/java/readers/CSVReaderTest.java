package readers;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import readers.CSVReader;
import structures.Item;

import java.io.IOException;

import static org.assertj.core.api.Fail.fail;

public class CSVReaderTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void reading() throws IOException {
        //given
        CSVReader uut = new CSVReader();
        String file = "items.csv";

        //when
        Item[] i  = uut.readItemsFromFile(file);

        //then
        Assert.assertEquals(i.length, 9);
    }

    @Test(expected = IOException.class)
    public void fileNotExists() throws IOException {
        // given
        CSVReader uut = new CSVReader();

        // when
        uut.readItemsFromFile("items2.csv");
    }
}
