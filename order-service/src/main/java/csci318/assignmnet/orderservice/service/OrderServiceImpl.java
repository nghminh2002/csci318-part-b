package csci318.assignmnet.orderservice.service;

import csci318.assignmnet.orderservice.model.Order;
import csci318.assignmnet.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Save new order to the database
    @Override
    public Order createOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        return orderRepository.save(updatedOrder);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(RuntimeException::new);
    }
}
