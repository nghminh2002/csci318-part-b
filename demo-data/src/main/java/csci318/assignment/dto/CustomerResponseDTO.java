package csci318.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerResponseDTO {
    @JsonProperty
    private Long customerId;

    @JsonProperty
    private String companyName;

    @JsonProperty
    private String address;

    @JsonProperty
    private String country;

    @JsonProperty
    private String name;

    @JsonProperty
    private String phone;

    @JsonProperty
    private String email;

    @JsonProperty
    private String position;

    @JsonProperty
    private Integer numberOfCreatedOrders;

    public Long getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }
}

