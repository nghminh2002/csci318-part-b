package csci318.assignment.salesboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.model.valueobject.Contact;
import csci318.assignment.model.Customer;

public class CustomerResponseDTO {
    @JsonProperty
    private final Long customerId;

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
        this.customerId = customer.getId();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress().toString();
        this.country = customer.getCountry();
        Contact contact = customer.getContact();
        this.name = contact.getName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.position = contact.getPosition();
    }
}
