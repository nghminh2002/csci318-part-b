package csci318.assignment.dto;

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

    public CustomerRequestDTO(String companyName, String address, String country, String name, String phone, String email, String position) {
        this.companyName = companyName;
        this.address = address;
        this.country = country;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }
}

