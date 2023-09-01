package csci318.assignment.customerservice.controller.dto;

public class CustomerRequestDTO {
    private String companyName;
    private String address;
    private String country;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String contactPosition;

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPosition() {
        return contactPosition;
    }
}
