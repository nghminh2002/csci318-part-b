package csci318.assignment.controller.dto;

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

    public String getProductCategory() {
        return productCategory;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }
}
