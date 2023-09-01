package csci318.assignment.customerservice.controller.dto;

import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;

public class CustomerResponseDTO {
    private final String companyName;
    private final String address;
    private final String country;
    private final String contactName;
    private final String contactPhone;
    private final String contactEmail;
    private final String contactPosition;

    public CustomerResponseDTO(Customer customer) {
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();

        Contact contact = customer.getContact();
        this.contactName = contact.getName();
        this.contactPhone = contact.getPhone();
        this.contactEmail = contact.getEmail();
        this.contactPosition = contact.getPosition();
    }

    public CustomerResponseDTO(Contact contact) {
        this.contactName = contact.getName();
        this.contactPhone = contact.getPhone();
        this.contactEmail = contact.getEmail();
        this.contactPosition = contact.getPosition();

        Customer customer = contact.getCustomer();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();
    }
}
