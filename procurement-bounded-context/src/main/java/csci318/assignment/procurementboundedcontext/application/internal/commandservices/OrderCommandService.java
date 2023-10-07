package csci318.assignment.procurementboundedcontext.application.internal.commandservices;

import csci318.assignment.procurementboundedcontext.application.internal.domainservices.OrderDomainService;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.OrderId;
import csci318.assignment.procurementboundedcontext.domain.model.commands.CreateOrderCommand;
import csci318.assignment.procurementboundedcontext.domain.model.commands.UpdateOrderCommand;
import csci318.assignment.procurementboundedcontext.infrastructure.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandService {
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;

    public OrderCommandService(OrderRepository orderRepository, OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.orderDomainService = orderDomainService;
    }

    public Order createOrder(CreateOrderCommand createOrderCommand) {
        String orderId = orderDomainService.generateOrderID(
                createOrderCommand.getSupplierId(),
                createOrderCommand.getProductId()
        );
        createOrderCommand.setOrderId(orderId);
        return orderRepository.save(new Order(createOrderCommand));
    }

    public Order updateOrder(UpdateOrderCommand updateOrderCommand) {
        Order order = orderRepository.findByOrderId(
                new OrderId(updateOrderCommand.getOrderId())
        );
        order.updateOrder(updateOrderCommand);
        return orderRepository.save(order);
    }
}
