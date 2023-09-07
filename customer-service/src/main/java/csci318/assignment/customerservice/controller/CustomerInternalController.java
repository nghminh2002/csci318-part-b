package csci318.assignment.customerservice.controller;

import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/internal")
public class CustomerInternalController {

    private final CustomerService customerService;

    public CustomerInternalController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //    Use case: Get customer by id
    @GetMapping("/{customerId}")
    Customer getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }
}

