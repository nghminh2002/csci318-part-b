package csci318.assignment.customeraccountboundedcontext.interfaces.rest;

import csci318.assignment.customeraccountboundedcontext.application.internal.commandservices.CustomerCommandService;
import csci318.assignment.customeraccountboundedcontext.application.internal.outboundservices.ExternalCustomerService;
import csci318.assignment.customeraccountboundedcontext.application.internal.queryservices.CustomerQueryService;
import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;
import csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto.CustomerOrderHistoryDTO;
import csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto.CustomerRequestDTO;
import csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto.CustomerResponseDTO;
import csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto.OrderProductResponseDTO;
import csci318.assignment.customeraccountboundedcontext.interfaces.rest.transform.CustomerCommandDTOAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;
    private final ExternalCustomerService externalCustomerService;

    public CustomerController(
            CustomerCommandService customerCommandService,
            CustomerQueryService customerQueryService,
            ExternalCustomerService externalCustomerService
    ) {
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
        this.externalCustomerService = externalCustomerService;
    }

//  Use case: Create customer
    @PostMapping()
    @ResponseBody
    public CustomerResponseDTO createCustomer(
            @RequestBody CustomerRequestDTO request
    ) {
        Customer newCustomer = customerCommandService.createCustomer(
                CustomerCommandDTOAssembler.toCommandFromDTO(request)
        );
        return new CustomerResponseDTO(newCustomer);
    }

//  Use case: Update customer name, address, country, contact
    @PutMapping("/{customerId}")
    @ResponseBody
    public CustomerResponseDTO updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequestDTO request
    ) {
        Customer originalCustomer = customerQueryService.findByCustomerId(customerId);
        Customer updatedCustomer =  customerCommandService.updateCustomer(
                CustomerCommandDTOAssembler.toCommandFromDTO(originalCustomer, request)
        );
        return new CustomerResponseDTO(updatedCustomer);
    }

//  Use case: Get customer by id
    @GetMapping("/{customerId}")
    @ResponseBody
    CustomerResponseDTO getCustomer(
            @PathVariable Long customerId
    ) {
        Customer existingCustomer = customerQueryService.findByCustomerId(customerId);
        return new CustomerResponseDTO(existingCustomer);
    }

//  Use case: Get all customers
    @GetMapping()
    @ResponseBody
    List<CustomerResponseDTO> getAllCustomers() {
        return customerQueryService.findAll().stream()
                .map(CustomerResponseDTO::new)
                .collect(Collectors.toList());
    }

    //    Use case: Get all products
    @GetMapping("/{customerId}/order-history")
    @ResponseBody
    CustomerOrderHistoryDTO getOrderHistoryByCustomerId(
            @PathVariable Long customerId
    ) {
        Customer existingCustomer = customerQueryService.findByCustomerId(customerId);
        List<OrderProductResponseDTO> createdOrders = externalCustomerService
                .getCustomerOrderHistory(existingCustomer.getCreatedOrders());
        return new CustomerOrderHistoryDTO(existingCustomer, createdOrders);
    }
}
