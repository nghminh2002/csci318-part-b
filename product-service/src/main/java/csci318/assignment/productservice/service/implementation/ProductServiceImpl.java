package csci318.assignment.productservice.service.implementation;

import csci318.assignment.productservice.model.Product;
import csci318.assignment.productservice.model.event.ProductEvent;
import csci318.assignment.productservice.repository.ProductRepository;
import csci318.assignment.productservice.service.ProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProductServiceImpl(ProductRepository productRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // Save new product into the database
    @Override
    public Product createProduct(Product newProduct) {
        Product savedProduct = productRepository.save(newProduct);
        ProductEvent event = new ProductEvent();
        event.setEventName("Create");
        event.setProductId(savedProduct.getId());
        event.setProductCategory(savedProduct.getProductCategory());
        event.setName(savedProduct.getName());
        applicationEventPublisher.publishEvent(event);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        return productRepository.save(updatedProduct);
    }

    // Get product by product id
    @Override
    public Product getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    // Get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
