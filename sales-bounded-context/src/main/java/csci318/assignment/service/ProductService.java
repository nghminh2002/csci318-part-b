package csci318.assignment.service;

import csci318.assignment.controller.dto.OrderCustomerResponseDTO;
import csci318.assignment.controller.dto.ProductRequestDTO;
import csci318.assignment.model.Product;
import csci318.assignment.model.event.ProductEvent;
import csci318.assignment.model.valueobject.ProductDetail;
import csci318.assignment.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProductDomainService productDomainService;
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(
            ApplicationEventPublisher applicationEventPublisher,
            ProductDomainService productDomainService,
            ProductRepository productRepository,
            RestTemplate restTemplate
    ) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.productDomainService = productDomainService;
        this.restTemplate = restTemplate;
    }

    // Save new product into the database
    public Product createProduct(ProductRequestDTO request) {
        Product product = productDomainService.createFromDTO(request);
        Product newProduct = productRepository.save(product);

        ProductEvent event = new ProductEvent();
        event.setEventName("Create");
        event.setProductId(newProduct.getId());
        event.setProductCategory(newProduct.getProductCategory());
        event.setName(newProduct.getName());
        applicationEventPublisher.publishEvent(event);

        return newProduct;
    }

    public Product updateProduct(Long productId, ProductRequestDTO request) {
        // 1. Find existing product
        Product existingProduct = this.getProduct(productId);
        if (existingProduct == null) {
            throw new RuntimeException("The product does not exist");
        }

        // 2. Update product detail
        ProductDetail updatedProductDetail = productDomainService.updateProductDetail(
                existingProduct.getProductDetail(),
                request.getComment(),
                request.getDescription()
        );

        // 3. Update product
        Product updatedProduct = productDomainService.updateProduct(
                existingProduct,
                request.getProductCategory(),
                request.getName(),
                request.getPrice()
        );
        updatedProduct.setProductDetail(updatedProductDetail);

        updatedProduct.updateProduct();
        return productRepository.save(updatedProduct);
    }

    // Get product by product id
    public Product getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add new order to product
    public Product addOrderToProduct(Long productId, Long orderId) {
        // 1. Find existing product
        Product existingProduct = this.getProduct(productId);
        if (existingProduct == null) {
            throw new RuntimeException("The product does not exist");
        }

        // 2. Add order to product
        existingProduct.addProductToOrder(orderId);

        return productRepository.save(existingProduct);
    }

    // Get all orders of the product
    public List<OrderCustomerResponseDTO> getAllOrdersHavingProduct(Long productId) {
        final String url = "http://localhost:8082/order/internal/";
        List<OrderCustomerResponseDTO> orders = new ArrayList<>();
        List<Long> ids = productRepository.findById(productId)
                .orElseThrow(RuntimeException::new)
                .getCreatedOrders();
        for (Long id : ids) {
            orders.add(restTemplate.getForObject(url + id, OrderCustomerResponseDTO.class));
        }
        return orders;
    }
}
