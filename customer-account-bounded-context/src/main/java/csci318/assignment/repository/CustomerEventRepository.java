package csci318.assignment.repository;

import csci318.assignment.model.event.CustomerEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEventRepository extends JpaRepository<CustomerEvent, Long> {
}
