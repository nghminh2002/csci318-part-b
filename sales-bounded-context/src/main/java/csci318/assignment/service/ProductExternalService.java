package csci318.assignment.service;

import csci318.assignment.controller.dto.OrderCustomerResponseDTO;

import java.util.List;

public interface ProductExternalService {
    List<OrderCustomerResponseDTO> getAllOrdersHavingProduct(Long productId);
}
