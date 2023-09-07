package csci318.assignment.productservice.service;

import csci318.assignment.productservice.model.event.ProductEvent;
import csci318.assignment.productservice.repository.ProductEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final ProductEventRepository productEventRepository;

    EventHandler(ProductEventRepository productEventRepository) {
        this.productEventRepository = productEventRepository;
    }

    @TransactionalEventListener
    public void handleCreateEvent(ProductEvent productEvent){
        productEventRepository.save(productEvent);
        System.out.println(productEvent);
    }

    @EventListener
    public void handleUpdateEvent(ProductEvent productEvent) {
        productEventRepository.save(productEvent);
        System.out.println(productEvent);
    }
}

