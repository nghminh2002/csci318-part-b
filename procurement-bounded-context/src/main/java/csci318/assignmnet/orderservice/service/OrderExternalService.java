package csci318.assignmnet.orderservice.service;

import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Product;

public interface OrderExternalService {
    Customer getOrderSupplier(Long supplierId);
    Product getOrderProduct(Long productId);
    void addOrderToProduct(Long productId, Long orderId);
}
