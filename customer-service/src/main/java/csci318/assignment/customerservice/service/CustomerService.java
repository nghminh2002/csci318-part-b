package csci318.assignment.customerservice.service;

import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;

import java.util.List;

public interface CustomerService {
    //Operations for customer
    Customer createCustomer(Customer newCustomer);
    Customer updateCustomer(Customer updateCustomer);
    Customer updateCustomerContact(Long customerId, Long contactId);
    Customer getCustomerById(Long customerId);
    List<Customer> getAllCustomers();

    //Operations for contact
    Contact createContact(Contact newContact);
    Contact updateContact(Contact updatedContact);
    Contact getContactById(Long contactId);
    List<Contact> getAllContacts();
}
