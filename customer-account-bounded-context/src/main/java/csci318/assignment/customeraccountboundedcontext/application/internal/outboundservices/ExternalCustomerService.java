package csci318.assignment.customeraccountboundedcontext.application.internal.outboundservices;

import csci318.assignment.customeraccountboundedcontext.interfaces.rest.dto.OrderProductResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalCustomerService {
    private final RestTemplate restTemplate;

    public ExternalCustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrderProductResponseDTO> getCustomerOrderHistory(List<String> orderIds) {
        final String url = "http://localhost:8082/order/internal/order-product/";
        List<OrderProductResponseDTO> orders = new ArrayList<>();
        for (String id : orderIds) {
            orders.add(restTemplate.getForObject(url + id, OrderProductResponseDTO.class));
        }
        return orders;
    }
}
