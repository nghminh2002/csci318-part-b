package csci318.assignment.productservice.service;

import csci318.assignment.productservice.model.Order;
import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.ProductDetail;
import csci318.assignment.productservice.repository.ProductDetailRepository;
import csci318.assignment.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final RestTemplate restTemplate;

    public ProductServiceImpl(ProductRepository productRepository, ProductDetailRepository productDetailRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.restTemplate = restTemplate;
    }

    // Save new product into the database
    @Override
    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        return productRepository.save(updatedProduct);
    }

    // Map product detail to product
    @Override
    public Product updateProductProductDetail(Long productId, Long productDetailId) {
        // 1. Find existing product
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(RuntimeException::new);

        // 2. Find existing product detail
        ProductDetail existingProductDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(RuntimeException::new);

        // 3. Set product detail for product
        existingProduct.setProductDetail(existingProductDetail);

        // 4. Update the updated product into database
        return productRepository.save(existingProduct);
    }

    // Get product by product id
    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(RuntimeException::new);
    }

    // Get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersHavingProduct(Long productId) {
        final String url = "http://localhost:8082/order/internal/";
        List<Order> orders = new ArrayList<>();
        List<Long> ids = productRepository.findById(productId).orElseThrow(RuntimeException::new)
                .getCreatedOrders();
        for (Long id : ids) {
            orders.add(restTemplate.getForObject(url + id, Order.class));
        }
        return orders;
    }

    // Save new product detail to the database
    @Override
    public ProductDetail createProductDetail(ProductDetail newProductDetail) {
        return productDetailRepository.save(newProductDetail);
    }

    @Override
    public ProductDetail updateProductDetail(ProductDetail updatedProductDetail) {
        return productDetailRepository.save(updatedProductDetail);
    }

    // Get product detail by id
    @Override
    public Optional<ProductDetail> getProductDetail(Long productDetailId) {
        return productDetailRepository.findById(productDetailId);
    }
}
