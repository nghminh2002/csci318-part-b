package csci318.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponseDTO {
    @JsonProperty
    private Long productId;

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
    private Integer numberOfCreatedOrders;

    public Long getProductId() {
        return productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getName() {
        return name;
    }
}

