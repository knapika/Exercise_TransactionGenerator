package structures;

import writers.IWriter;

import java.util.Arrays;

public class TransactionConfiguration {
    private final int[] rangeOfCustomerId;
    private final String rangeOfDate;
    private final String fileWithItem;
    private final int[] rangeOfnumberOfItems;
    private final int[] rangeOfQuantities;
    private final int numberOfTrans;
    private final String ourDir;
    private final IWriter writer;

    public TransactionConfiguration(int[] rangeOfCustomerId, String rangeOfDate, String fileWithItem,
                                    int[] rangeOfnumberOfItems, int[] rangeOfQuantities, int numberOfTrans,
                                    String ourDir, IWriter writer ) {
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
        if (!Arrays.equals(rangeOfCustomerId, that.rangeOfCustomerId)) return false;
        if (rangeOfDate != null ? !rangeOfDate.equals(that.rangeOfDate) : that.rangeOfDate != null) return false;
        if (fileWithItem != null ? !fileWithItem.equals(that.fileWithItem) : that.fileWithItem != null) return false;
        if (!Arrays.equals(rangeOfnumberOfItems, that.rangeOfnumberOfItems)) return false;
        if (!Arrays.equals(rangeOfQuantities, that.rangeOfQuantities)) return false;
        return ourDir != null ? ourDir.equals(that.ourDir) : that.ourDir == null;
    }

    public int[] getRangeOfCustomerId() {
        return rangeOfCustomerId;
    }

    public String getRangeOfDate() {
        return rangeOfDate;
    }

    public String getFileWithItem() {
        return fileWithItem;
    }

    public int[] getRangeOfnumberOfItems() {
        return rangeOfnumberOfItems;
    }

    public int[] getRangeOfQuantities() {
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


