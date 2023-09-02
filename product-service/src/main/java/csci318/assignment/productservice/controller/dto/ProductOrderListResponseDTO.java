package csci318.assignment.productservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.productservice.model.Order;
import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.ProductDetail;

import java.util.List;

public class ProductOrderListResponseDTO {
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

    @JsonProperty
    private final List<Order> orderList;

    public ProductOrderListResponseDTO(Product product, List<Order> orderList) {
        this.productCategory = product.getProductCategory();
        this.name = product.getName();
        this.price = product.getPrice();

        ProductDetail productDetail = product.getProductDetail();
        this.description = productDetail.getDescription();
        this.comment = productDetail.getComment();

        this.orderList = orderList;
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

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
