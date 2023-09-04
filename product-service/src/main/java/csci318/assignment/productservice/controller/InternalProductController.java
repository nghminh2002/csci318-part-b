package csci318.assignment.productservice.controller;

import csci318.assignment.productservice.controller.dto.ProductResponseDTO;
import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/internal/")
public class InternalProductController {
    private final ProductService productService;

    public InternalProductController(ProductService productService) {
        this.productService = productService;
    }

    //    Use case: Add order id to product
    @PutMapping("/{productId}/{orderId}")
    public ProductResponseDTO addOrderToProduct(@PathVariable Long productId, @PathVariable Long orderId) {
        Product existingProduct = productService.getProduct(productId);
        existingProduct.addProductToOrder(orderId);
        Product updatedProduct = productService.updateProduct(existingProduct);
        return new ProductResponseDTO(updatedProduct);
    }
}
