import org.junit.Assert;
import org.junit.Test;

public class TransactionConfigurationTest {
    @Test
    public void getInfos() {
        //given
        TransactionConfiguration uut = new TransactionConfiguration("1:20", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "items.csv", "5:15", "1:30", "1000", "./output");

        //when

        //then
        Assert.assertEquals(uut.getRangeOfCustomerId(), "1:20");
        Assert.assertEquals(uut.getRangeOfDate(), "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100");
        Assert.assertEquals(uut.getFileWithItem(), "items.csv");
        Assert.assertEquals(uut.getRangeOfQuantities(), "1:30");
        Assert.assertEquals(uut.getRangeOfnumberOfItems(), "5:15");
        Assert.assertEquals(uut.getNumberOfTrans(), "1000");
        Assert.assertEquals(uut.getOurDir(), "./output");
    }
}
