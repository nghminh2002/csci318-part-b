package csci318.assignment.dto;

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

    public ProductRequestDTO(String productCategory, String name, Double price, String description, String comment) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
        this.description = description;
        this.comment = comment;
    }
}

