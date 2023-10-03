package csci318.assignment.sharreddomain.events;

public class CustomerCreatedEvent {
    CustomerCreatedEventData customerCreatedEventData;

    public CustomerCreatedEvent() {}

    public CustomerCreatedEvent(CustomerCreatedEventData customerCreatedEventData) {
        this.customerCreatedEventData = customerCreatedEventData;
    }

    public CustomerCreatedEventData getCustomerCreatedEventData() {
        return customerCreatedEventData;
    }

    public void setCustomerCreatedEventData(CustomerCreatedEventData customerCreatedEventData) {
        this.customerCreatedEventData = customerCreatedEventData;
    }

    @Override
    public String toString() {
        return "CustomerCreatedEvent{" +
                "customerCreatedEventData=" + customerCreatedEventData +
                '}';
    }
}
