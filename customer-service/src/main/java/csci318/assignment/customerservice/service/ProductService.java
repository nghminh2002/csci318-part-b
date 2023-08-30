package csci318.assignment.customerservice.service;

import csci318.assignment.customerservice.model.Product;
import csci318.assignment.customerservice.model.ProductDetail;

import java.util.List;

public interface ProductService {
    //Operations for product
    Product createProduct(Product newProduct);
    Product updateProduct(Long productId, Product updatedProduct);
    Product updateProductProductDetail(Long productId, Long productDetailId);
    Product getProduct(Long productId);
    List<Product> getAllProducts();

    //Operations for product detail
    ProductDetail createProductDetail(ProductDetail newProductDetail);
    ProductDetail updateProductDetail(Long productDetailId, ProductDetail updatedProductDetail);
    ProductDetail getProductDetail(Long productDetailId);
    List<ProductDetail> getAllProductDetails();
}
