package csci318.assignment.procurementboundedcontext.application.internal.commandservices;

import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.OrderId;
import csci318.assignment.procurementboundedcontext.domain.model.commands.CreateOrderCommand;
import csci318.assignment.procurementboundedcontext.domain.model.commands.UpdateOrderCommand;
import csci318.assignment.procurementboundedcontext.infrastructure.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderCommandService {
    private final OrderRepository orderRepository;

    public OrderCommandService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(CreateOrderCommand createOrderCommand) {
        String random = UUID.randomUUID().toString().toUpperCase();
        String orderIdStr = random.substring(0, random.indexOf("-"));
        createOrderCommand.setOrderId(orderIdStr);
        Order newOrder = orderRepository.save(new Order(createOrderCommand));
        return newOrder;
    }

    public Order updateOrder(UpdateOrderCommand updateOrderCommand) {
        Order order = orderRepository.findByOrderId(new OrderId(updateOrderCommand.getOrderId()));
        order.updateOrder(updateOrderCommand);
        return orderRepository.save(order);
    }
}
