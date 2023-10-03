package csci318.assignment.customeraccountboundedcontext.application.internal.outboundservices;

import csci318.assignment.customeraccountboundedcontext.application.internal.commandservices.CustomerCommandService;
import csci318.assignment.customeraccountboundedcontext.infrastructure.brokers.OrderEventSource;
import csci318.assignment.sharreddomain.events.OrderCreatedEvent;
import csci318.assignment.sharreddomain.events.OrderCreatedEventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(OrderEventSource.class)
public class CustomerEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEventHandler.class);

    private final CustomerCommandService customerCommandService;

    public CustomerEventHandler(CustomerCommandService customerCommandService) {
        this.customerCommandService = customerCommandService;
    }

    @StreamListener(OrderEventSource.ORDER_CREATING_INPUT)
    public void receiveEvent(OrderCreatedEvent orderCreatedEvent) {
        try {
            OrderCreatedEventData receivedData = orderCreatedEvent.getOrderCreatedEventData();
            String message = String.format("New order %s for %s was created by %s",
                    receivedData.getOrderId(),
                    receivedData.getProductName(),
                    receivedData.getSupplierName());
            LOGGER.info(message);
            customerCommandService.addOrderToCustomer(
                    receivedData.getSupplierId(),
                    receivedData.getOrderId()
            );
        } catch (Exception e) {
            LOGGER.error("Error processing order event", e);
        }
    }
}
