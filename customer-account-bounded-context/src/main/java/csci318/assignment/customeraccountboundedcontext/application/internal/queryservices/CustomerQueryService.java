package csci318.assignment.customeraccountboundedcontext.application.internal.queryservices;

import csci318.assignment.customeraccountboundedcontext.infrastructure.repositories.CustomerRepository;
import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerQueryService {
    private final CustomerRepository customerRepository;

    public CustomerQueryService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByCustomerId(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("The customer does not exist");
        }
        return optionalCustomer.get();
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
