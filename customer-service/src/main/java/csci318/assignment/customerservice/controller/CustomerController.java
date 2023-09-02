package csci318.assignment.customerservice.controller;

import csci318.assignment.customerservice.controller.dto.CustomerResponseDTO;
import csci318.assignment.customerservice.controller.dto.CustomerRequestDTO;
import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    Use case: Create customer
    @PostMapping()
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO request) {
        Customer customer = new Customer();
        customer.setCompanyName(request.getCompanyName());
        customer.setAddress(request.getAddress());
        customer.setCountry(request.getCountry());
        Customer newCustomer = customerService.createCustomer(customer);

        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setPhone(request.getPhone());
        contact.setEmail(request.getEmail());
        contact.setPosition(request.getPosition());
        Contact newContact = customerService.createContact(contact);

        newCustomer.setContact(newContact);
        customerService.updateCustomer(newCustomer);

        return new CustomerResponseDTO(newCustomer);
    }

//    Use case: Update customer name, address, country
    @PatchMapping("/{customerId}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequestDTO request) {
        // 1. Find existing customer
        Customer existingCustomer = customerService.getCustomerById(customerId);

        // 2. Check if the company name needs to be updated
        // If yes, replace old company with the new company name
        if (request.getCompanyName() != null) {
            existingCustomer.setCompanyName(request.getCompanyName());
        }

        // 3. Check if the address needs to be updated
        // If yes, replace old address with the new address
        if (request.getAddress() != null) {
            existingCustomer.setAddress(request.getAddress());
        }

        // 4. Check if the country needs to be updated
        // If yes, replace old country with the new country
        if (request.getCountry() != null) {
            existingCustomer.setCountry(request.getCountry());
        }

        // 5. Find existing customer's contact
        Contact existingContact = customerService.getContactById(existingCustomer.getContact().getId());

        // 6. Check if the name needs to be updated
        // If yes, replace old name with the new name
        if (request.getName() != null) {
            existingContact.setName(request.getName());
        }

        // 7. Check if the phone needs to be updated
        // If yes, replace old phone with the new phone
        if (request.getPhone() != null) {
            existingContact.setPhone(request.getPhone());
        }

        // 8. Check if the email needs to be updated
        // If yes, replace old email with the new email
        if (request.getEmail() != null) {
            existingContact.setEmail(request.getEmail());
        }

        // 9. Check if the position needs to be updated
        // If yes, replace old position with the new position
        if (request.getPosition() != null) {
            existingContact.setPosition(request.getPosition());
        }

        //10. Save updated customer and contact to database
        Customer updatedCustomer =  customerService.updateCustomer(existingCustomer);
        customerService.updateContact(existingContact);

        return new CustomerResponseDTO(updatedCustomer);
    }

//    Use case: Map customer to contact
    @PatchMapping("/{customerId}/contact/{contactId}")
    public CustomerResponseDTO updateCustomerContact(@PathVariable Long customerId, @PathVariable Long contactId) {
        Customer updatedCustomer =  customerService.updateCustomerContact(customerId, contactId);
        return new CustomerResponseDTO(updatedCustomer);
    }

//    Use case: Get customer by id
    @GetMapping("/{customerId}")
    CustomerResponseDTO getCustomer(@PathVariable Long customerId) {
        Customer existingCustomer = customerService.getCustomerById(customerId);
        return new CustomerResponseDTO(existingCustomer);
    }

//    Use case: Get all customers
    @GetMapping()
    List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream().map(CustomerResponseDTO::new).collect(Collectors.toList());
    }

//    Use case: Get contact by id
    @GetMapping("/contact/{id}")
    CustomerResponseDTO getContact(@PathVariable Long id) {
        Contact existingContact = customerService.getContactById(id);
        return new CustomerResponseDTO(existingContact);
    }

//    Use case: Get all contacts
    @GetMapping("/contact")
    List<CustomerResponseDTO> getAllContacts() {
        return customerService.getAllContacts().stream().map(CustomerResponseDTO::new).collect(Collectors.toList());
    }
}
