package csci318.assignment.productservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetailRequestDTO {
    @JsonProperty
    private String description;

    @JsonProperty
    private String comment;

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
}
