package csci318.assignment.productservice.model;

import csci318.assignment.productservice.model.event.ProductEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends AbstractAggregateRoot<Product> {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String productCategory;

    @Column
    private String name;

    @Column
    private Double price;

    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "productDetailId")
    private ProductDetail productDetail;

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass=Long.class)
    private List<Long> createdOrders = new ArrayList<>();

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

    public List<Long> getCreatedOrders() {
        return createdOrders;
    }

    public void addCreatedOrders(Long orderId) {
        this.createdOrders.add(orderId);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", productCategory='" + productCategory + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productDetail=" + productDetail.toString() +
                '}';
    }

    public void createProduct() {
        ProductEvent event = new ProductEvent();
        event.setEventName("Create");
        event.setProductCategory(this.productCategory);
        event.setProductId(this.id);
        event.setName(this.name);
        registerEvent(event);
    }

    public void updateProduct() {
        ProductEvent event = new ProductEvent();
        event.setEventName("Update");
        event.setProductCategory(this.productCategory);
        event.setProductId(this.id);
        event.setName(this.name);
        registerEvent(event);
    }

    public void addProductToOrder(Long orderId) {
        this.createdOrders.add(orderId);
        ProductEvent event = new ProductEvent();
        event.setEventName("Order");
        event.setProductCategory(this.productCategory);
        event.setProductId(this.id);
        event.setName(this.name);
        registerEvent(event);
    }
}
