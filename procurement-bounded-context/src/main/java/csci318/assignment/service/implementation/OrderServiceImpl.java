package csci318.assignment.service.implementation;

import csci318.assignment.model.Order;
import csci318.assignment.model.event.OrderEvent;
import csci318.assignment.repository.OrderRepository;
import csci318.assignment.service.OrderService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderServiceImpl(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // Save new order to the database
    @Override
    public Order createOrder(Order newOrder) {
        Order order = orderRepository.save(newOrder);
        OrderEvent event = new OrderEvent();
        event.setEventName("Create");
        event.setOrderId(order.getId());
        event.setProductId(order.getProduct());
        event.setSupplierId(order.getSupplier());
        applicationEventPublisher.publishEvent(event);
        return order;
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        updatedOrder.updateOrder();
        return orderRepository.save(updatedOrder);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(RuntimeException::new);
    }
}
