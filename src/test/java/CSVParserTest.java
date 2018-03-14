import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CSVParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void reading() {
        //given
        CSVReader uut = new CSVReader();
        String file = "items.csv";

        //when
        Item[] i  = uut.readItemsFromFile(file);

        //then
        Assert.assertEquals(i.length, 9);
    }

}
