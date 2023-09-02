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

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
