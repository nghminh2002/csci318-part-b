package csci318.assignment.customerservice.service.Implementation;

import csci318.assignment.customerservice.model.Order;
import csci318.assignment.customerservice.repository.OrderRepository;
import csci318.assignment.customerservice.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Save new order to the database
    @Override
    public Order createOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    // Update order by id
    @Override
    public Order updateOrder(Long orderId, Order updatedOrder) {
        // 1. Find existing order
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(RuntimeException::new);

        // 2. Check if the supplier needs to be updated
        // If yes, replace old supplier with the new supplier
        if (updatedOrder.getSupplier() != null) {
            existingOrder.setSupplier(updatedOrder.getSupplier());
        }

        // 3. Check if the product needs to be updated
        // If yes, replace old product with the new product
        if (updatedOrder.getProduct() != null) {
            existingOrder.setProduct(updatedOrder.getProduct());
        }

        // 4. Check if the quantity needs to be updated
        // If yes, replace old quantity with the new quantity
        if (updatedOrder.getQuantity() != null) {
            existingOrder.setQuantity(updatedOrder.getQuantity());
        }

        // 5. Save updated order into the database
        return orderRepository.save(existingOrder);
    }
}
