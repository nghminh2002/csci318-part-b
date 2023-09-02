package csci318.assignment.productservice.controller.dto;

public class ProductRequestDTO {
    private String productCategory;
    private String name;
    private Double price;
    private String description;
    private String comment;
    private Long orderId;

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

    public Long getOrderId() {
        return orderId;
    }
}
