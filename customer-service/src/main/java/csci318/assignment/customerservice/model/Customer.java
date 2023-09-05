package csci318.assignment.customerservice.model;

import csci318.assignment.customerservice.model.event.CustomerEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer extends AbstractAggregateRoot<Customer> {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String companyName;

    @Embedded
    private Address address;

    @Column
    private String country;

    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "contactId")
    private Contact contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", address='" + address.toString() + '\'' +
                ", country='" + country + '\'' +
                ", contact=" + contact.toString() +
                '}';
    }

    public void createCustomer() {
        CustomerEvent event = new CustomerEvent();
        event.setEventName("Create");
        event.setCustomerId(this.id);
        event.setAddress(this.address.toString());
        event.setCountry(this.country);
        registerEvent(event);
    }

    public void updateCustomer() {
        CustomerEvent event = new CustomerEvent();
        event.setEventName("Update");
        event.setCustomerId(this.id);
        event.setAddress(this.address.toString());
        event.setCountry(this.country);
        registerEvent(event);
    }
}
