import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Fail.fail;

public class CmdParserTest {
    @Test
    public void correctRange(){
        //given
        CmdParser uut = new CmdParser();
        Method method = null;
        int[] result = new int[5];
        try {
            //when
            method = CmdParser.class.getDeclaredMethod("checkRangeCorrectness", String.class);
            method.setAccessible(true);
            result = (int[]) method.invoke(uut,"1:20");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //then
        Assert.assertTrue(result[0] == 1 && result[1] == 20);
    }

    @Test
    public void incorrectRange(){
        //given
        CmdParser uut = new CmdParser();
        Method method = null;
        int[] result = new int[5];
        try {
            //when
            method = CmdParser.class.getDeclaredMethod("checkRangeCorrectness", String.class);
            method.setAccessible(true);
            result = (int[]) method.invoke(uut,"21:20");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //then
        Assert.assertTrue(result == null);
    }
    
    @Test
    public void defaultDate() {
        //given
        CmdParser uut = new CmdParser();
        Method method = null;
        String result = null;
        String expectedDate = LocalDate.now().toString() + "T00:00:00.000-0100:" + LocalDate.now().toString() +
                "T23:59:59.999-0100";
        try {
            //when
            method = CmdParser.class.getDeclaredMethod("setDefaultDate", null);
            method.setAccessible(true);
            result = (String) method.invoke(uut,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //then
        Assert.assertTrue(result.equals(expectedDate));
        
    }

    @Test
    public void incorrectCustomerIds() {
        //given
        CmdParser uut = new CmdParser();
        String[] args = {"-customerIds", "21:20", "-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv", "-itemsCount", "5:15", "-itemsQuantity", "1:30", "-eventsCount", "1000", "-outDir",
                "./output"};
        try {
            //when
            uut.parse(args);
        } catch (Exception e) {
            //then
            Assert.assertEquals(e.getClass(), Exception.class);
            Assert.assertEquals(e.getMessage(), "Zły zakres dla parametru CustomerID");
        }
    }

    @Test
    public void incorrectItemsCounts() {
        //given
        CmdParser uut = new CmdParser();
        String[] args = {"-customerIds", "1:20", "-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv", "-itemsCount", "16:15", "-itemsQuantity", "1:30", "-eventsCount", "1000", "-outDir",
                "./output"};
        try {
            //when
            uut.parse(args);
        } catch (Exception e) {
            //then
            Assert.assertEquals(e.getClass(), Exception.class);
            Assert.assertEquals(e.getMessage(), "Zły zakres dla parametru itemsCount");
        }
    }

    @Test
    public void incorrectItemsQuantity() {
        //given
        CmdParser uut = new CmdParser();
        String[] args = {"-customerIds", "1:20", "-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv", "-itemsCount", "5:15", "-itemsQuantity", "31:30", "-eventsCount", "1000", "-outDir",
                "./output"};
        try {
            //when
            uut.parse(args);
        } catch (Exception e) {
            //then
            Assert.assertEquals(e.getClass(), Exception.class);
            Assert.assertEquals(e.getMessage(), "Zły zakres dla parametru itemsQuantity");
        }
    }

    @Test
    public void outDitNotExists() {
        //given
        CmdParser uut = new CmdParser();
        String[] args = {"-customerIds", "1:20", "-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv", "-itemsCount", "5:15", "-itemsQuantity", "20:30", "-eventsCount", "1000", "-outDir",
                "./output/xyz"};
        try {
            //when
            uut.parse(args);
        } catch (Exception e) {
            //then
            Assert.assertEquals(e.getClass(), Exception.class);
            Assert.assertEquals(e.getMessage(), "Podany katalog nie istnieje!");
        }
    }

    @Test
    public void testAllParameters() throws Exception {
        //given
        CmdParser uut = new CmdParser();
        String[] args = {"-customerIds", "1:20", "-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv", "-itemsCount", "5:15", "-itemsQuantity", "1:30", "-eventsCount", "1000", "-outDir",
                "."};
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        TransactionConfiguration tc = new TransactionConfiguration(customerIds, "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "items.csv", itemsCount, itemsQua, 1000, ".");
        //when

        //then
        Assert.assertTrue(uut.parse(args).equals(tc));
    }

    @Test
    public void testDefualts () throws Exception {
        //given
        CmdParser uut = new CmdParser();
        String[] args = {"-itemsFile", "items.csv"};
        String today = LocalDate.now().toString() + "T00:00:00.000-0100:" + LocalDate.now().toString() +
                "T23:59:59.999-0100";
        int[] customerIds = {1, 20};
        int[] itemsCount = {1, 5};
        int[] itemsQua = {1, 5};
        TransactionConfiguration tc = new TransactionConfiguration(customerIds, today,"items.csv",
                itemsCount, itemsQua, 100, ".");

        //when

        //then
        Assert.assertTrue(uut.parse(args).equals(tc));
    }
}
