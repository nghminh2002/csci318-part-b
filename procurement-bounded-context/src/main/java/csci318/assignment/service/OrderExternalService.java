package csci318.assignment.service;

import csci318.assignment.model.Customer;
import csci318.assignment.model.Product;

public interface OrderExternalService {
    Customer getOrderSupplier(Long supplierId);
    Product getOrderProduct(Long productId);
    void addOrderToProduct(Long productId, Long orderId);
}
