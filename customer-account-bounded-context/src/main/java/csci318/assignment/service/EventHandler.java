package csci318.assignment.service;

import csci318.assignment.model.event.CustomerEvent;
import csci318.assignment.repository.CustomerEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final CustomerEventRepository customerEventRepository;

    EventHandler(CustomerEventRepository customerEventRepository) {
        this.customerEventRepository = customerEventRepository;
    }

    @TransactionalEventListener
    public void handleCreateEvent(CustomerEvent customerEvent){
        customerEventRepository.save(customerEvent);
        System.out.println(customerEvent);
    }

    @EventListener
    public void handleUpdateEvent(CustomerEvent customerEvent) {
        customerEventRepository.save(customerEvent);
        System.out.println(customerEvent);
    }
}

