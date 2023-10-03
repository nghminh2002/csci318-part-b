package csci318.assignment.customeraccountboundedcontext.domain.model.commands;

import csci318.assignment.customeraccountboundedcontext.domain.model.valueobject.Contact;

public class UpdateCustomerCommand {
    private Long customerId;
    private String companyName;
    private String address;
    private String country;
    private Contact contact;

    public UpdateCustomerCommand() {}

    public UpdateCustomerCommand(Long customerId) {
        this.customerId = customerId;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
