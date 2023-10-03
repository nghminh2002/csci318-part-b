package csci318.assignment.procurementboundedcontext.interfaces.rest;

import csci318.assignment.procurementboundedcontext.application.internal.outboundservices.ExternalOrderService;
import csci318.assignment.procurementboundedcontext.application.internal.queryservices.OrderQueryService;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.OrderId;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Product;
import csci318.assignment.procurementboundedcontext.interfaces.rest.dto.OrderCustomerResponseDTO;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.interfaces.rest.dto.OrderProductResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/internal")
public class InternalOrderController {
    private final OrderQueryService orderQueryService;
    private final ExternalOrderService externalOrderService;

    public InternalOrderController(OrderQueryService orderQueryService, ExternalOrderService externalOrderService) {
        this.orderQueryService = orderQueryService;
        this.externalOrderService = externalOrderService;
    }

    @GetMapping("order-customer/{orderId}")
    public OrderCustomerResponseDTO getOrderWithCustomerById(@PathVariable String orderId) {
        Order existingOrder = orderQueryService.findByOrderId(new OrderId(orderId));
        Customer existingCustomer = externalOrderService.getOrderSupplier(existingOrder.getSupplier());
        return new OrderCustomerResponseDTO(existingOrder, existingCustomer);
    }

    @GetMapping("order-product/{orderId}")
    public OrderProductResponseDTO getOrderWithProductById(@PathVariable String orderId) {
        Order existingOrder = orderQueryService.findByOrderId(new OrderId(orderId));
        Product product = externalOrderService.getOrderProduct(existingOrder.getProduct());
        return new OrderProductResponseDTO(existingOrder, product);
    }
}
