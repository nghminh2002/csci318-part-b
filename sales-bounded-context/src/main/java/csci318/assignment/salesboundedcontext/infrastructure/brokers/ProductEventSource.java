package csci318.assignment.salesboundedcontext.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductEventSource {
    @Output("productCreatingChannel")
    MessageChannel productCreating();

    @Output("productUpdatingChannel")
    MessageChannel productUpdating();
}
