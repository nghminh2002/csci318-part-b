package csci318.assignment.customerservice.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CustomerEvent {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String eventName;

    @Column
    private Long customerId;

    @Column
    private String companyName;

    @Column
    private String address;

    @Column
    private String country;

    public CustomerEvent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CustomerEvent{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", customerId=" + customerId +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

