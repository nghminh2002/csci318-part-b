package csci318.assignment.productservice.service.implementation;

import csci318.assignment.productservice.model.Order;
import csci318.assignment.productservice.repository.ProductRepository;
import csci318.assignment.productservice.service.ProductExternalService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductExternalServiceImpl implements ProductExternalService {
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductExternalServiceImpl(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Order> getAllOrdersHavingProduct(Long productId) {
        final String url = "http://localhost:8082/order/internal/";
        List<Order> orders = new ArrayList<>();
        List<Long> ids = productRepository.findById(productId).orElseThrow(RuntimeException::new)
                .getCreatedOrders();
        for (Long id : ids) {
            orders.add(restTemplate.getForObject(url + id, Order.class));
        }
        return orders;
    }
}
