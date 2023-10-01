package csci318.assignment.procurementboundedcontext.application.internal.outboundservices;

import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalOrderService {
    private final RestTemplate restTemplate;

    public ExternalOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
