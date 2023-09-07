package csci318.assignment.customerservice.model;

import csci318.assignment.customerservice.model.event.CustomerEvent;
import csci318.assignment.customerservice.model.valueobject.Contact;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer extends AbstractAggregateRoot<Customer> {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String companyName;

    @Column
    private String address;

    @Column
    private String country;

    @Embedded
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
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", contact=" + contact.toString() +
                '}';
    }

    public void updateCustomer() {
        CustomerEvent event = new CustomerEvent();
        event.setEventName("Update");
        event.setCompanyName(this.companyName);
        event.setCustomerId(this.id);
        event.setAddress(this.address);
        event.setCountry(this.country);
        registerEvent(event);
    }
}
