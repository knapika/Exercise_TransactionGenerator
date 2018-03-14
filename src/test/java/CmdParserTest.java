import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Fail.fail;

public class CmdParserTest {
    @Test
    public void testAllParameters() {
        CmdParser uut = new CmdParser();
        String[] args = {"-customerIds", "1:20", "-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv", "-itemsCount", "5:15", "-itemsQuantity", "1:30", "-eventsCount", "1000", "-outDir",
                "./output"};
        TransactionConfiguration tc = new TransactionConfiguration("1:20", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "items.csv", "5:15", "1:30", "1000", "./output");


        Assert.assertTrue(uut.parse(args).equals(tc));
    }

    @Test
    public void testDefualts () {
        CmdParser uut = new CmdParser();
        String[] args = {"-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv", "-outDir", "./output"};

        TransactionConfiguration tc = new TransactionConfiguration("1:20", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "items.csv", "1:5", "1:5", "100", "./output");
        Assert.assertTrue(uut.parse(args).equals(tc));
    }
}
