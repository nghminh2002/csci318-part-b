package csci318.assignmnet.orderservice.service;

import csci318.assignmnet.orderservice.controller.dto.AddOrderToProductDTO;
import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Order;
import csci318.assignmnet.orderservice.model.Product;
import csci318.assignmnet.orderservice.model.event.OrderEvent;
import csci318.assignmnet.orderservice.repository.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // Save new order to the database
    @Override
    public Order createOrder(Order newOrder) {
        Order order = orderRepository.save(newOrder);
        OrderEvent event = new OrderEvent();
        event.setEventName("Create");
        event.setOrderId(order.getId());
        event.setProductId(order.getProduct());
        event.setSupplierId(order.getSupplier());
        applicationEventPublisher.publishEvent(event);
        this.addOrderToProduct(order.getProduct(), order.getId());
        return order;
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
        return restTemplate.getForObject(url + supplierId, Customer.class);
    }

    @Override
    public Product getOrderProduct(Long productId) {
        final String url = "http://localhost:8081/product/";
        return restTemplate.getForObject(url + productId, Product.class);
    }

    private void addOrderToProduct(Long productId, Long orderId) {
        final String url = "http://localhost:8081/product/internal/" + productId + "/" + orderId ;
        final AddOrderToProductDTO request = new AddOrderToProductDTO(orderId);
        restTemplate.put(url, request);
    }
}
