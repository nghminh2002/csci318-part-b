package csci318.assignmnet.orderservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddOrderToProductDTO {
    @JsonProperty
    private Long orderId;

    public AddOrderToProductDTO(Long orderId) {
        this.orderId = orderId;
    }
}
