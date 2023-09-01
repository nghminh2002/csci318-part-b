package csci318.assignment.productservice.service;

import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.ProductDetail;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    //Operations for product
    Product createProduct(Product newProduct);
    Product updateProduct(Product updatedProduct);
    Product updateProductProductDetail(Long productId, Long productDetailId);
    Product getProduct(Long productId);
    List<Product> getAllProducts();

    //Operations for product detail
    ProductDetail createProductDetail(ProductDetail newProductDetail);
    ProductDetail updateProductDetail(ProductDetail updatedProductDetail);
    Optional<ProductDetail> getProductDetail(Long productDetailId);
    List<ProductDetail> getAllProductDetails();
}
