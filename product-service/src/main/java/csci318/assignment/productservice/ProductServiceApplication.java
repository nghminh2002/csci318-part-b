package csci318.assignment.productservice;

import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.valueobject.ProductDetail;
import csci318.assignment.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner loadDatabase(ProductRepository productRepository) {
        return args -> {
            // Product and productDetail example 1
            ProductDetail productDetail1 = new ProductDetail();
            productDetail1.setDescription("Free cage chicken");
            productDetail1.setComment("Produced in Australia");

            Product product1 = new Product();
            product1.setId(1L);
            product1.setProductCategory("Meat");
            product1.setName("Chicken");
            product1.setPrice(15.2);
            product1.setProductDetail(productDetail1);
            product1.addCreatedOrders(1L);
            product1.addCreatedOrders(2L);
            Product savedProduct1 = productRepository.save(product1);
            System.out.println(savedProduct1);

            // Product and productDetail example 2
            ProductDetail productDetail2 = new ProductDetail();
            productDetail2.setDescription("Orange Vegetable");
            productDetail2.setComment("Grown Locally");

            Product product2 = new Product();
            product2.setId(2L);
            product2.setProductCategory("Vegetable");
            product2.setName("Carrot");
            product2.setPrice(5.0);
            product2.setProductDetail(productDetail2);
            product2.addCreatedOrders(3L);
            Product savedProduct2 = productRepository.save(product2);
            System.out.println(savedProduct2);
        };
    }
}
