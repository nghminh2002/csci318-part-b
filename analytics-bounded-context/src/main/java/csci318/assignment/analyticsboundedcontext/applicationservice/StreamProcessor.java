package csci318.assignment.analyticsboundedcontext.applicationservice;

import csci318.assignment.shareddomain.events.CustomerCreatedEvent;
import csci318.assignment.shareddomain.events.OrderCreatedEvent;
import csci318.assignment.shareddomain.events.ProductCreatedEvent;
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
    public Consumer<KStream<String, CustomerCreatedEvent>> processCustomer() {
        return inputStream -> {
            //generate total created customers
            KStream<String, Long> customerCountStream = inputStream
                    .map((key, value) -> KeyValue.pair("Customer", 1L))
                    .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                    .count()
                    .toStream();

            customerCountStream
                    .print(Printed.<String, Long>toSysOut().withLabel("Total customer created: "));
        };
    }

    @Bean
    public Consumer<KStream<String, ProductCreatedEvent>> processProduct() {
        return inputStream -> {
            //generate total created products
            KStream<String, Long> productCountStream = inputStream
                    .map((key, value) -> KeyValue.pair("Product", 1L))
                    .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                    .count()
                    .toStream();

            productCountStream
                    .print(Printed.<String, Long>toSysOut().withLabel("Total created product: "));

            //generate total created products by product category
            KStream<String, Long> productByCategoryCountStream = inputStream.map((key, value) -> {
                        String productCategory = value.getProductCreatedEventData().getProductCategory();
                        return KeyValue.pair(productCategory, 1L);
                    }).
                    groupByKey(Grouped.with(Serdes.String(), Serdes.Long())).
                    reduce(Long::sum).toStream();

            //just print the stream out to console
            productByCategoryCountStream.
                    print(Printed.<String, Long>toSysOut().withLabel("Total products by product category: "));
        };
    }

    @Bean
    public Consumer<KStream<String, OrderCreatedEvent>> processOrder() {
        return inputStream -> {

            //generate total created orders
            KStream<String, Long> orderCountStream = inputStream
                    .map((key, value) -> KeyValue.pair("Order", 1L))
                    .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                    .count()
                    .toStream();

            orderCountStream
                    .print(Printed.<String, Long>toSysOut().withLabel("Total created order: "));

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
