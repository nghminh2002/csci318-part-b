package csci318.assignment.salesboundedcontext.application.internal.outboundservices;

import csci318.assignment.salesboundedcontext.application.internal.commandservices.ProductCommandService;
import csci318.assignment.salesboundedcontext.infrastructure.brokers.OrderEventSource;
import csci318.assignment.shareddomain.events.OrderCreatedEvent;
import csci318.assignment.shareddomain.events.OrderCreatedEventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(OrderEventSource.class)
public class ProductEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventHandler.class);

    private final ProductCommandService productCommandService;

    public ProductEventHandler(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
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
            productCommandService.addOrderToProduct(
                    receivedData.getProductId(),
                    receivedData.getOrderId()
            );
        } catch (Exception e) {
            LOGGER.error("Error processing order event", e);
        }
    }
}