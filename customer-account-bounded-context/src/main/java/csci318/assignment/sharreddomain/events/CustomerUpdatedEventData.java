package csci318.assignment.sharreddomain.events;

public class CustomerUpdatedEventData {
    private String eventName;
    private Long customerId;
    private String companyName;
    private String address;

    public CustomerUpdatedEventData() {
    }

    public CustomerUpdatedEventData(Long customerId, String companyName, String address) {
        this.eventName = "customerupdating";
        this.customerId = customerId;
        this.companyName = companyName;
        this.address = address;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerUpdatedEventData{" +
                "eventName='" + eventName + '\'' +
                ", customerId=" + customerId +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
