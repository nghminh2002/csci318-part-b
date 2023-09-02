package csci318.assignment.productservice;

import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.ProductDetail;
import csci318.assignment.productservice.repository.ProductDetailRepository;
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
    public CommandLineRunner loadDatabase(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        return args -> {
            // Product and productDetail example 1
            Product product1 = new Product();
            product1.setProductCategory("Meat");
            product1.setName("Chicken");
            product1.setPrice(15.2);
            product1.addCreatedOrders(1L);
            product1.addCreatedOrders(2L);
            Product savedProduct1 = productRepository.save(product1);

            ProductDetail productDetail1 = new ProductDetail();
            productDetail1.setDescription("Free cage chicken");
            productDetail1.setComment("Produced in Australia");
            ProductDetail savedProductDetail1 = productDetailRepository.save(productDetail1);

            savedProduct1.setProductDetail(savedProductDetail1);
            productRepository.save(savedProduct1);
            System.out.println(savedProduct1);

            // Product and productDetail example 2
            Product product2 = new Product();
            product2.setProductCategory("Vegetable");
            product2.setName("Carrot");
            product2.setPrice(5.0);
            product2.addCreatedOrders(3L);
            Product savedProduct2 = productRepository.save(product2);

            ProductDetail productDetail2 = new ProductDetail();
            productDetail2.setDescription("Orange Vegetable");
            productDetail2.setComment("Grown Locally");
            ProductDetail savedProductDetail2 = productDetailRepository.save(productDetail2);

            savedProduct2.setProductDetail(savedProductDetail2);
            productRepository.save(savedProduct2);
            System.out.println(savedProduct2);
        };
    }
}
