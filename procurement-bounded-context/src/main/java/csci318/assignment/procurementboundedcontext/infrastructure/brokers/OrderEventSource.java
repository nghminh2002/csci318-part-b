package csci318.assignment.procurementboundedcontext.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderEventSource {

    @Output("orderCreatingChannel")
    MessageChannel orderCreating();

    @Output("orderUpdatingChannel")
    MessageChannel orderUpdating();
}