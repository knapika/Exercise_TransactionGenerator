package writers;

import structures.Transaction;

import java.io.BufferedWriter;
import java.util.List;

public interface IWriter {
    public boolean write(List<Transaction> transactionList, BufferedWriter writerBuff);
}
