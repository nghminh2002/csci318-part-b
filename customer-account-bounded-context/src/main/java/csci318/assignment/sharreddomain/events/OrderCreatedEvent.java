package csci318.assignment.sharreddomain.events;

public class OrderCreatedEvent {
    OrderCreatedEventData orderCreatedEventData;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(OrderCreatedEventData orderCreatedEventData) {
        this.orderCreatedEventData = orderCreatedEventData;
    }

    public OrderCreatedEventData getOrderCreatedEventData() {
        return orderCreatedEventData;
    }

    public void setOrderCreatedEventData(OrderCreatedEventData orderCreatedEventData) {
        this.orderCreatedEventData = orderCreatedEventData;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderCreatedEventData=" + orderCreatedEventData +
                '}';
    }
}
