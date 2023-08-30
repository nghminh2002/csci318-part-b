package csci318.assignment.customerservice.controller;

import csci318.assignment.customerservice.model.Product;
import csci318.assignment.customerservice.model.ProductDetail;
import csci318.assignment.customerservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    Use case: Create product
    @PostMapping()
    public Product createProduct(@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

//    Use case: Update product category, name and price
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

//    Use case: Map product detail to product
    @PutMapping("/{id}/detail/{detailId}")
    public Product updateProductProductDetail(@PathVariable Long id, @PathVariable Long detailId) {
        return productService.updateProductProductDetail(id, detailId);
    }

//    Use case: Get product by id
    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

//    Use case: Get all products
    @GetMapping()
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

//    Use case: Create product detail
    @PostMapping("/detail")
    public ProductDetail createProductDetail(@RequestBody ProductDetail newProductDetail) {
        return productService.createProductDetail(newProductDetail);
    }

//    Use case: Update product detail by id
    @PutMapping("/detail/{id}")
    public ProductDetail updateProductDetail(@PathVariable Long id, @RequestBody ProductDetail detail) {
        return productService.updateProductDetail(id, detail);
    }

//    Use case: Get product detail by id
    @GetMapping("/detail/{id}")
    ProductDetail getProductDetail(@PathVariable Long id) {
        return productService.getProductDetail(id);
    }

//    Use case: Get all product details
    @GetMapping("/detail")
    List<ProductDetail> getAllProductDetails() {
        return productService.getAllProductDetails();
    }
}
