package csci318.assignmnet.orderservice.service;

import csci318.assignmnet.orderservice.model.Order;

public interface OrderService {
    Order createOrder(Order newOrder);
    Order updateOrder(Order updatedOrder);
    Order getOrderById(Long orderId);
}
