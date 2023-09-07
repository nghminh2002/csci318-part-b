package csci318.assignmnet.orderservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestDTO {
    @JsonProperty
    private Long supplier;

    @JsonProperty
    private Long product;

    @JsonProperty
    private Integer quantity;

    public Long getSupplier() {
        return supplier;
    }

    public Long getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
