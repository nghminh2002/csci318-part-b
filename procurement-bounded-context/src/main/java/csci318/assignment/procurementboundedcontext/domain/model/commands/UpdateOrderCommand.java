package csci318.assignment.procurementboundedcontext.domain.model.commands;

public class UpdateOrderCommand {
    private String orderId;
    private Integer orderQuantity;

    public UpdateOrderCommand() {}

    public UpdateOrderCommand(String orderId, Integer orderQuantity) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
