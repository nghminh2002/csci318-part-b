package csci318.assignment.customerservice.repository;

import csci318.assignment.customerservice.model.event.CustomerEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEventRepository extends JpaRepository<CustomerEvent, Long> {
}
