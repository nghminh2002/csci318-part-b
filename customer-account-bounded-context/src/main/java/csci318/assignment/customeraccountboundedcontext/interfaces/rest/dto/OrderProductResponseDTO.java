package csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.customeraccountboundedcontext.domain.model.entities.Product;

public class OrderProductResponseDTO {
    @JsonProperty
    private String orderId;

    @JsonProperty
    private Product product;

    @JsonProperty
    private Integer quantity;
}
