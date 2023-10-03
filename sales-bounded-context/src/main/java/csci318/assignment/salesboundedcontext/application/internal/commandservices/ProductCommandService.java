package csci318.assignment.salesboundedcontext.application.internal.commandservices;

import csci318.assignment.salesboundedcontext.application.internal.queryservices.ProductQueryService;
import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import csci318.assignment.salesboundedcontext.domain.model.commands.CreateProductCommand;
import csci318.assignment.salesboundedcontext.domain.model.commands.UpdateProductCommand;
import csci318.assignment.salesboundedcontext.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    private final ProductQueryService productQueryService;
    private final ProductRepository productRepository;

    public ProductCommandService(
            ProductQueryService productQueryService,
            ProductRepository productRepository
    ) {
        this.productQueryService = productQueryService;
        this.productRepository = productRepository;
    }

    public Product createProduct(CreateProductCommand createProductCommand) {
        return productRepository.save(new Product(createProductCommand));
    }

    public Product updateProduct(UpdateProductCommand updateProductCommand) {
        Product product = productQueryService
                .findByProductId(updateProductCommand.getProductId());
        product.updateProduct(updateProductCommand);
        return productRepository.save(product);
    }

    public void addOrderToProduct(Long productId, String orderId) {
        Product existingProduct = productQueryService.findByProductId(productId);
        existingProduct.addCreatedOrders(orderId);
        productRepository.save(existingProduct);
    }
}
