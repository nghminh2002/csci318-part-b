package csci318.assignment.shareddomain.events;

public class CustomerUpdatedEvent {
    CustomerUpdatedEventData customerUpdatedEventData;

    public CustomerUpdatedEvent() {}

    public CustomerUpdatedEvent(CustomerUpdatedEventData customerUpdatedEventData) {
        this.customerUpdatedEventData = customerUpdatedEventData;
    }

    public CustomerUpdatedEventData getCustomerUpdatedEventData() {
        return customerUpdatedEventData;
    }

    public void setCustomerUpdatedEventData(CustomerUpdatedEventData customerUpdatedEventData) {
        this.customerUpdatedEventData = customerUpdatedEventData;
    }

    @Override
    public String toString() {
        return "CustomerUpdatedEvent{" +
                "customerUpdatedEventData=" + customerUpdatedEventData +
                '}';
    }
}
