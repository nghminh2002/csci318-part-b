package csci318.assignment.procurementboundedcontext.interfaces.rest;

import csci318.assignment.procurementboundedcontext.application.internal.commandservices.OrderCommandService;
import csci318.assignment.procurementboundedcontext.application.internal.outboundservices.ExternalOrderService;
import csci318.assignment.procurementboundedcontext.application.internal.queryservices.OrderQueryService;
import csci318.assignment.procurementboundedcontext.domain.model.aggregates.OrderId;
import csci318.assignment.procurementboundedcontext.domain.model.commands.CreateOrderCommand;
import csci318.assignment.procurementboundedcontext.domain.model.commands.UpdateOrderCommand;
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

import java.util.ArrayList;
import java.util.List;

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

        CreateOrderCommand createOrderCommand = OrderCommandDTOAssembler.toCommandFromDTO(request);
        createOrderCommand.setSupplier(existingCustomer);
        createOrderCommand.setProduct(existingProduct);
        Order newOrder = orderCommandService.createOrder(createOrderCommand);

        return new OrderResponseDTO(newOrder, existingCustomer, existingProduct);
    }

//  Use case: Update order
    @PutMapping("/{orderId}")
    @ResponseBody
    public OrderResponseDTO updateOrder(@PathVariable String orderId, @RequestBody OrderRequestDTO request) {
        UpdateOrderCommand updateOrderCommand = OrderCommandDTOAssembler.toCommandFromDTO(orderId);
        // 1. Find existing order
        Order existingOrder = orderQueryService.findByOrderId(new OrderId(orderId));
        Customer existingCustomer = null;
        Product existingProduct = null;

        // 2. Check if the supplier needs to be updated
        // If yes, replace old supplier with the new supplier
        if (request.getSupplier() != null) {
            // 2.1. Check if the updated supplier exists
            existingCustomer = externalOrderService.getOrderSupplier(request.getSupplier());
            if (existingCustomer == null) {
                throw new RuntimeException("Customer does not exist!");
            }
            updateOrderCommand.setSupplierId(request.getSupplier());
        }

        // 3. Check if the product needs to be updated
        // If yes, replace old product with the new product
        if (request.getProduct() != null) {
            // 2.1. Check if the updated product exists
            existingProduct = externalOrderService.getOrderProduct(request.getProduct());
            if (existingProduct == null) {
                throw new RuntimeException("Product does not exist!");
            }
            updateOrderCommand.setProductId(request.getProduct());
        }

        // 4. Check if the quantity needs to be updated
        // If yes, replace old quantity with the new quantity
        if (request.getQuantity() != null) {
            updateOrderCommand.setOrderQuantity(request.getQuantity());
        }

        // 5. Save updated order into the database
        Order updatedOrder = orderCommandService.updateOrder(updateOrderCommand);

        // 6. If customer and product have not been fetched then fetch
        if (existingCustomer == null) {
            existingCustomer = externalOrderService.getOrderSupplier(existingOrder.getSupplier());
        }
        if (existingProduct == null) {
            existingProduct = externalOrderService.getOrderProduct(existingOrder.getProduct());
        }

        return new OrderResponseDTO(updatedOrder, existingCustomer, existingProduct);
    }

//  Use case: Get order by order id
    @GetMapping("/{orderId}")
    @ResponseBody
    public OrderResponseDTO getOrderById(@PathVariable String orderId) {
        Order order = orderQueryService.findByOrderId(new OrderId(orderId));
        Customer supplier = externalOrderService.getOrderSupplier(order.getSupplier());
        Product product = externalOrderService.getOrderProduct(order.getProduct());
        return new OrderResponseDTO(order, supplier, product);
    }

//  Use case: Get all orders
    @GetMapping()
    @ResponseBody
    public List<OrderResponseDTO> getOrderList() {
        List<Order> orderList = orderQueryService.findAll();
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Order order: orderList) {
            Customer supplier = externalOrderService.getOrderSupplier(order.getSupplier());
            Product product = externalOrderService.getOrderProduct(order.getProduct());
            orderResponseDTOList.add(new OrderResponseDTO(order, supplier, product));
        }
        return orderResponseDTOList;
    }
}
