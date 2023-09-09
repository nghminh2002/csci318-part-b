package csci318.assignment.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.model.Product;
import csci318.assignment.model.valueobject.ProductDetail;

public class ProductResponseDTO {
    @JsonProperty
    private final Long productId;

    @JsonProperty
    private final String productCategory;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final Double price;

    @JsonProperty
    private final String description;

    @JsonProperty
    private final String comment;

    public ProductResponseDTO(Product product) {
        this.productId = product.getId();
        this.productCategory = product.getProductCategory();
        this.name = product.getName();
        this.price = product.getPrice();

        ProductDetail productDetail = product.getProductDetail();
        this.description = productDetail.getDescription();
        this.comment = productDetail.getComment();
    }
}