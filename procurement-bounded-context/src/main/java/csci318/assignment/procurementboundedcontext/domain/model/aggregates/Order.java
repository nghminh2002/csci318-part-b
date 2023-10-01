package csci318.assignment.procurementboundedcontext.domain.model.aggregates;

import csci318.assignment.procurementboundedcontext.domain.model.commands.CreateOrderCommand;
import csci318.assignment.procurementboundedcontext.domain.model.commands.UpdateOrderCommand;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Product;
import csci318.assignment.shareddomain.events.OrderCreatedEvent;
import csci318.assignment.shareddomain.events.OrderCreatedEventData;
import csci318.assignment.shareddomain.events.OrderUpdatedEvent;
import csci318.assignment.shareddomain.events.OrderUpdatedEventData;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
@Table(name = "customer_order")
@NamedQueries({
        @NamedQuery(name = "Order.findAll",
                query = "Select o from Order o"),
        @NamedQuery(name = "Order.findByOrderId",
                query = "Select o from Order o where o.orderId = ?1"),
        @NamedQuery(name = "Order.findAllOrderIds",
                query = "Select o.orderId from Order  o") })
public class Order extends AbstractAggregateRoot<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private OrderId orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer supplier;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Column
    private Integer quantity;

    public Order() {}

    public Order(CreateOrderCommand createOrderCommand) {
        this.orderId = new OrderId(createOrderCommand.getOrderId());
        this.supplier = createOrderCommand.getSupplier();
        this.product = createOrderCommand.getProduct();

        addDomainEvent(new OrderCreatedEvent(new OrderCreatedEventData(orderId.getOrderId(),
                        supplier.getCompanyName(),
                        product.getName(),
                        createOrderCommand.getOrderQuantity())));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public Customer getSupplier() {
        return supplier;
    }

    public void setSupplier(Customer supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    public void updateOrder(UpdateOrderCommand updateOrderCommand) {
        this.quantity = updateOrderCommand.getOrderQuantity();
        addDomainEvent(new OrderUpdatedEvent(new OrderUpdatedEventData
                (this.orderId.getOrderId())));
    }

    public void addDomainEvent(Object event){
        registerEvent(event);
    }
}
