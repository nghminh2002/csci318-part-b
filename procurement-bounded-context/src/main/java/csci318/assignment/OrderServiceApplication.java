package csci318.assignment;

import csci318.assignment.model.Order;
import csci318.assignment.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner loadDatabase(OrderRepository orderRepository) {
        return args -> {
            // Order example 1
            Order order1 = new Order();
            order1.setId(1L);
            order1.setProduct(1L);
            order1.setSupplier(1L);
            order1.setQuantity(10);
            Order savedOrder1 = orderRepository.save(order1);
            System.out.println(savedOrder1);

            // Order example 2
            Order order2 = new Order();
            order2.setId(2L);
            order2.setProduct(1L);
            order2.setSupplier(2L);
            order2.setQuantity(100);
            Order savedOrder2 = orderRepository.save(order2);
            System.out.println(savedOrder2);

            // Order example 2
            Order order3 = new Order();
            order3.setId(3L);
            order3.setProduct(2L);
            order3.setSupplier(2L);
            order3.setQuantity(1);
            Order savedOrder3 = orderRepository.save(order3);
            System.out.println(savedOrder3);
        };
    }
}
