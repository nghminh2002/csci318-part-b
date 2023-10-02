package csci318.assignment.salesboundedcontext.infrastructure.repositories;

import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
