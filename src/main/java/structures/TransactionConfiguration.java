package structures;

import writers.IWriter;

import java.util.Arrays;

public class TransactionConfiguration {
    private final Range<Integer> rangeOfCustomerId;
    private final String rangeOfDate;
    private final String fileWithItem;
    private final Range<Integer> rangeOfnumberOfItems;
    private final Range<Integer> rangeOfQuantities;
    private final int numberOfTrans;
    private final String ourDir;
    private final IWriter writer;

    public TransactionConfiguration(Range<Integer> rangeOfCustomerId, String rangeOfDate, String fileWithItem,
                                    Range<Integer> rangeOfnumberOfItems, Range<Integer> rangeOfQuantities, int numberOfTrans,
                                    String ourDir, IWriter writer) {
        this.rangeOfCustomerId = rangeOfCustomerId;
        this.rangeOfDate = rangeOfDate;
        this.fileWithItem = fileWithItem;
        this.rangeOfnumberOfItems = rangeOfnumberOfItems;
        this.rangeOfQuantities = rangeOfQuantities;
        this.numberOfTrans = numberOfTrans;
        this.ourDir = ourDir;
        this.writer = writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionConfiguration that = (TransactionConfiguration) o;

        if (numberOfTrans != that.numberOfTrans) return false;
        if (rangeOfCustomerId != null ? !rangeOfCustomerId.equals(that.rangeOfCustomerId) : that.rangeOfCustomerId != null)
            return false;
        if (rangeOfDate != null ? !rangeOfDate.equals(that.rangeOfDate) : that.rangeOfDate != null) return false;
        if (fileWithItem != null ? !fileWithItem.equals(that.fileWithItem) : that.fileWithItem != null) return false;
        if (rangeOfnumberOfItems != null ? !rangeOfnumberOfItems.equals(that.rangeOfnumberOfItems) : that.rangeOfnumberOfItems != null)
            return false;
        if (rangeOfQuantities != null ? !rangeOfQuantities.equals(that.rangeOfQuantities) : that.rangeOfQuantities != null)
            return false;
        if (ourDir != null ? !ourDir.equals(that.ourDir) : that.ourDir != null) return false;
        return writer != null ? writer.equals(that.writer) : that.writer == null;
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
}


