package csci318.assignment.salesboundedcontext.interfaces.rest;

import csci318.assignment.salesboundedcontext.application.internal.commandservices.ProductCommandService;
import csci318.assignment.salesboundedcontext.application.internal.queryservices.ProductQueryService;
import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/internal/")
public class InternalProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public InternalProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

//    Use case: Add order id to product
    @PutMapping("/{productId}/{orderId}")
    public Product addOrderToProduct(
            @PathVariable Long productId,
            @PathVariable String orderId) {
        return productCommandService.addOrderToProduct(productId, orderId);
    }

//    Use case: Get product by id
    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productQueryService.findByProductId(id);
    }
}
