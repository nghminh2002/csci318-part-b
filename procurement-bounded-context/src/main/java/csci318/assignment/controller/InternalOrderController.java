package csci318.assignment.controller;

import csci318.assignment.controller.dto.OrderCustomerResponseDTO;
import csci318.assignment.model.Customer;
import csci318.assignment.model.Order;
import csci318.assignment.service.OrderExternalService;
import csci318.assignment.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/internal")
public class InternalOrderController {
    private final OrderService orderService;
    private final OrderExternalService orderExternalService;

    public InternalOrderController(OrderService orderService, OrderExternalService orderExternalService) {
        this.orderService = orderService;
        this.orderExternalService = orderExternalService;
    }

    @GetMapping("/{orderId}")
    public OrderCustomerResponseDTO getOrderWithCustomerById(@PathVariable Long orderId) {
        Order existingOrder = orderService.getOrderById(orderId);
        Customer existingCustomer = orderExternalService.getOrderSupplier(existingOrder.getSupplier());
        return new OrderCustomerResponseDTO(existingOrder, existingCustomer);
    }
}
