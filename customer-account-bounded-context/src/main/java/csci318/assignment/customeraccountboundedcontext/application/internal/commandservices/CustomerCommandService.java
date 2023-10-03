package csci318.assignment.customeraccountboundedcontext.application.internal.commandservices;

import csci318.assignment.customeraccountboundedcontext.application.internal.queryservices.CustomerQueryService;
import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;
import csci318.assignment.customeraccountboundedcontext.domain.model.commands.CreateCustomerCommand;
import csci318.assignment.customeraccountboundedcontext.domain.model.commands.UpdateCustomerCommand;
import csci318.assignment.customeraccountboundedcontext.infrastructure.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommandService {
    private final CustomerQueryService customerQueryService;
    private final CustomerRepository customerRepository;

    public CustomerCommandService(
            CustomerQueryService customerQueryService,
            CustomerRepository customerRepository
    ) {
        this.customerQueryService = customerQueryService;
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerCommand createCustomerCommand) {
        return customerRepository.save(new Customer(createCustomerCommand));
    }

    public Customer updateCustomer(UpdateCustomerCommand updateCustomerCommand) {
        Customer customer = customerQueryService.findByCustomerId(
                updateCustomerCommand.getCustomerId()
        );
        customer.updateCustomer(updateCustomerCommand);
        return customerRepository.save(customer);
    }

    public void addOrderToCustomer(Long customerId, String orderId) {
        Customer existingCustomer = customerQueryService.findByCustomerId(customerId);
        existingCustomer.addCreatedOrders(orderId);
        customerRepository.save(existingCustomer);
    }
}
