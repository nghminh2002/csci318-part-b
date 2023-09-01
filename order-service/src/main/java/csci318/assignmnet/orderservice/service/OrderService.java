package csci318.assignmnet.orderservice.service;

import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Order;
import csci318.assignmnet.orderservice.model.Product;

public interface OrderService {
    Order createOrder(Order newOrder);
    Order updateOrder(Order updatedOrder);
    Order getOrderById(Long orderId);
    Customer getOrderSupplier(Long supplierId);
    Product getOrderProduct(Long productId);
}
