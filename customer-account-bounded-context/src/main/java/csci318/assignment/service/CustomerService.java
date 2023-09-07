package csci318.assignment.service;

import csci318.assignment.model.Customer;

import java.util.List;

public interface CustomerService {
    //Operations for customer
    Customer createCustomer(Customer newCustomer);
    Customer updateCustomer(Customer updateCustomer);
    Customer getCustomerById(Long customerId);
    List<Customer> getAllCustomers();
}
