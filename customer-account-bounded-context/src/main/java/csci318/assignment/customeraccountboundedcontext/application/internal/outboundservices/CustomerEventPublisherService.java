package csci318.assignment.customeraccountboundedcontext.application.internal.outboundservices;

import csci318.assignment.customeraccountboundedcontext.infrastructure.brokers.CustomerEventSource;
import csci318.assignment.sharreddomain.events.CustomerCreatedEvent;
import csci318.assignment.sharreddomain.events.CustomerUpdatedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(CustomerEventSource.class)
public class CustomerEventPublisherService {
    CustomerEventSource customerEventSource;

    public CustomerEventPublisherService(CustomerEventSource customerEventSource) {
        this.customerEventSource = customerEventSource;
    }

    @TransactionalEventListener
    public void handleCustomerCreatedEvent(CustomerCreatedEvent customerCreatedEvent){
        customerEventSource
                .customerCreating()
                .send(MessageBuilder.withPayload(customerCreatedEvent).build());
    }

    @TransactionalEventListener
    public void handleCustomerUpdatedEvent(CustomerUpdatedEvent customerUpdatedEvent){
        customerEventSource
                .customerUpdating()
                .send(MessageBuilder.withPayload(customerUpdatedEvent).build());
    }
}
