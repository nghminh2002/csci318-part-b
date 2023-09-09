package csci318.assignment.service;

import csci318.assignment.controller.dto.CustomerRequestDTO;
import csci318.assignment.model.Customer;
import csci318.assignment.model.event.CustomerEvent;
import csci318.assignment.model.valueobject.Contact;
import csci318.assignment.repository.CustomerRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDomainService customerDomainService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerDomainService customerDomainService,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.customerRepository = customerRepository;
        this.customerDomainService = customerDomainService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    //    Save new customer to database
    public Customer createCustomer(CustomerRequestDTO request) {
        Customer customer = customerDomainService.createFromDTO(request);
        Customer savedCustomer = customerRepository.save(customer);

        CustomerEvent event = new CustomerEvent();
        event.setEventName("Create");
        event.setCustomerId(savedCustomer.getId());
        event.setCompanyName(savedCustomer.getCompanyName());
        event.setAddress(savedCustomer.getAddress());
        event.setCountry(savedCustomer.getCountry());
        applicationEventPublisher.publishEvent(event);

        return savedCustomer;
    }

    //    Update customer
    public Customer updateCustomer(Long customerId, CustomerRequestDTO request) {
        // 1. Find existing customer
        Customer existingCustomer = this.getCustomerById(customerId);

        // 2. Update customer
        Customer updatedCustomer = customerDomainService.updateCustomer(
                existingCustomer,
                request.getCompanyName(),
                request.getAddress(),
                request.getCountry()
        );

        // 3. Update customer's contact
        Contact newContact = customerDomainService.updateContact(
                existingCustomer.getContact(),
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getPosition()
        );
        updatedCustomer.setContact(newContact);

        // 4. Trigger update event
        existingCustomer.updateCustomer();

        // 5. Save customer
        return customerRepository.save(existingCustomer);
    }

    //    Get customer by id
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElse(null);
    }

    //    Get all customers from database
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
