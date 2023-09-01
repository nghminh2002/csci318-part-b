package csci318.assignment.customerservice.controller.dto;

import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;

public class CustomerResponseDTO {
    private final String companyName;
    private final String address;
    private final String country;
    private final String name;
    private final String phone;
    private final String email;
    private final String position;

    public CustomerResponseDTO(Customer customer) {
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
        this.name = contact.getName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.position = contact.getPosition();

        Customer customer = contact.getCustomer();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();
    }
}
