package csci318.assignment.procurementboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;

public class OrderCustomerResponseDTO {
    @JsonProperty
    private final Long orderId;

    @JsonProperty
    private final Customer supplier;

    @JsonProperty
    private final Integer quantity;

    public OrderCustomerResponseDTO(Order order) {
        this.orderId = order.getId();
        this.supplier = order.getSupplier();
        this.quantity = order.getQuantity();
    }
}
