package csci318.assignment.controller;

import csci318.assignment.controller.dto.OrderCustomerResponseDTO;
import csci318.assignment.controller.dto.ProductOrderListResponseDTO;
import csci318.assignment.controller.dto.ProductRequestDTO;
import csci318.assignment.controller.dto.ProductResponseDTO;
import csci318.assignment.model.Product;
import csci318.assignment.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    Use case: Create product
    @PostMapping()
    @ResponseBody
    public ProductResponseDTO createProduct(
            @RequestBody ProductRequestDTO request
    ) {
        Product newProduct = productService.createProduct(request);
        return new ProductResponseDTO(newProduct);
    }

//    Use case: Update product category, name, price, description and comment
    @PutMapping("/{productId}")
    @ResponseBody
    public ProductResponseDTO updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequestDTO request
    ) {
        Product updatedProduct = productService.updateProduct(
                productId,
                request
        );
        return new ProductResponseDTO(updatedProduct);
    }

//    Use case: Get product by id
    @GetMapping("/{id}")
    @ResponseBody
    ProductResponseDTO getProduct(@PathVariable Long id) {
        Product existingProduct = productService.getProduct(id);
        return new ProductResponseDTO(existingProduct);
    }

//    Use case: Get all products
    @GetMapping()
    @ResponseBody
    List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());
    }

    //    Use case: Get all products
    @GetMapping("/{productId}/all-orders")
    @ResponseBody
    ProductOrderListResponseDTO getAllOrdersHavingProduct(
            @PathVariable Long productId
    ) {
        Product existingProduct = productService.getProduct(productId);
        List<OrderCustomerResponseDTO> createdOrders =
                productService.getAllOrdersHavingProduct(productId);
        return new ProductOrderListResponseDTO(existingProduct, createdOrders);
    }
}
