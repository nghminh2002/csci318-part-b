package csci318.assignment.procurementboundedcontext.domain.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderId implements Serializable {

    @Column(name="order_id")
    private String orderId;

    public OrderId(){}

    public OrderId(String orderId){this.orderId = orderId;}

    public String getOrderId(){return this.orderId;}
}
