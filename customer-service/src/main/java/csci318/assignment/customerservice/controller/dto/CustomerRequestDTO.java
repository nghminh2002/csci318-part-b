package csci318.assignment.customerservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRequestDTO {
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

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }
}
