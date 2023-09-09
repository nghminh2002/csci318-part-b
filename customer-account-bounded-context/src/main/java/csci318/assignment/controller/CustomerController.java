package csci318.assignment.controller;

import csci318.assignment.controller.dto.CustomerResponseDTO;
import csci318.assignment.controller.dto.CustomerRequestDTO;
import csci318.assignment.model.Customer;
import csci318.assignment.service.CustomerApplicationService;
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

    private final CustomerApplicationService customerApplicationService;

    public CustomerController(CustomerApplicationService customerApplicationService) {
        this.customerApplicationService = customerApplicationService;
    }

//    Use case: Create customer
    @PostMapping()
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO request) {
        Customer newCustomer = customerApplicationService.createCustomer(request);
        return new CustomerResponseDTO(newCustomer);
    }

//    Use case: Update customer name, address, country, contact
    @PutMapping("/{customerId}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequestDTO request) {
        Customer updatedCustomer =  customerApplicationService.updateCustomer(customerId, request);
        return new CustomerResponseDTO(updatedCustomer);
    }

//    Use case: Get customer by id
    @GetMapping("/{customerId}")
    CustomerResponseDTO getCustomer(@PathVariable Long customerId) {
        Customer existingCustomer = customerApplicationService.getCustomerById(customerId);
        return new CustomerResponseDTO(existingCustomer);
    }

//    Use case: Get all customers
    @GetMapping()
    List<CustomerResponseDTO> getAllCustomers() {
        return customerApplicationService.getAllCustomers().stream()
                .map(CustomerResponseDTO::new)
                .collect(Collectors.toList());
    }
}
