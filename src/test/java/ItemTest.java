import org.junit.Assert;
import org.junit.Test;

public class ItemTest {
    @Test
    public void createWithAllParams() {
        //given
        Item uut = new Item("jabłko", 5, 1.5);

        //when

        //then
        Assert.assertEquals(uut.getName(), "jabłko");
        Assert.assertTrue(uut.getPrice() == 1.5);
        Assert.assertTrue(uut.getQuantity() == 5);
    }

    @Test
    public void createWithoutQuantity() {
        //given
        Item uut = new Item("jabłko", 1.5);

        //when

        //then
        Assert.assertEquals(uut.getName(), "jabłko");
        Assert.assertTrue(uut.getPrice() == 1.5);
        Assert.assertTrue(uut.getQuantity() == 0);
    }
}
