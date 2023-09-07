package csci318.assignment.productservice.service;

import csci318.assignment.productservice.model.Order;

import java.util.List;

public interface ProductExternalService {
    List<Order> getAllOrdersHavingProduct(Long productId);
}
