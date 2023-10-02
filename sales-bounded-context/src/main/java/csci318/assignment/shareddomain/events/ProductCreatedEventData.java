package csci318.assignment.shareddomain.events;

public class ProductCreatedEventData {
    private String eventName;
    private Long productId;
    private String productCategory;
    private String productName;

    public ProductCreatedEventData() {}

    public ProductCreatedEventData(Long productId, String productCategory, String productName) {
        this.eventName = "productcreating";
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
        return "ProductCreatedEventData{" +
                "productId=" + productId +
                ", productCategory='" + productCategory + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
