package csci318.assignment.shareddomain.events;

public class OrderUpdatedEventData {
    private String orderId;

    public OrderUpdatedEventData(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
