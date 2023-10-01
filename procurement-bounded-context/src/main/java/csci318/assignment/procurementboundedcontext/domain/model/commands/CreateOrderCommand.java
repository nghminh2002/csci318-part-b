package csci318.assignment.procurementboundedcontext.domain.model.commands;

import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Product;

public class CreateOrderCommand {
    private String orderId;
    private Integer orderQuantity;
    private Long supplierId;
    private Long productId;
    private Customer supplier;
    private Product product;

    public CreateOrderCommand() {}

    public CreateOrderCommand(Integer orderQuantity, Long supplierId, Long productId) {
        this.orderQuantity = orderQuantity;
        this.supplierId = supplierId;
        this.productId = productId;
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
}
