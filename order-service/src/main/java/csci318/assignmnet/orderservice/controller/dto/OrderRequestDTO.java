package csci318.assignmnet.orderservice.controller.dto;

public class OrderRequestDTO {
    private Long supplier;
    private Long product;
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
