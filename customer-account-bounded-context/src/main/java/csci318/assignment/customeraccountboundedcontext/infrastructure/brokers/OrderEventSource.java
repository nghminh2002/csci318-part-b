package csci318.assignment.customeraccountboundedcontext.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderEventSource {
    String ORDER_CREATING_INPUT = "orderCreatingChannel";

    @Input(ORDER_CREATING_INPUT)
    SubscribableChannel orderCreatingChannel();
}
