package csci318.assignment.procurementboundedcontext.interfaces.rest;

import csci318.assignment.procurementboundedcontext.application.internal.commandservices.OrderCommandService;
import csci318.assignment.procurementboundedcontext.application.internal.outboundservices.ExternalOrderService;
import csci318.assignment.procurementboundedcontext.application.internal.queryservices.OrderQueryService;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.OrderId;
import csci318.assignment.procurementboundedcontext.interfaces.rest.dto.OrderRequestDTO;
import csci318.assignment.procurementboundedcontext.interfaces.rest.dto.OrderResponseDTO;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Customer;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.Order;
import csci318.assignment.procurementboundedcontext.domain.model.entities.Product;
import csci318.assignment.procurementboundedcontext.interfaces.rest.transform.OrderCommandDTOAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderCommandService orderCommandService;
    private final ExternalOrderService externalOrderService;
    private final OrderQueryService orderQueryService;

    public OrderController(OrderCommandService orderCommandService, ExternalOrderService externalOrderService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.externalOrderService = externalOrderService;
        this.orderQueryService = orderQueryService;
    }

//  Use case: Create order
    @PostMapping()
    @ResponseBody
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO request) {
        Customer existingCustomer = externalOrderService.getOrderSupplier(request.getSupplier());
        if (existingCustomer == null) {
            throw new RuntimeException("Customer does not exist!");
        }

        Product existingProduct = externalOrderService.getOrderProduct(request.getProduct());
        if (existingProduct == null) {
            throw new RuntimeException("Product does not exist!");
        }

        Order newOrder = orderCommandService.createOrder(OrderCommandDTOAssembler.toCommandFromDTO(request));

        return new OrderResponseDTO(newOrder);
    }

//  Use case: Update order
    @PutMapping("/{orderId}")
    public OrderResponseDTO updateOrder(@PathVariable String orderId, @RequestBody OrderRequestDTO request) {
        // 1. Find existing order
        Order existingOrder = orderQueryService.findByOrderId(new OrderId(orderId));

        // 2. Update quantity
        if (request.getQuantity() != null) {
            existingOrder.setQuantity(request.getQuantity());
        }

        // 3. Save updated order into the database
        Order updatedOrder = orderCommandService.updateOrder(
                OrderCommandDTOAssembler.toCommandFromDTO(orderId, request)
        );

        return new OrderResponseDTO(updatedOrder);
    }

//  Use case: Get order by order id
    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable String orderId) {
        Order order = orderQueryService.findByOrderId(new OrderId(orderId));
        return new OrderResponseDTO(order);
    }

//  Use case: Get all orders
    @GetMapping()
    public List<OrderResponseDTO> getOrderById() {
        List<Order> orderList = orderQueryService.findAll();
        return orderList.stream().map(OrderResponseDTO::new).collect(Collectors.toList());
    }
}
