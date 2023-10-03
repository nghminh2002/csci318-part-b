package csci318.assignment.procurementboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Product;

public class OrderProductResponseDTO {
    @JsonProperty
    private final String orderId;

    @JsonProperty
    private final Product product;

    @JsonProperty
    private final Integer quantity;

    public OrderProductResponseDTO(Order order, Product product) {
        this.orderId = order.getOrderId().getOrderId();
        this.product = product;
        this.quantity = order.getQuantity();
    }
}
