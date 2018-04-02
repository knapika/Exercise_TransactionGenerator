import org.junit.Assert;
import org.junit.Test;
import structures.Item;

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

    @Test
    public void testToString() {
        //given
        Item uut = new Item("a", 4, 1.5);
        String exp = "{name: a,quantity:4,price:1.5}";
        //when
        String result = uut.toString();
        //then
        Assert.assertEquals(exp, result);

    }
}
