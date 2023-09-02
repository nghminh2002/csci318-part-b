package csci318.assignment.productservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequestDTO {
    @JsonProperty
    private String productCategory;

    @JsonProperty
    private String name;

    @JsonProperty
    private Double price;

    @JsonProperty
    private String description;

    @JsonProperty
    private String comment;

    @JsonProperty
    private Long orderId;

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
