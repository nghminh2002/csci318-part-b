package csci318.assignment.salesboundedcontext.domain.model.commands;

import csci318.assignment.salesboundedcontext.domain.model.valueobject.ProductDetail;

public class UpdateProductCommand {
    private Long productId;
    private String productCategory;
    private String name;
    private Double price;
    private ProductDetail productDetail;

    public UpdateProductCommand() {}

    public UpdateProductCommand(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

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

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
}
