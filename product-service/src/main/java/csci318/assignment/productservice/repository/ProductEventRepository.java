package csci318.assignment.productservice.repository;

import csci318.assignment.productservice.model.event.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {
}
