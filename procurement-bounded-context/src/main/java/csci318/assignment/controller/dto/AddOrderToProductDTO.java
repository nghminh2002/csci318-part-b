package csci318.assignment.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddOrderToProductDTO {
    @JsonProperty
    private Long orderId;

    public AddOrderToProductDTO(Long orderId) {
        this.orderId = orderId;
    }
}
