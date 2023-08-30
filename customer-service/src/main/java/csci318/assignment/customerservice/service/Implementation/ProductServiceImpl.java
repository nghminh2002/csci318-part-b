package csci318.assignment.customerservice.service.Implementation;

import csci318.assignment.customerservice.model.Product;
import csci318.assignment.customerservice.model.ProductDetail;
import csci318.assignment.customerservice.repository.ProductDetailRepository;
import csci318.assignment.customerservice.repository.ProductRepository;
import csci318.assignment.customerservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    // Save new product into the database
    @Override
    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    // Update product by id
    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        // 1. Find existing product
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(RuntimeException::new);

        // 2. Check if the product category needs to be updated
        // If yes, replace old product category with the new product category
        if (updatedProduct.getProductCategory() != null) {
            existingProduct.setProductCategory(updatedProduct.getProductCategory());
        }

        // 3. Check if the name needs to be updated
        // If yes, replace old name with the new name
        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }

        // 3. Check if the price needs to be updated
        // If yes, replace old price with the new price
        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }

        // 4. Save updated product into the database
        return productRepository.save(existingProduct);
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

    // Save new product detail to the database
    @Override
    public ProductDetail createProductDetail(ProductDetail newProductDetail) {
        return productDetailRepository.save(newProductDetail);
    }

    // Update product detail by id
    @Override
    public ProductDetail updateProductDetail(Long productDetailId, ProductDetail updatedProductDetail) {
        // 1. Find existing product detail
        ProductDetail existingProductDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(RuntimeException::new);

        // 2. Check if the description needs to be updated
        // If yes, replace old description with the new description
        if (updatedProductDetail.getDescription() != null) {
            existingProductDetail.setDescription(updatedProductDetail.getDescription());
        }

        // 3. Check if the comment needs to be updated
        // If yes, replace old comment with the new comment
        if (updatedProductDetail.getComment() != null) {
            existingProductDetail.setComment(updatedProductDetail.getComment());
        }

        // 4. Save updated product detail to database
        return productDetailRepository.save(existingProductDetail);
    }

    // Get product detail by id
    @Override
    public ProductDetail getProductDetail(Long productDetailId) {
        return productDetailRepository.findById(productDetailId)
                .orElseThrow(RuntimeException::new);
    }

    // Get all product details
    @Override
    public List<ProductDetail> getAllProductDetails() {
        return productDetailRepository.findAll();
    }
}
