package csci318.assignmnet.orderservice.model.valueobject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProductDetail {
    private String description;
    private String comment;

    public ProductDetail() {}

    public ProductDetail(String description, String comment) {
        this.description = description;
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetail productDetail = (ProductDetail) o;
        return Objects.equals(description, productDetail.description) &&
                Objects.equals(comment, productDetail.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, comment);
    }

    @Override
    public String toString() {
        return "{" +
                "description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
