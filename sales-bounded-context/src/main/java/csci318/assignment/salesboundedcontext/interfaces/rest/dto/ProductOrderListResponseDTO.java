package csci318.assignment.salesboundedcontext.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import csci318.assignment.salesboundedcontext.domain.model.valueobject.ProductDetail;

import java.util.List;

public class ProductOrderListResponseDTO {
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

    @JsonProperty
    private final List<OrderCustomerResponseDTO> orderList;

    public ProductOrderListResponseDTO(Product product, List<OrderCustomerResponseDTO> orderList) {
        this.productId = product.getId();
        this.productCategory = product.getProductCategory();
        this.name = product.getName();
        this.price = product.getPrice();

        ProductDetail productDetail = product.getProductDetail();
        this.description = productDetail.getDescription();
        this.comment = productDetail.getComment();

        this.orderList = orderList;
    }
}
