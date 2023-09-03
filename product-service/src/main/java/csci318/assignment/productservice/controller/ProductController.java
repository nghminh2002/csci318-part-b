package csci318.assignment.productservice.controller;

import csci318.assignment.productservice.controller.dto.ProductDetailRequestDTO;
import csci318.assignment.productservice.controller.dto.ProductDetailResponseDTO;
import csci318.assignment.productservice.controller.dto.ProductOrderListResponseDTO;
import csci318.assignment.productservice.controller.dto.ProductRequestDTO;
import csci318.assignment.productservice.controller.dto.ProductResponseDTO;
import csci318.assignment.productservice.model.Order;
import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.ProductDetail;
import csci318.assignment.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
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
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO request) {
        Product product = new Product();
        product.setProductCategory(request.getProductCategory());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        Product newProduct = productService.createProduct(product);

        if (request.getComment() != null || request.getDescription() != null) {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setDescription(request.getDescription());
            productDetail.setComment(request.getComment());
            ProductDetail newProductDetail = productService.createProductDetail(productDetail);

            newProduct.setProductDetail(newProductDetail);
            productService.updateProduct(newProduct);
        }

        return new ProductResponseDTO(newProduct);
    }

//    Use case: Update product category, name and price
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

        if (request.getOrderId() != null) {
            existingProduct.addProductToOrder(request.getOrderId());
        }

        // 5. Check if the product detail needs to be updated
        if (request.getComment() != null || request.getDescription() != null) {

            // 4.1. Check if this product has already had product detail
            ProductDetail currentProductDetail = existingProduct.getProductDetail();
            ProductDetail existingProductDetail = null;

            // 4.2. If this product already had product detail then find it
            if (currentProductDetail != null) {
                Optional<ProductDetail> optionalProductDetail = productService.getProductDetail(currentProductDetail.getId());
                if (optionalProductDetail.isPresent()) {
                    existingProductDetail = optionalProductDetail.get();
                }
            }

            // 4.3. If this product already had product detail then update the existing product detail
            //      else create new product detail for the product
            if (existingProductDetail != null) {
                if (request.getDescription() != null) {
                    existingProductDetail.setDescription(request.getDescription());
                }

                if (request.getComment() != null) {
                    existingProductDetail.setComment(request.getComment());
                }
            } else {
                ProductDetail newProductDetail = new ProductDetail();
                newProductDetail.setDescription(request.getDescription());
                newProductDetail.setComment(request.getComment());
                existingProductDetail = productService.createProductDetail(newProductDetail);

                existingProduct.setProductDetail(newProductDetail);
            }

            // 4.4 Update product detail
            productService.updateProductDetail(existingProductDetail);
        }

        // 6. Update product
        existingProduct.updateProduct();
        Product updatedProduct = productService.updateProduct(existingProduct);

        return new ProductResponseDTO(updatedProduct);
    }

//    Use case: Map product detail to product
    @PutMapping("/{productId}/detail/{detailId}")
    public ProductResponseDTO updateProductProductDetail(@PathVariable Long productId, @PathVariable Long detailId) {
        Product updatedProduct = productService.updateProductProductDetail(productId, detailId);
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
        List<Order> createdOrders = productService.getAllOrdersHavingProduct(productId);
        return new ProductOrderListResponseDTO(existingProduct, createdOrders);
    }

//    Use case: Create product detail
    @PostMapping("/detail")
    public ProductDetailResponseDTO createProductDetail(@RequestBody ProductDetailRequestDTO request) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription(request.getDescription());
        productDetail.setComment(request.getComment());
        ProductDetail newProductDetail = productService.createProductDetail(productDetail);
        return new ProductDetailResponseDTO(newProductDetail);
    }
}
