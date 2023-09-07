package csci318.assignmnet.orderservice.controller;

import csci318.assignmnet.orderservice.controller.dto.OrderRequestDTO;
import csci318.assignmnet.orderservice.controller.dto.OrderResponseDTO;
import csci318.assignmnet.orderservice.model.Customer;
import csci318.assignmnet.orderservice.model.Order;
import csci318.assignmnet.orderservice.model.Product;
import csci318.assignmnet.orderservice.service.OrderExternalService;
import csci318.assignmnet.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderExternalService orderExternalService;

    public OrderController(OrderService orderService, OrderExternalService orderExternalService) {
        this.orderService = orderService;
        this.orderExternalService = orderExternalService;
    }

//    Use case: Create order
    @PostMapping()
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO request) {
        Order order = new Order();
        order.setProduct(request.getProduct());
        order.setSupplier(request.getSupplier());
        order.setQuantity(request.getQuantity());
        Order newOrder = orderService.createOrder(order);
        Customer customer = orderExternalService.getOrderSupplier(request.getSupplier());
        Product product = orderExternalService.getOrderProduct(request.getProduct());
        orderExternalService.addOrderToProduct(product.getId(), newOrder.getId());
        return new OrderResponseDTO(newOrder, customer, product);
    }

//    Use case: Update order
    @PutMapping("/{orderId}")
    public OrderResponseDTO updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDTO request) {
        // 1. Find existing order
        Order existingOrder = orderService.getOrderById(orderId);

        // 2. Check if the supplier needs to be updated
        // If yes, replace old supplier with the new supplier
        if (request.getSupplier() != null) {
            existingOrder.setSupplier(request.getSupplier());
        }

        // 3. Check if the product needs to be updated
        // If yes, replace old product with the new product
        if (request.getProduct() != null) {
            existingOrder.setProduct(request.getProduct());
        }

        // 4. Check if the quantity needs to be updated
        // If yes, replace old quantity with the new quantity
        if (request.getQuantity() != null) {
            existingOrder.setQuantity(request.getQuantity());
        }

        // 5. Save updated order into the database
        Order updatedOrder = orderService.updateOrder(existingOrder);
        Customer customer = orderExternalService.getOrderSupplier(existingOrder.getSupplier());
        Product product = orderExternalService.getOrderProduct(existingOrder.getProduct());
        return new OrderResponseDTO(updatedOrder, customer, product);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        Customer customer = orderExternalService.getOrderSupplier(order.getSupplier());
        Product product = orderExternalService.getOrderProduct(order.getProduct());
        return new OrderResponseDTO(order, customer, product);
    }
}
