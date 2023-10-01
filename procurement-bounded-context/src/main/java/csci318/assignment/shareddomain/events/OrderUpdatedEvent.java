package csci318.assignment.shareddomain.events;

public class OrderUpdatedEvent {
    OrderUpdatedEventData orderUpdatedEventData;

    public OrderUpdatedEvent(OrderUpdatedEventData orderUpdatedEventData) {
        this.orderUpdatedEventData = orderUpdatedEventData;
    }

    public OrderUpdatedEventData getOrderUpdatedEventData() {
        return orderUpdatedEventData;
    }
}
