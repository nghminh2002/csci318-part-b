package csci318.assignment.customerservice.service;

import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.repository.ContactRepository;
import csci318.assignment.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

//    Save new customer to database
    @Override
    public Customer createCustomer(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

//    Get all customers from database
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

//    Get customer by id
    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(RuntimeException::new);
    }

//    Update customer
    @Override
    public Customer updateCustomer(Customer updateCustomer) {
        return customerRepository.save(updateCustomer);
    }

//    Map user's contact to customer
    @Override
    public Customer updateCustomerContact(Long customerId, Long contactId) {
        // 1. Find existing customer
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(RuntimeException::new);

        // 2. Find existing contact
        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(RuntimeException::new);

        // 3. Set contact for customer
        existingCustomer.setContact(existingContact);

        // 4. Update the updated customer into database
        return customerRepository.save(existingCustomer);
    }

//    Save new contact to the database
    @Override
    public Contact createContact(Contact newContact) {
        return contactRepository.save(newContact);
    }

//    Update customer
    @Override
    public Contact updateContact(Contact updatedContact) {
        return contactRepository.save(updatedContact);
    }

    // Get contact by id
    @Override
    public Contact getContactById(Long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(RuntimeException::new);
    }

    // Get all contacts
    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
