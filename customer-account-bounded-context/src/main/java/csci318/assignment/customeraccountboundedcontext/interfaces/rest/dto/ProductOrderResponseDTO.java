package csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;

public class ProductOrderResponseDTO {
    @JsonProperty
    private String orderId;

    @JsonProperty
    private Customer supplier;

    @JsonProperty
    private Integer quantity;
}
