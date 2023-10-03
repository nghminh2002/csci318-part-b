package csci318.assignment.salesboundedcontext.domain.model.commands;

import csci318.assignment.salesboundedcontext.domain.model.valueobject.ProductDetail;

public class CreateProductCommand {
    private String productCategory;
    private String name;
    private Double price;
    private ProductDetail productDetail;

    public CreateProductCommand() {}

    public CreateProductCommand(
            String productCategory,
            String name,
            Double price,
            ProductDetail productDetail
    ) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
        this.productDetail = productDetail;
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
