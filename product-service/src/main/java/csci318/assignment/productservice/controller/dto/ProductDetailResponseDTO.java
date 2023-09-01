package csci318.assignment.productservice.controller.dto;

import csci318.assignment.productservice.model.ProductDetail;

public class ProductDetailResponseDTO {
    private final String description;
    private final String comment;

    public ProductDetailResponseDTO(ProductDetail productDetail) {
        this.description = productDetail.getDescription();
        this.comment = productDetail.getComment();
    }
}
