package csci318.assignment.productservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.productservice.model.ProductDetail;

public class ProductDetailResponseDTO {
    @JsonProperty
    private final String description;

    @JsonProperty
    private final String comment;

    public ProductDetailResponseDTO(ProductDetail productDetail) {
        this.description = productDetail.getDescription();
        this.comment = productDetail.getComment();
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }
}
