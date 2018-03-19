import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionJSONFileWriterTest {
    private LocalDateTime now = LocalDateTime.now();
    private Transaction transaction = new Transaction(1, 2, LocalDateTime.now(),null, 254.22);
    @Mock
    BufferedWriter writer;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void write() {
        //given
        TransactionJSONFileWriter uut = new TransactionJSONFileWriter(".");
        String jsonString = "{\"id\":1,\"customerId\":2,\"timestamp\":\"" + transaction.getDate() +"\",\"sum\":254.22}";
        //when
        uut.write(transaction, writer);
        //then
        try {
            Mockito.verify(writer, Mockito.times(1)).write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
