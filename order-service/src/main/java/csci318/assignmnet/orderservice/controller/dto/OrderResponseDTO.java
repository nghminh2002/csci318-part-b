package csci318.assignmnet.orderservice.controller.dto;

import csci318.assignmnet.orderservice.model.Contact;
import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Order;
import csci318.assignmnet.orderservice.model.Product;
import csci318.assignmnet.orderservice.model.ProductDetail;

public class OrderResponseDTO {
    private final Long supplier;
    private final Long product;
    private final Integer quantity;
    private final String companyName;
    private final String address;
    private final String country;
    private final String contactName;
    private final String contactPhone;
    private final String contactEmail;
    private final String contactPosition;
    private final String productCategory;
    private final String name;
    private final Double price;
    private final String description;
    private final String comment;

    public OrderResponseDTO(Order order, Customer customer, Product product) {
        this.supplier = order.getSupplier();
        this.product = order.getProduct();
        this.quantity = order.getQuantity();

        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.country = customer.getCountry();
        Contact contact = customer.getContact();
        this.contactName = contact.getName();
        this.contactPhone = contact.getPhone();
        this.contactEmail = contact.getEmail();
        this.contactPosition = contact.getPosition();

        this.productCategory = product.getProductCategory();
        this.name = product.getName();
        this.price = product.getPrice();

        ProductDetail productDetail = product.getProductDetail();
        this.description = productDetail.getDescription();
        this.comment = productDetail.getComment();
    }
}
