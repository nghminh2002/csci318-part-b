package csci318.assignment.sharreddomain.events;

public class ProductCreatedEvent {
    ProductCreatedEventData productCreatedEventData;

    public ProductCreatedEvent() {}

    public ProductCreatedEvent(ProductCreatedEventData productCreatedEventData) {
        this.productCreatedEventData = productCreatedEventData;
    }

    public ProductCreatedEventData getProductCreatedEventData() {
        return productCreatedEventData;
    }

    public void setProductCreatedEventData(ProductCreatedEventData productCreatedEventData) {
        this.productCreatedEventData = productCreatedEventData;
    }

    @Override
    public String toString() {
        return "ProductCreatedEvent{" +
                "productCreatedEventData=" + productCreatedEventData +
                '}';
    }
}
