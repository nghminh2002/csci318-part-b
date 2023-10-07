package csci318.assignment.customeraccountboundedcontext.application.internal.domainservices;

import csci318.assignment.customeraccountboundedcontext.domain.model.valueobject.Contact;
import csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto.CustomerRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerDomainService {
    public Contact updateCustomerContact(Contact originalContact, CustomerRequestDTO request) {
        if (request.getName() != null ||
                request.getPhone() != null ||
                request.getEmail() != null ||
                request.getPosition() != null
        ) {
            String newName = request.getName() != null
                    ? request.getName()
                    : originalContact.getName();
            String newPhone = request.getPhone() != null
                    ? request.getPhone()
                    : originalContact.getPhone();
            String newEmail = request.getEmail() != null
                    ? request.getEmail()
                    : originalContact.getEmail();
            String newPosition = request.getPosition() != null
                    ? request.getPosition()
                    : originalContact.getPosition();
            return new Contact(newName, newPhone, newEmail, newPosition);
        } else {
            return originalContact;
        }
    }
}
