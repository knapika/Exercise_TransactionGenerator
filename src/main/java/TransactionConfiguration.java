public class TransactionConfiguration {
    private final String rangeOfCustomerId;
    private final String rangeOfDate;
    private final String fileWithItem;
    private final String rangeOfnumberOfItems;
    private final String rangeOfQuantities;
    private final String numberOfTrans;
    private final String ourDir;


    public TransactionConfiguration(String rangeOfCustomerId, String rangeOfDate, String fileWithItem,
                                    String rangeOfnumberOfItems, String rangeOfQuantities, String numberOfTrans,
                                    String ourDir) {
        this.rangeOfCustomerId = rangeOfCustomerId;
        this.rangeOfDate = rangeOfDate;
        this.fileWithItem = fileWithItem;
        this.rangeOfnumberOfItems = rangeOfnumberOfItems;
        this.rangeOfQuantities = rangeOfQuantities;
        this.numberOfTrans = numberOfTrans;
        this.ourDir = ourDir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionConfiguration that = (TransactionConfiguration) o;

        if (rangeOfCustomerId != null ? !rangeOfCustomerId.equals(that.rangeOfCustomerId) : that.rangeOfCustomerId != null)
            return false;
        if (rangeOfDate != null ? !rangeOfDate.equals(that.rangeOfDate) : that.rangeOfDate != null) return false;
        if (fileWithItem != null ? !fileWithItem.equals(that.fileWithItem) : that.fileWithItem != null) return false;
        if (rangeOfnumberOfItems != null ? !rangeOfnumberOfItems.equals(that.rangeOfnumberOfItems) : that.rangeOfnumberOfItems != null)
            return false;
        if (rangeOfQuantities != null ? !rangeOfQuantities.equals(that.rangeOfQuantities) : that.rangeOfQuantities != null)
            return false;
        if (numberOfTrans != null ? !numberOfTrans.equals(that.numberOfTrans) : that.numberOfTrans != null)
            return false;
        return ourDir != null ? ourDir.equals(that.ourDir) : that.ourDir == null;
    }

    public String getRangeOfCustomerId() {
        return rangeOfCustomerId;
    }

    public String getRangeOfDate() {
        return rangeOfDate;
    }

    public String getFileWithItem() {
        return fileWithItem;
    }

    public String getRangeOfnumberOfItems() {
        return rangeOfnumberOfItems;
    }

    public String getRangeOfQuantities() {
        return rangeOfQuantities;
    }

    public String getNumberOfTrans() {
        return numberOfTrans;
    }

    public String getOurDir() {
        return ourDir;
    }
}
