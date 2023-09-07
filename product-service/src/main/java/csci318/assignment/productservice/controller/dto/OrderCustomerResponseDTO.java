package csci318.assignment.productservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.productservice.model.Customer;

public class OrderCustomerResponseDTO {
    @JsonProperty
    private Long orderId;

    @JsonProperty
    private Customer supplier;

    @JsonProperty
    private Integer quantity;
}
