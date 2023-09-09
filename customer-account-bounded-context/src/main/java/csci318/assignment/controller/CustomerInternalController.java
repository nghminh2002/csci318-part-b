package csci318.assignment.controller;

import csci318.assignment.model.Customer;
import csci318.assignment.service.CustomerApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/internal")
public class CustomerInternalController {

    private final CustomerApplicationService customerApplicationService;

    public CustomerInternalController(CustomerApplicationService customerApplicationService) {
        this.customerApplicationService = customerApplicationService;
    }

    //    Use case: Get customer by id
    @GetMapping("/{customerId}")
    Customer getCustomer(@PathVariable Long customerId) {
        return customerApplicationService.getCustomerById(customerId);
    }
}

