package csci318.assignment.analyticsboundedcontext.applicationservice;

import csci318.assignment.shareddomain.events.OrderCreatedEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class StreamProcessor {
    @Bean
    public Consumer<KStream<String, OrderCreatedEvent>> process() {
        return inputStream -> {

            //generate total created orders
            KStream<String, Long> orderCountStream = inputStream
                    .map((key, value) -> KeyValue.pair("Order", 1L))
                    .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                    .count()
                    .toStream();

            orderCountStream
                    .print(Printed.<String, Long>toSysOut().withLabel("Total Orders Created"));

            //generate total sold quantity by product
            KStream<String, Long> quantitySoldByProductCountStream = inputStream.map((key, value) -> {
                        String productName = value.getOrderCreatedEventData().getProductName();
                        Long quantity = value.getOrderCreatedEventData().getQuantity().longValue();
                        return KeyValue.pair(productName, quantity);
                    }).
                    groupByKey(Grouped.with(Serdes.String(), Serdes.Long())).
                    reduce(Long::sum).toStream();

            //just print the stream out to console
            quantitySoldByProductCountStream.
                    print(Printed.<String, Long>toSysOut().withLabel("Total quantity sold by product"));

            //generate total order by customer
            KStream<String, Long> orderByCustomerCountStream = inputStream.map((key, value) -> {
                        String customerName = value.getOrderCreatedEventData().getSupplierName();
                        return KeyValue.pair(customerName, 1L);
                    }).
                    groupByKey(Grouped.with(Serdes.String(), Serdes.Long())).
                    reduce(Long::sum).toStream();

            //just print the stream out to console
            orderByCustomerCountStream.
                    print(Printed.<String, Long>toSysOut().withLabel("Total orders by customer"));
        };
    }
}
