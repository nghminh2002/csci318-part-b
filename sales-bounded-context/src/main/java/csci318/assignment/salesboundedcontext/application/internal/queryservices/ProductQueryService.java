package csci318.assignment.salesboundedcontext.application.internal.queryservices;

import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import csci318.assignment.salesboundedcontext.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findByProductId(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("The product does not exist");
        }
        return optionalProduct.get();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
