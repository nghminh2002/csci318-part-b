package csci318.assignment.customerservice;

import csci318.assignment.customerservice.model.valueobject.Contact;
import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner loadDatabase(CustomerRepository customerRepository) {
        return args -> {
            // Customer and contact example 1
            Contact contact1 = new Contact();
            contact1.setName("Hue Minh Nguyen");
            contact1.setPhone("0123456789");
            contact1.setEmail("hmn998@uowmail.edu.au");
            contact1.setPosition("Technical Support");

            Customer customer1 = new Customer();
            customer1.setId(1L);
            customer1.setCompanyName("Company A");
            customer1.setAddress("Moore St, Liverpool, NSW");
            customer1.setCountry("Australia");
            customer1.setContact(contact1);
            Customer savedCustomer1 = customerRepository.save(customer1);

            System.out.println(savedCustomer1);

            // Customer and contact example 2
            Contact contact2 = new Contact();
            contact2.setName("Nguyen Hue Minh");
            contact2.setPhone("0987654321");
            contact2.setEmail("hmn998@gmail.com");
            contact2.setPosition("Software Engineer");

            Customer customer2 = new Customer();
            customer2.setId(2L);
            customer2.setCompanyName("Company B");
            customer2.setAddress("King St, Melbourne, VIC");
            customer2.setCountry("Australia");
            customer2.setContact(contact2);
            Customer savedCustomer2 = customerRepository.save(customer2);

            System.out.println(savedCustomer2);
        };
    }
}
