import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionTest {
    @Test
    public void getInfos() {
        //given
        LocalDateTime now = LocalDateTime.now();
        Item[] i = {new Item("jabłko", 5, 1.5)};
        Transaction uut = new Transaction(2,5, now, i, 555.123123);
        //when

        //then
        Assert.assertTrue(uut.getId() == 2);
        Assert.assertTrue(uut.getCustomerId() == 5);
        Assert.assertTrue(uut.getSum().equals(new BigDecimal(555.123123).setScale(2, BigDecimal.ROUND_HALF_UP)));
        Assert.assertTrue(uut.getDate().equals(uut.getDate()));
        Assert.assertTrue(uut.getListOfItems().equals(i));
    }
}
