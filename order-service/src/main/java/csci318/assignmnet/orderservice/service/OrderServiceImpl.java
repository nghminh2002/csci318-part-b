package csci318.assignmnet.orderservice.service;

import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Order;
import csci318.assignmnet.orderservice.model.Product;
import csci318.assignmnet.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    // Save new order to the database
    @Override
    public Order createOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        return orderRepository.save(updatedOrder);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Customer getOrderSupplier(Long supplierId) {
        final String url = "http://localhost:8080/customer/";
        Customer customer = restTemplate.getForObject(url + supplierId, Customer.class);
        return customer;
    }

    @Override
    public Product getOrderProduct(Long productId) {
        final String url = "http://localhost:8081/product/";
        Product product = restTemplate.getForObject(url + productId, Product.class);
        return product;
    }
}
