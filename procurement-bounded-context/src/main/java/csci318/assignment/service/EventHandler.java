package csci318.assignment.service;

import csci318.assignment.model.event.OrderEvent;
import csci318.assignment.repository.OrderEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final OrderEventRepository orderEventRepository;

    EventHandler(OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
    }

    @TransactionalEventListener
    public void handleCreateEvent(OrderEvent orderEvent){
        orderEventRepository.save(orderEvent);
        System.out.println(orderEvent);
    }

    @EventListener
    public void handleUpdateEvent(OrderEvent orderEvent) {
        orderEventRepository.save(orderEvent);
        System.out.println(orderEvent);
    }
}

