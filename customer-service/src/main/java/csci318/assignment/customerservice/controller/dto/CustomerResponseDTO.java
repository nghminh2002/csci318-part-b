package csci318.assignment.customerservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;

public class CustomerResponseDTO {
    @JsonProperty
    private final Long id;

    @JsonProperty
    private final String companyName;

    @JsonProperty
    private final String address;

    @JsonProperty
    private final String country;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String phone;

    @JsonProperty
    private final String email;

    @JsonProperty
    private final String position;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();

        Contact contact = customer.getContact();
        this.name = contact.getName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.position = contact.getPosition();
    }

    public CustomerResponseDTO(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.position = contact.getPosition();

        Customer customer = contact.getCustomer();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }
}
