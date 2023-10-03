package csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;
import csci318.assignment.customeraccountboundedcontext.domain.model.valueobject.Contact;

import java.util.List;

public class CustomerOrderHistoryDTO {
    @JsonProperty
    private final Long customerId;

    @JsonProperty
    private final String companyName;

    @JsonProperty
    private final String address;

    @JsonProperty
    private final String country;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String phone;

    @JsonProperty
    private final String email;

    @JsonProperty
    private final String position;

    @JsonProperty
    private final List<ProductOrderResponseDTO> orderList;

    public CustomerOrderHistoryDTO(Customer customer, List<ProductOrderResponseDTO> orderList) {
        this.customerId = customer.getId();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();

        Contact contact = customer.getContact();
        this.name = contact.getName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.position = contact.getPosition();

        this.orderList = orderList;
    }
}
