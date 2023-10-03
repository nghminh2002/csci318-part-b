package csci318.assignment.salesboundedcontext.application.internal.outboundservices;

import csci318.assignment.salesboundedcontext.infrastructure.brokers.ProductEventSource;
import csci318.assignment.shareddomain.events.ProductCreatedEvent;
import csci318.assignment.shareddomain.events.ProductUpdatedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(ProductEventSource.class)
public class ProductEventPublisherService {
    ProductEventSource productEventSource;

    public ProductEventPublisherService(ProductEventSource productEventSource) {
        this.productEventSource = productEventSource;
    }

    @TransactionalEventListener
    public void handleProductCreatedEvent(ProductCreatedEvent productCreatedEvent){
        productEventSource
                .productCreating()
                .send(MessageBuilder.withPayload(productCreatedEvent).build());
    }

    @TransactionalEventListener
    public void handleOrderUpdatedEvent(ProductUpdatedEvent productUpdatedEvent){
        productEventSource
                .productUpdating()
                .send(MessageBuilder.withPayload(productUpdatedEvent).build());
    }
}
