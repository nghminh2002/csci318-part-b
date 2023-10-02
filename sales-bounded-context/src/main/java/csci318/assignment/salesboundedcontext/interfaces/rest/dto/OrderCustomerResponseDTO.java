package csci318.assignment.salesboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.salesboundedcontext.domain.model.entities.Customer;

public class OrderCustomerResponseDTO {
    @JsonProperty
    private Long orderId;

    @JsonProperty
    private Customer supplier;

    @JsonProperty
    private Integer quantity;
}
