package csci318.assignmnet.orderservice.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderEvent {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String eventName;

    @Column
    private Long orderId;

    @Column
    private Long supplierId;

    @Column
    private Long productId;

    public OrderEvent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", orderId=" + orderId +
                ", supplierId=" + supplierId +
                ", productId=" + productId +
                '}';
    }
}
