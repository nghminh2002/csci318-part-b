package csci318.assignmnet.orderservice.service.implementation;

import csci318.assignmnet.orderservice.controller.dto.AddOrderToProductDTO;
import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Product;
import csci318.assignmnet.orderservice.service.OrderExternalService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderExternalServiceImpl implements OrderExternalService {
    private final RestTemplate restTemplate;

    public OrderExternalServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

    @Override
    public void addOrderToProduct(Long productId, Long orderId) {
        final String url = "http://localhost:8081/product/internal/" + productId + "/" + orderId ;
        final AddOrderToProductDTO request = new AddOrderToProductDTO(orderId);
        restTemplate.put(url, request);
    }
}
