package csci318.assignment.productservice.controller;

import csci318.assignment.productservice.controller.dto.OrderCustomerResponseDTO;
import csci318.assignment.productservice.controller.dto.ProductOrderListResponseDTO;
import csci318.assignment.productservice.controller.dto.ProductRequestDTO;
import csci318.assignment.productservice.controller.dto.ProductResponseDTO;
import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.valueobject.ProductDetail;
import csci318.assignment.productservice.service.ProductExternalService;
import csci318.assignment.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductExternalService productExternalService;

    public ProductController(ProductService productService, ProductExternalService productExternalService) {
        this.productService = productService;
        this.productExternalService = productExternalService;
    }

//    Use case: Create product
    @PostMapping()
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO request) {
        Product product = new Product();
        product.setProductCategory(request.getProductCategory());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setProductDetail(new ProductDetail(request.getDescription(), request.getComment()));
        Product newProduct = productService.createProduct(product);
        return new ProductResponseDTO(newProduct);
    }

//    Use case: Update product category, name, price, description and comment
    @PutMapping("/{productId}")
    public ProductResponseDTO updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO request) {
        // 1. Find existing product
        Product existingProduct = productService.getProduct(productId);

        // 2. Check if the product category needs to be updated
        // If yes, replace old product category with the new product category
        if (request.getProductCategory() != null) {
            existingProduct.setProductCategory(request.getProductCategory());
        }

        // 3. Check if the name needs to be updated
        // If yes, replace old name with the new name
        if (request.getName() != null) {
            existingProduct.setName(request.getName());
        }

        // 3. Check if the price needs to be updated
        // If yes, replace old price with the new price
        if (request.getPrice() != null) {
            existingProduct.setPrice(request.getPrice());
        }

        // 4. Check if the product detail needs to be updated
        if (request.getComment() != null || request.getDescription() != null) {
            ProductDetail currentProductDetail = existingProduct.getProductDetail();
            String description = request.getDescription() != null ? request.getDescription() : currentProductDetail.getDescription();
            String comment = request.getComment() != null ? request.getComment() : currentProductDetail.getComment();
            existingProduct.setProductDetail(new ProductDetail(description, comment));
        }

        // 5. Update product
        existingProduct.updateProduct();
        Product updatedProduct = productService.updateProduct(existingProduct);
        return new ProductResponseDTO(updatedProduct);
    }

//    Use case: Get product by id
    @GetMapping("/{id}")
    ProductResponseDTO getProduct(@PathVariable Long id) {
        Product existingProduct = productService.getProduct(id);
        return new ProductResponseDTO(existingProduct);
    }

//    Use case: Get all products
    @GetMapping()
    List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts().stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    //    Use case: Get all products
    @GetMapping("/{productId}/all-orders")
    ProductOrderListResponseDTO getAllOrdersHavingProduct(@PathVariable Long productId) {
        Product existingProduct = productService.getProduct(productId);
        List<OrderCustomerResponseDTO> createdOrders = productExternalService.getAllOrdersHavingProduct(productId);
        return new ProductOrderListResponseDTO(existingProduct, createdOrders);
    }
}
