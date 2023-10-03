package csci318.assignment.customeraccountboundedcontext.domain.model.commands;

import csci318.assignment.customeraccountboundedcontext.domain.model.valueobject.Contact;

public class CreateCustomerCommand {
    private String companyName;
    private String address;
    private String country;
    private Contact contact;

    public CreateCustomerCommand() {}

    public CreateCustomerCommand(String companyName, String address, String country, Contact contact) {
        this.companyName = companyName;
        this.address = address;
        this.country = country;
        this.contact = contact;
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
