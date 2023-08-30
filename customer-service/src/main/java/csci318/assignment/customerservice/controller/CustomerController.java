package csci318.assignment.customerservice.controller;

import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    Use case: Create customer
    @PostMapping()
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerService.createCustomer(newCustomer);
    }

//    Use case: Update customer name, address, country
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        return customerService.updateCustomerById(customerId, customer);
    }

//    Use case: Map customer to contact
    @PutMapping("/{customerId}/contact/{contactId}")
    public Customer updateCustomerContact(@PathVariable Long customerId, @PathVariable Long contactId) {
        return customerService.updateCustomerContact(customerId, contactId);
    }

//    Use case: Get customer by id
    @GetMapping("/{id}")
    Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

//    Use case: Get all customers
    @GetMapping()
    List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

//    Use case: Create contact
    @PostMapping("/contact")
    public Contact createContact(@RequestBody Contact newContact) {
        return customerService.createContact(newContact);
    }

//    Use case: Update contact for customer by id
    @PutMapping("/contact/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        return customerService.updateContact(id, contact);
    }

//    Use case: Get contact by id
    @GetMapping("/contact/{id}")
    Contact getContact(@PathVariable Long id) {
        return customerService.getContactById(id);
    }

//    Use case: Get all contacts
    @GetMapping("/contact")
    List<Contact> getAllContacts() {
        return customerService.getAllContacts();
    }
}
