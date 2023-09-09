package csci318.assignment.service;

import csci318.assignment.controller.dto.OrderRequestDTO;
import csci318.assignment.model.Customer;
import csci318.assignment.model.Order;
import csci318.assignment.model.Product;
import csci318.assignment.model.event.OrderEvent;
import csci318.assignment.repository.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(
            ApplicationEventPublisher applicationEventPublisher,
            OrderDomainService orderDomainService,
            OrderRepository orderRepository,
            RestTemplate restTemplate
    ) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    // Save new order to the database
    public Order createOrder(OrderRequestDTO request) {
        Order newOrder = orderDomainService.createOrderFromDTO(request);
        Order order = orderRepository.save(newOrder);

        OrderEvent event = new OrderEvent();
        event.setEventName("Create");
        event.setOrderId(order.getId());
        event.setProductId(order.getProduct());
        event.setSupplierId(order.getSupplier());
        applicationEventPublisher.publishEvent(event);

        this.addOrderToProduct(request.getProduct(), newOrder.getId());
        return order;
    }

    public Order updateOrder(Order updatedOrder) {
        updatedOrder.updateOrder();
        return orderRepository.save(updatedOrder);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(RuntimeException::new);
    }

    public Customer getOrderSupplier(Long supplierId) {
        final String url = "http://localhost:8080/customer/internal/" + supplierId;
        return restTemplate.getForObject(url, Customer.class);
    }

    public Product getOrderProduct(Long productId) {
        final String url = "http://localhost:8081/product/internal/" + productId;
        return restTemplate.getForObject(url, Product.class);
    }

    public void addOrderToProduct(Long productId, Long orderId) {
        final String url = "http://localhost:8081/product/internal/" + productId + "/" + orderId ;
        restTemplate.put(url, null);
    }
}
