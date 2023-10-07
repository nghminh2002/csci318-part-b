package csci318.assignment.shareddomain.events;

public class CustomerCreatedEventData {
    private String eventName;
    private Long customerId;
    private String companyName;
    private String address;

    public CustomerCreatedEventData() {
    }

    public CustomerCreatedEventData(Long customerId, String companyName, String address) {
        this.eventName = "customercreating";
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
        return "CustomerCreatedEventData{" +
                "eventName='" + eventName + '\'' +
                ", customerId=" + customerId +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}