package csci318.assignment.procurementboundedcontext.application.internal.outboundservices;

import csci318.assignment.procurementboundedcontext.infrastructure.brokers.OrderEventSource;
import csci318.assignment.shareddomain.events.OrderCreatedEvent;
import csci318.assignment.shareddomain.events.OrderUpdatedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(OrderEventSource.class)
public class OrderEventPublisherService {
    OrderEventSource orderEventSource;

    public OrderEventPublisherService(OrderEventSource orderEventSource) {
        this.orderEventSource = orderEventSource;
    }

    @TransactionalEventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent){
        orderEventSource
                .orderCreating()
                .send(MessageBuilder.withPayload(orderCreatedEvent).build());
    }

    @TransactionalEventListener
    public void handleOrderUpdatedEvent(OrderUpdatedEvent orderUpdatedEvent){
        orderEventSource
                .orderUpdating()
                .send(MessageBuilder.withPayload(orderUpdatedEvent).build());
    }
}
