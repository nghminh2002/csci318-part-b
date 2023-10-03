package csci318.assignment.customeraccountboundedcontext.interfaces.rest;

import csci318.assignment.customeraccountboundedcontext.application.internal.queryservices.CustomerQueryService;
import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/internal")
public class CustomerInternalController {

    private final CustomerQueryService customerQueryService;

    public CustomerInternalController(CustomerQueryService customerQueryService) {

        this.customerQueryService = customerQueryService;
    }

//    Use case: Get customer by id
    @GetMapping("/{customerId}")
    Customer getCustomer(@PathVariable Long customerId) {
        return customerQueryService.findByCustomerId(customerId);
    }
}

