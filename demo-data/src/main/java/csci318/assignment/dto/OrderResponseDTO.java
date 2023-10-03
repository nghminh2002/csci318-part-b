package csci318.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponseDTO {
    @JsonProperty
    private String orderId;

    @JsonProperty
    private Integer quantity;

    @JsonProperty
    private String companyName;

    @JsonProperty
    private String address;

    @JsonProperty
    private String country;

    @JsonProperty
    private String productCategory;

    @JsonProperty
    private String name;

    @JsonProperty
    private Double price;

    public String getOrderId() {
        return orderId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getName() {
        return name;
    }
}
