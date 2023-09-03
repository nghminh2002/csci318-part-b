package csci318.assignmnet.orderservice.model;

import csci318.assignmnet.orderservice.model.event.OrderEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_order")
public class Order extends AbstractAggregateRoot<Order> {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long supplier;

    @Column
    private Long product;

    @Column
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", supplier=" + supplier +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public void updateOrder() {
        OrderEvent event = new OrderEvent();
        event.setEventName("Update");
        event.setOrderId(this.id);
        event.setProductId(this.product);
        event.setSupplierId(this.supplier);
        registerEvent(event);
    }
}
