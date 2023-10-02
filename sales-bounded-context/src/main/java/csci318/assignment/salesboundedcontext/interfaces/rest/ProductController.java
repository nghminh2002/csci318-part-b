package csci318.assignment.salesboundedcontext.interfaces.rest;

import csci318.assignment.salesboundedcontext.application.internal.commandservices.ProductCommandService;
import csci318.assignment.salesboundedcontext.application.internal.outboundservices.ExternalProductService;
import csci318.assignment.salesboundedcontext.application.internal.queryservices.ProductQueryService;
import csci318.assignment.salesboundedcontext.interfaces.rest.dto.OrderCustomerResponseDTO;
import csci318.assignment.salesboundedcontext.interfaces.rest.dto.ProductOrderListResponseDTO;
import csci318.assignment.salesboundedcontext.interfaces.rest.dto.ProductRequestDTO;
import csci318.assignment.salesboundedcontext.interfaces.rest.dto.ProductResponseDTO;
import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import csci318.assignment.salesboundedcontext.interfaces.rest.transform.ProductCommandDTOAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;
    private final ExternalProductService externalProductService;

    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService, ExternalProductService externalProductService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
        this.externalProductService = externalProductService;
    }

//    Use case: Create product
    @PostMapping()
    @ResponseBody
    public ProductResponseDTO createProduct(
            @RequestBody ProductRequestDTO request
    ) {
        Product newProduct = productCommandService.createProduct(ProductCommandDTOAssembler.toCommandFromDTO(request));
        return new ProductResponseDTO(newProduct);
    }

//    Use case: Update product category, name, price, description and comment
    @PutMapping("/{productId}")
    @ResponseBody
    public ProductResponseDTO updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequestDTO request
    ) {
        Product originalProduct = productQueryService.findByProductId(productId);
        Product updatedProduct = productCommandService.updateProduct(
                ProductCommandDTOAssembler.toCommandFromDTO(originalProduct, request)
        );
        return new ProductResponseDTO(updatedProduct);
    }

//    Use case: Get product by id
    @GetMapping("/{id}")
    @ResponseBody
    ProductResponseDTO getProduct(@PathVariable Long id) {
        Product existingProduct = productQueryService.findByProductId(id);
        return new ProductResponseDTO(existingProduct);
    }

//    Use case: Get all products
    @GetMapping()
    @ResponseBody
    List<ProductResponseDTO> getAllProducts() {
        return productQueryService.findAll().stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());
    }

    //    Use case: Get all products
    @GetMapping("/{productId}/all-orders")
    @ResponseBody
    ProductOrderListResponseDTO getAllOrdersHavingProduct(
            @PathVariable Long productId
    ) {
        Product existingProduct = productQueryService.findByProductId(productId);
        List<OrderCustomerResponseDTO> createdOrders =
                externalProductService.getAllOrdersHavingProduct(existingProduct.getCreatedOrders());
        return new ProductOrderListResponseDTO(existingProduct, createdOrders);
    }
}
