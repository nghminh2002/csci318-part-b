package csci318.assignment.productservice.service;

import csci318.assignment.productservice.model.Product;

import java.util.List;

public interface ProductService {
    //Operations for product
    Product createProduct(Product newProduct);
    Product updateProduct(Product updatedProduct);
    Product getProduct(Long productId);
    List<Product> getAllProducts();
}
