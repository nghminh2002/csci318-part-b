package csci318.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestDTO {
    @JsonProperty
    private Long supplier;

    @JsonProperty
    private Long product;

    @JsonProperty
    private Integer quantity;

    public OrderRequestDTO(Long supplier, Long product, Integer quantity) {
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }
}
