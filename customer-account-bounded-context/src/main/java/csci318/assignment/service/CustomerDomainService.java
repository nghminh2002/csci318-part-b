package csci318.assignment.service;

import csci318.assignment.controller.dto.CustomerRequestDTO;
import csci318.assignment.model.Customer;
import csci318.assignment.model.event.CustomerEvent;
import csci318.assignment.model.valueobject.Contact;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomerDomainService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomerDomainService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Customer createFromDTO(CustomerRequestDTO request) {
        Contact contact = new Contact(
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getPosition()
        );

        Customer customer = new Customer();
        customer.setCompanyName(request.getCompanyName());
        customer.setAddress(request.getAddress());
        customer.setCountry(request.getCountry());
        customer.setContact(contact);

        return customer;
    }

    public void emitCustomerCreationEvent(Customer savedCustomer) {
        CustomerEvent event = new CustomerEvent();
        event.setEventName("Create");
        event.setCustomerId(savedCustomer.getId());
        event.setCompanyName(savedCustomer.getCompanyName());
        event.setAddress(savedCustomer.getAddress());
        event.setCountry(savedCustomer.getCountry());
        applicationEventPublisher.publishEvent(event);
    }

    public  Customer updateCustomer(Customer originalCustomer, String companyName, String address, String country) {
        // 1. Check if the company name needs to be updated
        // If yes, replace old company with the new company name
        if (companyName != null) {
            originalCustomer.setCompanyName(companyName);
        }

        // 2. Check if the address needs to be updated
        // If yes, replace old address with the new address
        if (address != null) {
            originalCustomer.setAddress(address);
        }

        // 3. Check if the country needs to be updated
        // If yes, replace old country with the new country
        if (country != null) {
            originalCustomer.setCountry(country);
        }

        return originalCustomer;
    }

    public Contact updateContact(Contact originalContact, String name, String phone, String email, String position) {
        if (name != null || phone != null || email != null || position != null) {
            String newName = name != null ? name : originalContact.getName();
            String newPhone = phone != null ? phone : originalContact.getPhone();
            String newEmail = email != null ? email : originalContact.getEmail();
            String newPosition = position != null ? position : originalContact.getPosition();
            return new Contact(newName, newPhone, newEmail, newPosition);
        }
        return originalContact;
    }
}
