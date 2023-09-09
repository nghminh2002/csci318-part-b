package csci318.assignment.service;

import csci318.assignment.controller.dto.ProductRequestDTO;
import csci318.assignment.model.Product;
import csci318.assignment.model.valueobject.ProductDetail;
import org.springframework.stereotype.Service;

@Service
public class ProductDomainService {

    public Product createFromDTO(ProductRequestDTO request) {
        ProductDetail productDetail = new ProductDetail(
                request.getDescription(),
                request.getComment()
        );

        Product product = new Product();
        product.setProductCategory(request.getProductCategory());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setProductDetail(productDetail);

        return product;
    }

    public  Product updateProduct(
            Product originalProduct,
            String productCategory,
            String name,
            Double price
    ) {
        // 1. Check if the product category needs to be updated
        // If yes, replace old product category with the new product category
        if (productCategory != null) {
            originalProduct.setProductCategory(productCategory);
        }

        // 2. Check if the name needs to be updated
        // If yes, replace old name with the new name
        if (name != null) {
            originalProduct.setName(name);
        }

        // 3. Check if the price needs to be updated
        // If yes, replace old price with the new price
        if (price != null) {
            originalProduct.setPrice(price);
        }

        return  originalProduct;
    }

    public ProductDetail updateProductDetail(
            ProductDetail originProductDetail,
            String comment,
            String description
    ) {
        if (comment != null || description != null) {
            String newDescription = description != null
                    ? description
                    : originProductDetail.getDescription();
            String newComment = comment != null
                    ? comment
                    : originProductDetail.getComment();
            return new ProductDetail(newDescription, newComment);
        }
        return originProductDetail;
    }
}
