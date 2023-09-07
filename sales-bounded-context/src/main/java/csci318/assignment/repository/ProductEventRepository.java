package csci318.assignment.repository;

import csci318.assignment.model.event.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {
}
