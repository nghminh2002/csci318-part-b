package csci318.assignment.customeraccountboundedcontext.interfaces.rest.transform;

import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;
import csci318.assignment.customeraccountboundedcontext.domain.model.commands.CreateCustomerCommand;
import csci318.assignment.customeraccountboundedcontext.domain.model.commands.UpdateCustomerCommand;
import csci318.assignment.customeraccountboundedcontext.domain.model.valueobject.Contact;
import csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto.CustomerRequestDTO;

public class CustomerCommandDTOAssembler {

    public static CreateCustomerCommand toCommandFromDTO(CustomerRequestDTO request) {
        Contact contact = new Contact(
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getPosition()
        );
        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand();
        createCustomerCommand.setCompanyName(request.getCompanyName());
        createCustomerCommand.setAddress(request.getAddress());
        createCustomerCommand.setCountry(request.getCountry());
        createCustomerCommand.setContact(contact);
        return createCustomerCommand;
    }

    public static UpdateCustomerCommand toCommandFromDTO(
            Customer originalCustomer,
            CustomerRequestDTO request
    ) {
        UpdateCustomerCommand updateCustomerCommand = new UpdateCustomerCommand(
                originalCustomer.getId()
        );
        // 1. Check if the company name needs to be updated
        // If yes, replace old company with the new company name
        if (request.getCompanyName() != null) {
            updateCustomerCommand.setCompanyName(request.getCompanyName());
        } else {
            updateCustomerCommand.setCompanyName(originalCustomer.getCompanyName());
        }

        // 2. Check if the address needs to be updated
        // If yes, replace old address with the new address
        if (request.getAddress() != null) {
            updateCustomerCommand.setAddress(request.getAddress());
        } else {
            updateCustomerCommand.setAddress(originalCustomer.getAddress());
        }

        // 3. Check if the country needs to be updated
        // If yes, replace old country with the new country
        if (request.getCountry() != null) {
            updateCustomerCommand.setCountry(request.getCountry());
        } else {
            updateCustomerCommand.setCountry(originalCustomer.getCountry());
        }

        return updateCustomerCommand;
    }
}
