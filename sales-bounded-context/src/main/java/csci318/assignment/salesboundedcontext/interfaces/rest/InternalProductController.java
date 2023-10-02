package csci318.assignment.salesboundedcontext.interfaces.rest;

import csci318.assignment.salesboundedcontext.application.internal.queryservices.ProductQueryService;
import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/internal/")
public class InternalProductController {
    private final ProductQueryService productQueryService;

    public InternalProductController(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

//    Use case: Get product by id
    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productQueryService.findByProductId(id);
    }
}
