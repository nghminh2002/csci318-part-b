package csci318.assignment.shareddomain.events;

public class OrderCreatedEventData {
    private String orderId;
    private String supplierName;
    private String productName;
    private Integer quantity;

    public OrderCreatedEventData() {}

    public OrderCreatedEventData(String orderId) {
        this.orderId = orderId;
    }

    public OrderCreatedEventData(String orderId,
                                 String supplierName,
                                 String productName,
                                 Integer quantity) {
        this.orderId = orderId;
        this.supplierName = supplierName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderCreatedEventData{" +
                "orderId='" + orderId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
