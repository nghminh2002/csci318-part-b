package csci318.assignment.salesboundedcontext.application.internal.outboundservices;

import csci318.assignment.salesboundedcontext.interfaces.rest.dto.OrderCustomerResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalProductService {
    private final RestTemplate restTemplate;

    public ExternalProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrderCustomerResponseDTO> getAllOrdersHavingProduct(List<String> orderIds) {
        final String url = "http://localhost:8082/order/internal/order-customer/";
        List<OrderCustomerResponseDTO> orders = new ArrayList<>();
        for (String id : orderIds) {
            orders.add(restTemplate.getForObject(url + id, OrderCustomerResponseDTO.class));
        }
        return orders;
    }
}
