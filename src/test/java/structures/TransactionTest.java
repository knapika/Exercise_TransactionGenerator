package structures;

import org.junit.Assert;
import org.junit.Test;
import structures.Item;
import structures.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionTest {
    private LocalDateTime now = LocalDateTime.now();

    @Test
    public void getId() {
        //given
        Item[] i = {new Item("jabłko", 5, 1.5)};
        Transaction uut = new Transaction(2,5, now, i, 555.123123);
        //when

        //then
        Assert.assertTrue(uut.getId() == 2);

    }

    @Test
    public void getCustoreId() {
        //given
        Item[] i = {new Item("jabłko", 5, 1.5)};
        Transaction uut = new Transaction(2,5, now, i, 555.123123);
        //when

        //then
        Assert.assertTrue(uut.getCustomerId() == 5);
    }

    @Test
    public void getSum() {
        //given
        Item[] i = {new Item("jabłko", 5, 1.5)};
        Transaction uut = new Transaction(2,5, now, i, 555.123123);
        //when

        //then
        Assert.assertTrue(uut.getSum().equals(new BigDecimal(555.123123).setScale(2, BigDecimal.ROUND_HALF_UP)));
    }

    @Test
    public void getDate() {
        //given
        LocalDateTime now = LocalDateTime.now();
        Item[] i = {new Item("jabłko", 5, 1.5)};
        Transaction uut = new Transaction(2,5, now, i, 555.123123);
        //when

        //then
        Assert.assertTrue(uut.getDate().equals(uut.getDate()));
    }
    @Test
    public void getInfos() {
        //given
        Item[] i = {new Item("jabłko", 5, 1.5)};
        Transaction uut = new Transaction(2,5, now, i, 555.123123);
        //when

        //then
        Assert.assertTrue(uut.getListOfItems().equals(i));
    }
}
