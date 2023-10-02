package csci318.assignment.procurementboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;

public class OrderCustomerResponseDTO {
    @JsonProperty
    private final String orderId;

    @JsonProperty
    private final Customer supplier;

    @JsonProperty
    private final Integer quantity;

    public OrderCustomerResponseDTO(Order order, Customer supplier) {
        this.orderId = order.getOrderId().getOrderId();
        this.supplier = supplier;
        this.quantity = order.getQuantity();
    }
}
