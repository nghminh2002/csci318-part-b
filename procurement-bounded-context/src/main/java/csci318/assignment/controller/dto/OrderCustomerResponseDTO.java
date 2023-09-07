package csci318.assignment.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.model.Customer;
import csci318.assignment.model.Order;

public class OrderCustomerResponseDTO {
    @JsonProperty
    private final Long orderId;

    @JsonProperty
    private final Customer supplier;

    @JsonProperty
    private final Integer quantity;

    public OrderCustomerResponseDTO(Order order, Customer supplier) {
        this.orderId = order.getId();
        this.supplier = supplier;
        this.quantity = order.getQuantity();
    }
}
