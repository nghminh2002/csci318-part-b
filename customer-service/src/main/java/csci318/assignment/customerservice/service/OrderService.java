package csci318.assignment.customerservice.service;

import csci318.assignment.customerservice.model.Order;

public interface OrderService {
    Order createOrder(Order newOrder);
    Order updateOrder(Long orderId, Order updatedOrder);
}
