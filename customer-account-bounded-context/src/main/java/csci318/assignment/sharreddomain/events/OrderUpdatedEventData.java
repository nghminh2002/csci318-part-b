package csci318.assignment.sharreddomain.events;

public class OrderUpdatedEventData {
    private String orderId;

    public OrderUpdatedEventData(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}

