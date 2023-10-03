package csci318.assignment.customeraccountboundedcontext.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerEventSource {
    @Output("customerCreatingChannel")
    MessageChannel customerCreating();

    @Output("customerUpdatingChannel")
    MessageChannel customerUpdating();
}
