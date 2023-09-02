package csci318.assignment.customerservice;

import csci318.assignment.customerservice.model.Contact;
import csci318.assignment.customerservice.model.Customer;
import csci318.assignment.customerservice.repository.ContactRepository;
import csci318.assignment.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadDatabase(CustomerRepository customerRepository, ContactRepository contactRepository) {
        return args -> {
            // Customer and contact example 1
            Customer customer1 = new Customer();
            customer1.setCompanyName("Company A");
            customer1.setAddress("Liverpool, South Western Sydney");
            customer1.setCountry("Australia");
            Customer savedCustomer1 = customerRepository.save(customer1);

            Contact contact1 = new Contact();
            contact1.setName("Hue Minh Nguyen");
            contact1.setPhone("0123456789");
            contact1.setEmail("hmn998@uowmail.edu.au");
            contact1.setPosition("Technical Support");
            Contact savedContact1 = (contactRepository.save(contact1));

            savedCustomer1.setContact(savedContact1);
            customerRepository.save(savedCustomer1);
            System.out.println(savedCustomer1);

            // Customer and contact example 2
            Customer customer2 = new Customer();
            customer2.setCompanyName("Company B");
            customer2.setAddress("Auckland");
            customer2.setCountry("New Zealand");
            Customer savedCustomer2 = customerRepository.save(customer2);

            Contact contact2 = new Contact();
            contact2.setName("Nguyen Hue Minh");
            contact2.setPhone("0987654321");
            contact2.setEmail("hmn998@gmail.com");
            contact2.setPosition("Software Engineer");
            Contact savedContact2 = (contactRepository.save(contact2));

            savedCustomer2.setContact(savedContact2);
            customerRepository.save(savedCustomer2);
            System.out.println(savedCustomer2);
        };
    }
}
