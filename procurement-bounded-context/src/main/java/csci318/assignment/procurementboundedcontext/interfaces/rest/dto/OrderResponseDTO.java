package csci318.assignment.procurementboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Product;

public class OrderResponseDTO {
    @JsonProperty
    private String orderId;

    @JsonProperty
    private Integer quantity;

    @JsonProperty
    private String companyName;

    @JsonProperty
    private String address;

    @JsonProperty
    private String country;

    @JsonProperty
    private String productCategory;

    @JsonProperty
    private String name;

    @JsonProperty
    private Double price;

    public OrderResponseDTO(Order order, Customer supplier, Product product) {
        this.orderId = order.getOrderId().getOrderId();
        this.quantity = order.getQuantity();
        this.companyName = supplier.getCompanyName();
        this.address = supplier.getAddress();
        this.country = supplier.getCountry();
        this.productCategory = product.getProductCategory();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
