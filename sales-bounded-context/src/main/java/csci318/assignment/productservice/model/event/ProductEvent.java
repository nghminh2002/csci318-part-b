package csci318.assignment.productservice.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductEvent {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String eventName;

    @Column
    private Long productId;

    @Column
    private String productCategory;

    @Column
    private String name;

    public ProductEvent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", productId=" + productId +
                ", productCategory='" + productCategory + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
