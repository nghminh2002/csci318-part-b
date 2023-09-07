package csci318.assignment.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.model.Customer;
import csci318.assignment.model.Order;
import csci318.assignment.model.Product;

public class OrderResponseDTO {
    @JsonProperty
    private final Long orderId;

    @JsonProperty
    private final Long supplier;

    @JsonProperty
    private final Long product;

    @JsonProperty
    private final Integer quantity;

    @JsonProperty
    private final String companyName;

    @JsonProperty
    private final String address;

    @JsonProperty
    private final String country;

    @JsonProperty
    private final String productCategory;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final Double price;

    public OrderResponseDTO(Order order, Customer customer, Product product) {
        this.orderId = order.getId();
        this.supplier = order.getSupplier();
        this.product = order.getProduct();
        this.quantity = order.getQuantity();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();
        this.productCategory = product.getProductCategory();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
