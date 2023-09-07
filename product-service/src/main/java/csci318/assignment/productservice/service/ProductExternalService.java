package csci318.assignment.productservice.service;

import csci318.assignment.productservice.controller.dto.OrderCustomerResponseDTO;

import java.util.List;

public interface ProductExternalService {
    List<OrderCustomerResponseDTO> getAllOrdersHavingProduct(Long productId);
}
