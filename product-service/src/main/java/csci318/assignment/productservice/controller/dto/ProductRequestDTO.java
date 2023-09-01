package csci318.assignment.productservice.controller.dto;

public class ProductRequestDTO {
    private String productCategory;
    private String name;
    private Double price;
    private String description;
    private String comment;

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
}
