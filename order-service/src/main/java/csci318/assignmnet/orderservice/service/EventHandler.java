package csci318.assignmnet.orderservice.service;

import csci318.assignmnet.orderservice.model.event.OrderEvent;
import csci318.assignmnet.orderservice.repository.OrderEventRepository;
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

