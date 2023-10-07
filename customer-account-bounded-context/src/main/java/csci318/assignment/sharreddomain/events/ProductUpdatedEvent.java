package csci318.assignment.sharreddomain.events;

public class ProductUpdatedEvent {
    ProductUpdatedEventData productUpdatedEventData;

    public ProductUpdatedEvent() {}

    public ProductUpdatedEvent(ProductUpdatedEventData productUpdatedEventData) {
        this.productUpdatedEventData = productUpdatedEventData;
    }

    public ProductUpdatedEventData getProductUpdatedEventData() {
        return productUpdatedEventData;
    }

    public void setProductUpdatedEventData(ProductUpdatedEventData productUpdatedEventData) {
        this.productUpdatedEventData = productUpdatedEventData;
    }

    @Override
    public String toString() {
        return "ProductUpdatedEvent{" +
                "productUpdatedEventData=" + productUpdatedEventData +
                '}';
    }
}
