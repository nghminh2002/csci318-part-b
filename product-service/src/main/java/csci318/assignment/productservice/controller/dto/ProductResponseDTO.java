package csci318.assignment.productservice.controller.dto;

import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.ProductDetail;

public class ProductResponseDTO {
    private final String productCategory;
    private final String name;
    private final Double price;
    private final String description;
    private final String comment;

    public ProductResponseDTO(Product product) {
        this.productCategory = product.getProductCategory();
        this.name = product.getName();
        this.price = product.getPrice();

        ProductDetail productDetail = product.getProductDetail();
        this.description = productDetail.getDescription();
        this.comment = productDetail.getComment();
    }
}
