package csci318.assignment.procurementboundedcontext.application.internal.queryservices;

import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.OrderId;
import csci318.assignment.procurementboundedcontext.infrastructure.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {
    private final OrderRepository orderRepository;

    public OrderQueryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<OrderId> findByOrderIds() {
        return orderRepository.findAllOrderIds();
    }

    public Order findByOrderId(OrderId orderId){
        return orderRepository.findByOrderId(orderId);
    }

}
