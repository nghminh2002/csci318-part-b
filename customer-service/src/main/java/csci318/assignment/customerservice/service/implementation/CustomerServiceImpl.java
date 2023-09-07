package csci318.assignment.customerservice.service.implementation;

import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.model.event.CustomerEvent;
import csci318.assignment.customerservice.repository.CustomerRepository;
import csci318.assignment.customerservice.service.CustomerService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomerServiceImpl(CustomerRepository customerRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.customerRepository = customerRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

//    Save new customer to database
    @Override
    public Customer createCustomer(Customer newCustomer) {
        Customer savedCustomer = customerRepository.save(newCustomer);
        CustomerEvent event = new CustomerEvent();
        event.setEventName("Create");
        event.setCustomerId(savedCustomer.getId());
        event.setCompanyName(savedCustomer.getCompanyName());
        event.setAddress(savedCustomer.getAddress().toString());
        event.setCountry(savedCustomer.getCountry());
        applicationEventPublisher.publishEvent(event);
        return savedCustomer;
    }

//    Update customer
    @Override
    public Customer updateCustomer(Customer updateCustomer) {
        updateCustomer.updateCustomer();
        return customerRepository.save(updateCustomer);
    }

//    Get all customers from database
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

//    Get customer by id
    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElse(null);
    }
}
