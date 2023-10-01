package csci318.assignment.procurementboundedcontext.infrastructure.repositories;

import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(OrderId orderId);

    List<OrderId> findAllOrderIds();

    List<Order> findAll();
}
