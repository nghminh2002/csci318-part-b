package csci318.assignment.salesboundedcontext.domain.model.aggregates;

import csci318.assignment.salesboundedcontext.domain.model.commands.CreateProductCommand;
import csci318.assignment.salesboundedcontext.domain.model.commands.UpdateProductCommand;
import csci318.assignment.salesboundedcontext.domain.model.valueobject.ProductDetail;
import csci318.assignment.shareddomain.events.ProductCreatedEvent;
import csci318.assignment.shareddomain.events.ProductCreatedEventData;
import csci318.assignment.shareddomain.events.ProductUpdatedEvent;
import csci318.assignment.shareddomain.events.ProductUpdatedEventData;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends AbstractAggregateRoot<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productCategory;

    @Column
    private String name;

    @Column
    private Double price;

    @Embedded
    private ProductDetail productDetail;

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass=String.class)
    private List<String> createdOrders = new ArrayList<>();

    public Product() {}

    public Product(CreateProductCommand createProductCommand) {
        this.productCategory = createProductCommand.getProductCategory();
        this.name = createProductCommand.getName();
        this.price = createProductCommand.getPrice();
        this.productDetail = createProductCommand.getProductDetail();
        addDomainEvent(new ProductCreatedEvent(
                new ProductCreatedEventData(this.id, this.productCategory, this.name)
        ));
    }

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

    public List<String> getCreatedOrders() {
        return createdOrders;
    }

    public void addCreatedOrders(String orderId) {
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

    public void updateProduct(UpdateProductCommand updateProductCommand) {
        this.productCategory = updateProductCommand.getProductCategory();
        this.name = updateProductCommand.getName();
        this.price = updateProductCommand.getPrice();
        this.productDetail = updateProductCommand.getProductDetail();
        addDomainEvent(new ProductUpdatedEvent(
                new ProductUpdatedEventData(this.id, this.productCategory, this.name)
        ));
    }

    public void addDomainEvent(Object event){
        registerEvent(event);
    }
}
