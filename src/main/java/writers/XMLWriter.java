package writers;

import structures.Transaction;

import java.io.BufferedWriter;
import java.util.List;

public class XMLWriter implements IWriter {
    @Override
    public boolean write(List<Transaction> transactionList, BufferedWriter writerBuff) {
        return false;
    }
}
