package csci318.assignment.customeraccountboundedcontext.domain.model.entities;

import csci318.assignment.customeraccountboundedcontext.domain.model.valueobject.ProductDetail;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String productCategory;

    @Column
    private String name;

    @Column
    private Double price;

    @Embedded
    private ProductDetail productDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
