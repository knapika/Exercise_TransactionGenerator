package writers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structures.Transaction;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class XMLWriter implements IWriter {
    private final Logger XMLWriterLogger = LogManager.getLogger(XMLWriter.class);
    private final String dir;

    public XMLWriter(String dir) {
        this.dir = dir;
    }

    @Override
    public boolean write(List<Transaction> transactionList, BufferedWriter writerBuff) {
        String path ="";
        BufferedWriter writer = null;
        XMLEncoder encoder = null;
        XMLWriterLogger.info("Start writing XMLs to files");
        try {
            for (Transaction tran : transactionList) {
                path = dir + "/transaction" + tran.getId() + ".xml";
                XStream mapping = new XStream(new DomDriver());
                String xml = mapping.toXML(tran);
                XMLWriterLogger.debug("Start writing transaction " + tran.getId());
                if (writerBuff == null) {
                    writer = new BufferedWriter(new FileWriter(path));
                } else {
                    writer = writerBuff;
                }
                writer.write(xml);
                writer.close();
                XMLWriterLogger.debug("Transaction " + tran.getId() + " successfully written");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
