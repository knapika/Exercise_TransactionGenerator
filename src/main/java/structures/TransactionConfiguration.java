package structures;

import writers.IWriter;

import java.util.Arrays;
import java.util.Objects;

public class TransactionConfiguration {
    private final Range<Integer> rangeOfCustomerId;
    private final String rangeOfDate;
    private final String fileWithItem;
    private final Range<Integer> rangeOfnumberOfItems;
    private final Range<Integer> rangeOfQuantities;
    private final int numberOfTrans;
    private final String ourDir;
    private final IWriter writer;
    private final String brokerUrl;
    private final String queue;
    private final String topic;
    private final boolean ifOutDir;
    private final String format;


    public boolean isIfOutDir() {
        return ifOutDir;
    }

    public TransactionConfiguration(Range<Integer> rangeOfCustomerId, String rangeOfDate, String fileWithItem,
                                    Range<Integer> rangeOfnumberOfItems, Range<Integer> rangeOfQuantities,
                                    int numberOfTrans, String ourDir, IWriter writer, String brokerUrl, String queue,
                                    String topic, boolean ifOutDir, String format) {
        this.rangeOfCustomerId = rangeOfCustomerId;
        this.rangeOfDate = rangeOfDate;
        this.fileWithItem = fileWithItem;
        this.rangeOfnumberOfItems = rangeOfnumberOfItems;
        this.rangeOfQuantities = rangeOfQuantities;
        this.numberOfTrans = numberOfTrans;
        this.ourDir = ourDir;
        this.writer = writer;
        this.brokerUrl = brokerUrl;
        this.queue = queue;
        this.topic = topic;
        this.ifOutDir = ifOutDir;
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionConfiguration that = (TransactionConfiguration) o;
        return numberOfTrans == that.numberOfTrans &&
                Objects.equals(rangeOfCustomerId, that.rangeOfCustomerId) &&
                Objects.equals(rangeOfDate, that.rangeOfDate) &&
                Objects.equals(fileWithItem, that.fileWithItem) &&
                Objects.equals(rangeOfnumberOfItems, that.rangeOfnumberOfItems) &&
                Objects.equals(rangeOfQuantities, that.rangeOfQuantities) &&
                Objects.equals(ourDir, that.ourDir) &&
                Objects.equals(writer, that.writer) &&
                Objects.equals(brokerUrl, that.brokerUrl) &&
                Objects.equals(queue, that.queue) &&
                Objects.equals(topic, that.topic);
    }


    public Range<Integer> getRangeOfCustomerId() {
        return rangeOfCustomerId;
    }

    public String getRangeOfDate() {
        return rangeOfDate;
    }

    public String getFileWithItem() {
        return fileWithItem;
    }

    public Range<Integer> getRangeOfnumberOfItems() {
        return rangeOfnumberOfItems;
    }

    public Range<Integer> getRangeOfQuantities() {
        return rangeOfQuantities;
    }

    public int getNumberOfTrans() {
        return numberOfTrans;
    }

    public String getOurDir() {
        return ourDir;
    }

    public IWriter getWriter() {
        return writer;
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public String getQueue() {
        return queue;
    }

    public String getTopic() {
        return topic;
    }
}


