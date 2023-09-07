package csci318.assignment.customerservice.controller;

import csci318.assignment.customerservice.controller.dto.CustomerResponseDTO;
import csci318.assignment.customerservice.controller.dto.CustomerRequestDTO;
import csci318.assignment.customerservice.model.valueobject.Address;
import csci318.assignment.customerservice.model.valueobject.Contact;
import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        Address address = new Address(request.getStreet(), request.getCity(), request.getState());
        Contact contact = new Contact(request.getName(), request.getPhone(), request.getEmail(), request.getPosition());

        Customer customer = new Customer();
        customer.setCompanyName(request.getCompanyName());
        customer.setAddress(address);
        customer.setCountry(request.getCountry());
        customer.setContact(contact);
        Customer newCustomer = customerService.createCustomer(customer);

        return new CustomerResponseDTO(newCustomer);
    }

//    Use case: Update customer name, address, country, contact
    @PutMapping("/{customerId}")
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
        if (request.getStreet() != null || request.getCity() != null || request.getState() != null) {
            Address currentAddress = existingCustomer.getAddress();
            String street = request.getStreet() != null ? request.getStreet() : currentAddress.getStreet();
            String city = request.getCity() != null ? request.getCity() : currentAddress.getCity();
            String state = request.getState() != null ? request.getState() : currentAddress.getState();
            existingCustomer.setAddress(new Address(street, city, state));
        }

        // 4. Check if the country needs to be updated
        // If yes, replace old country with the new country
        if (request.getCountry() != null) {
            existingCustomer.setCountry(request.getCountry());
        }

        // 5. Check if the contact needs to be updated
        // If yes, replace old contact with the new contact
        if (request.getName() != null || request.getPhone() != null || request.getEmail() != null || request.getPosition() != null) {
            Contact currentContact = existingCustomer.getContact();
            String name = request.getName() != null ? request.getName() : currentContact.getName();
            String phone = request.getPhone() != null ? request.getPhone() : currentContact.getPhone();
            String email = request.getEmail() != null ? request.getEmail() : currentContact.getEmail();
            String position = request.getPosition() != null ? request.getPosition() : currentContact.getPosition();
            existingCustomer.setContact(new Contact(name, phone, email, position));
        }

        //10. Save updated customer database
        Customer updatedCustomer =  customerService.updateCustomer(existingCustomer);

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
}
