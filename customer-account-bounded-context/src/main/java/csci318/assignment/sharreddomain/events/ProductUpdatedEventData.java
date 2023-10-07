package csci318.assignment.sharreddomain.events;

public class ProductUpdatedEventData {
    private String eventName;
    private Long productId;
    private String productCategory;
    private String productName;

    public ProductUpdatedEventData() {}

    public ProductUpdatedEventData(Long productId, String productCategory, String productName) {
        this.eventName = "productupdating";
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ProductUpdatedEventData{" +
                "eventName='" + eventName + '\'' +
                ", productId=" + productId +
                ", productCategory='" + productCategory + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
