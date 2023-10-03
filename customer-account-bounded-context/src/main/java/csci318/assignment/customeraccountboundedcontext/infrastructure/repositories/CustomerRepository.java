package csci318.assignment.customeraccountboundedcontext.infrastructure.repositories;

import csci318.assignment.customeraccountboundedcontext.domain.model.aggregates.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
