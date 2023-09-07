package csci318.assignment.service;

import csci318.assignment.model.Order;

public interface OrderService {
    Order createOrder(Order newOrder);
    Order updateOrder(Order updatedOrder);
    Order getOrderById(Long orderId);
}
