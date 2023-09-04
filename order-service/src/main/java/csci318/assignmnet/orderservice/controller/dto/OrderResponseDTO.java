package csci318.assignmnet.orderservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Order;
import csci318.assignmnet.orderservice.model.Product;

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

    public Long getOrderId() {
        return orderId;
    }

    public Long getSupplier() {
        return supplier;
    }

    public Long getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
