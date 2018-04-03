package generators;

import generators.TransactionGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import structures.Item;
import structures.Transaction;
import structures.TransactionConfiguration;
import writers.IWriter;

import java.util.List;
import java.util.Random;

public class TransactionGeneratorTest {
    @Mock
    private IWriter writer;

    @Mock
    private RandomsGenerator randomsGenerator;

    private Item[] items = new Item[]{new Item("a", 5, 1.2), new Item("b", 2, 12)};

    @Before
    public void setUp() {
        randomsGenerator = new RandomsGenerator();
    }
    @Test
    public void generateAndSave() {
        //given
        int[] customerIds = {1, 20};
        int[] itemsCount = {5, 15};
        int[] itemsQua = {1, 30};
        TransactionConfiguration tc = new TransactionConfiguration(customerIds, "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "items.csv", itemsCount, itemsQua, 1000, ".", writer);
        TransactionGenerator uut = new TransactionGenerator(tc, randomsGenerator, items);

        //when
        List<Transaction> transactionList = uut.generateTransactions();

        //then
        Assert.assertTrue(transactionList.size() == 1000);
    }
}
