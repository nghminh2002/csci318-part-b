package csci318.assignmnet.orderservice.repository;

import csci318.assignmnet.orderservice.model.event.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
