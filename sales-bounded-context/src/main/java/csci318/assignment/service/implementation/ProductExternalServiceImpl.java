package csci318.assignment.service.implementation;

import csci318.assignment.controller.dto.OrderCustomerResponseDTO;
import csci318.assignment.repository.ProductRepository;
import csci318.assignment.service.ProductExternalService;
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
    public List<OrderCustomerResponseDTO> getAllOrdersHavingProduct(Long productId) {
        final String url = "http://localhost:8082/order/internal/";
        List<OrderCustomerResponseDTO> orders = new ArrayList<>();
        List<Long> ids = productRepository.findById(productId).orElseThrow(RuntimeException::new)
                .getCreatedOrders();
        for (Long id : ids) {
            orders.add(restTemplate.getForObject(url + id, OrderCustomerResponseDTO.class));
        }
        return orders;
    }
}
