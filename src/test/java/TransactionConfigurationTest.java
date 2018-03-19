import org.junit.Assert;
import org.junit.Test;

public class TransactionConfigurationTest {
    @Test
    public void getCustomerId() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        TransactionConfiguration uut = new TransactionConfiguration(customerIds, "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "items.csv", itemsCount, itemsQua, 1000, ".");

        //when

        //then
        Assert.assertEquals(uut.getRangeOfCustomerId(), customerIds);
    }

    @Test
    public void getDate() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        String date = "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100";
        TransactionConfiguration uut = new TransactionConfiguration(customerIds, date,"items.csv", itemsCount,
                itemsQua, 1000, ".");

        //when

        //then
        Assert.assertEquals(uut.getRangeOfDate(), date);
    }

    @Test
    public void getFile() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        String date = "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100";
        TransactionConfiguration uut = new TransactionConfiguration(customerIds, date,"items.csv", itemsCount,
                itemsQua, 1000, ".");

        //when

        //then
        Assert.assertEquals(uut.getFileWithItem(), "items.csv");
    }

    @Test
    public void getNumberOfItems() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        String date = "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100";
        TransactionConfiguration uut = new TransactionConfiguration(customerIds, date,"items.csv", itemsCount,
                itemsQua, 1000, ".");

        //when

        //then
        Assert.assertEquals(uut.getRangeOfnumberOfItems(), itemsCount);
    }

    @Test
    public void getRangeOfQuantities() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        String date = "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100";
        TransactionConfiguration uut = new TransactionConfiguration(customerIds, date,"items.csv", itemsCount,
                itemsQua, 1000, ".");

        //when

        //then
        Assert.assertEquals(uut.getRangeOfQuantities(), itemsQua);
    }

    @Test
    public void getNumberOfTrans() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        String date = "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100";
        TransactionConfiguration uut = new TransactionConfiguration(customerIds, date,"items.csv", itemsCount,
                itemsQua, 1000, ".");

        //when

        //then
        Assert.assertEquals(uut.getNumberOfTrans(), 1000);
    }

    @Test
    public void getOutDir() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        String date = "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100";
        TransactionConfiguration uut = new TransactionConfiguration(customerIds, date,"items.csv", itemsCount,
                itemsQua, 1000, ".");

        //when

        //then
        Assert.assertEquals(uut.getOurDir(), ".");
    }
}
