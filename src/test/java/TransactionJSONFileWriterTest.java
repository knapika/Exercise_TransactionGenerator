import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import structures.Transaction;
import writers.TransactionJSONFileWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TransactionJSONFileWriterTest {
    private LocalDateTime now = LocalDateTime.now();
    private List<Transaction> transactionList = new LinkedList<Transaction>(Arrays.asList(
            new Transaction(2, 5,LocalDateTime.now(),null, 24.22)));
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Mock
    BufferedWriter writer;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void write() {
        //given
        TransactionJSONFileWriter uut = new TransactionJSONFileWriter(".");
        String jsonString_0 = gson.toJson(transactionList.get(0));

        //when
        uut.write(transactionList, writer);
        //then
        try {
            Mockito.verify(writer, Mockito.atLeast(1)).write(jsonString_0);
            Mockito.verify(writer, Mockito.atMost(1)).write(jsonString_0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeWithNull() {
        //given
        TransactionJSONFileWriter uut = new TransactionJSONFileWriter(".");

        //when
        boolean done = uut.write(transactionList, null);
        //then
        Assert.assertTrue(done);
    }

    @Test
    public void wrongDir() throws Exception {
        //given
        TransactionJSONFileWriter uut = new TransactionJSONFileWriter("badDir");

        //when
        boolean done = uut.write(transactionList, null);
        //then
        Assert.assertFalse(done);
    }
}
