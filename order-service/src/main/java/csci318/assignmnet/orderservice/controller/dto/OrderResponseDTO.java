package csci318.assignmnet.orderservice.controller.dto;

import csci318.assignmnet.orderservice.model.Order;

public class OrderResponseDTO {
    private Long supplier;
    private Long product;
    private Integer quantity;

    public OrderResponseDTO(Order order) {
        this.supplier = order.getSupplier();
        this.product = order.getProduct();
        this.quantity = order.getQuantity();
    }
}
