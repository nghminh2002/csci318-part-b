package csci318.assignment.service;

import csci318.assignment.controller.dto.OrderRequestDTO;
import csci318.assignment.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderDomainService {

    public Order createOrderFromDTO(OrderRequestDTO request) {
        Order order = new Order();
        order.setProduct(request.getProduct());
        order.setSupplier(request.getSupplier());
        order.setQuantity(request.getQuantity());
        return order;
    }
}
