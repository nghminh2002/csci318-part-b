package csci318.assignment.procurementboundedcontext.domain.model.commands;

public class UpdateOrderCommand {
    private final String orderId;
    private final Integer orderQuantity;

    public UpdateOrderCommand(String orderId, Integer orderQuantity) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }
}
