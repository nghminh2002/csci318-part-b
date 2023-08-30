package csci318.assignment.customerservice.service.Implementation;

import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.repository.ContactRepository;
import csci318.assignment.customerservice.repository.CustomerRepository;
import csci318.assignment.customerservice.service.CustomerService;
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

//    Update customer by id
    @Override
    public Customer updateCustomerById(Long customerId, Customer updateCustomer) {
        // 1. Find existing customer
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(RuntimeException::new);

        // 2. Check if the company name needs to be updated
        // If yes, replace old company with the new company name
        if (updateCustomer.getCompanyName() != null) {
            existingCustomer.setCompanyName(updateCustomer.getCompanyName());
        }

        // 3. Check if the address needs to be updated
        // If yes, replace old address with the new address
        if (updateCustomer.getAddress() != null) {
            existingCustomer.setAddress(updateCustomer.getAddress());
        }

        // 4. Check if the country needs to be updated
        // If yes, replace old country with the new country
        if (updateCustomer.getCountry() != null) {
            existingCustomer.setCountry(updateCustomer.getCountry());
        }

        //5. Save updated customer to database
        return customerRepository.save(existingCustomer);
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

//    Update contact by id
    @Override
    public Contact updateContact(Long contactId, Contact updatedContact) {
        // 1. Find existing contact
        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(RuntimeException::new);

        // 2. Check if the name needs to be updated
        // If yes, replace old name with the new name
        if (updatedContact.getName() != null) {
            existingContact.setName(updatedContact.getName());
        }

        // 3. Check if the phone needs to be updated
        // If yes, replace old phone with the new phone
        if (updatedContact.getPhone() != null) {
            existingContact.setPhone(updatedContact.getPhone());
        }

        // 4. Check if the email needs to be updated
        // If yes, replace old email with the new email
        if (updatedContact.getEmail() != null) {
            existingContact.setEmail(updatedContact.getEmail());
        }

        // 5. Check if the position needs to be updated
        // If yes, replace old position with the new position
        if (updatedContact.getPosition() != null) {
            existingContact.setPosition(updatedContact.getPosition());
        }

        //6. Save the updated contact into the database
        return contactRepository.save(existingContact);
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
