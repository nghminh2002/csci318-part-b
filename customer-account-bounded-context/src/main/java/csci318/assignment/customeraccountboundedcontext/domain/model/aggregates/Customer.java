package csci318.assignment.customeraccountboundedcontext.domain.model.aggregates;

import csci318.assignment.customeraccountboundedcontext.domain.model.commands.CreateCustomerCommand;
import csci318.assignment.customeraccountboundedcontext.domain.model.commands.UpdateCustomerCommand;
import csci318.assignment.customeraccountboundedcontext.domain.model.valueobject.Contact;
import csci318.assignment.sharreddomain.events.CustomerCreatedEvent;
import csci318.assignment.sharreddomain.events.CustomerCreatedEventData;
import csci318.assignment.sharreddomain.events.CustomerUpdatedEvent;
import csci318.assignment.sharreddomain.events.CustomerUpdatedEventData;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends AbstractAggregateRoot<Customer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String companyName;

    @Column
    private String address;

    @Column
    private String country;

    @Embedded
    private Contact contact;

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass=String.class)
    private List<String> createdOrders = new ArrayList<>();

    public Customer() {}

    public Customer(CreateCustomerCommand createCustomerCommand) {
        this.companyName = createCustomerCommand.getCompanyName();
        this.address = createCustomerCommand.getAddress();
        this.country = createCustomerCommand.getCountry();
        this.contact = createCustomerCommand.getContact();
        addDomainEvent(new CustomerCreatedEvent(new CustomerCreatedEventData(
                this.id, this.companyName, this.address
        )));
    }

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

    public List<String> getCreatedOrders() {
        return createdOrders;
    }

    public void addCreatedOrders(String orderId) {
        this.createdOrders.add(orderId);
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

    public void updateCustomer(UpdateCustomerCommand updateCustomerCommand) {
        this.companyName = updateCustomerCommand.getCompanyName();
        this.address = updateCustomerCommand.getAddress();
        this.country = updateCustomerCommand.getCountry();
        this.contact = updateCustomerCommand.getContact();
        addDomainEvent(new CustomerUpdatedEvent(new CustomerUpdatedEventData(
                this.id, this.companyName, this.address)));
    }

    public void addDomainEvent(Object event){
        registerEvent(event);
    }
}
