import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class TransactionJSONFileWriterTest {
    private LocalDateTime now = LocalDateTime.now();
    private Transaction transaction = new Transaction(1, 2, LocalDateTime.now(),null, 254.22);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Mock
    BufferedWriter writer;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void write() {
        //given
        TransactionJSONFileWriter uut = new TransactionJSONFileWriter(".");
        String jsonString = gson.toJson(transaction);
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
