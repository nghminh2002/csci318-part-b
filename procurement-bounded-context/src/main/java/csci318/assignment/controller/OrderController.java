package csci318.assignment.controller;

import csci318.assignment.controller.dto.OrderRequestDTO;
import csci318.assignment.controller.dto.OrderResponseDTO;
import csci318.assignment.model.Customer;
import csci318.assignment.model.Order;
import csci318.assignment.model.Product;
import csci318.assignment.service.OrderService;
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

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    Use case: Create order
    @PostMapping()
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO request) {
        Customer existingCustomer = orderService.getOrderSupplier(request.getSupplier());
        if (existingCustomer == null) {
            throw new RuntimeException("Customer does not exist!");
        }

        Product existingProduct = orderService.getOrderProduct(request.getProduct());
        if (existingProduct == null) {
            throw new RuntimeException("Product does not exist!");
        }

        Order newOrder = orderService.createOrder(request);

        return new OrderResponseDTO(newOrder, existingCustomer, existingProduct);
    }

//    Use case: Update order
    @PutMapping("/{orderId}")
    public OrderResponseDTO updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDTO request) {
        // 1. Find existing order
        Order existingOrder = orderService.getOrderById(orderId);
        Customer existingCustomer = null;
        Product existingProduct = null;

        // 2. Check if the supplier needs to be updated
        // If yes, replace old supplier with the new supplier
        if (request.getSupplier() != null) {
            // 2.1. Check if the updated supplier exists
            existingCustomer = orderService.getOrderSupplier(request.getSupplier());
            if (existingCustomer == null) {
                throw new RuntimeException("Customer does not exist!");
            }
            existingOrder.setSupplier(request.getSupplier());
        }

        // 3. Check if the product needs to be updated
        // If yes, replace old product with the new product
        if (request.getProduct() != null) {
            // 2.1. Check if the updated product exists
            existingProduct = orderService.getOrderProduct(request.getProduct());
            if (existingProduct == null) {
                throw new RuntimeException("Product does not exist!");
            }
            existingOrder.setProduct(request.getProduct());
        }

        // 4. Check if the quantity needs to be updated
        // If yes, replace old quantity with the new quantity
        if (request.getQuantity() != null) {
            existingOrder.setQuantity(request.getQuantity());
        }

        // 5. Save updated order into the database
        Order updatedOrder = orderService.updateOrder(existingOrder);

        // 6. If customer and product have not been fetched then fetch
        if (existingCustomer == null) {
            existingCustomer = orderService.getOrderSupplier(existingOrder.getSupplier());
        }
        if (existingProduct == null) {
            existingProduct = orderService.getOrderProduct(existingOrder.getProduct());
        }

        return new OrderResponseDTO(updatedOrder, existingCustomer, existingProduct);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        Customer customer = orderService.getOrderSupplier(order.getSupplier());
        Product product = orderService.getOrderProduct(order.getProduct());
        return new OrderResponseDTO(order, customer, product);
    }
}
