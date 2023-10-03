package csci318.assignment.salesboundedcontext.interfaces.rest.transform;

import csci318.assignment.salesboundedcontext.domain.model.aggregates.Product;
import csci318.assignment.salesboundedcontext.domain.model.commands.CreateProductCommand;
import csci318.assignment.salesboundedcontext.domain.model.commands.UpdateProductCommand;
import csci318.assignment.salesboundedcontext.domain.model.valueobject.ProductDetail;
import csci318.assignment.salesboundedcontext.interfaces.rest.dto.ProductRequestDTO;

public class ProductCommandDTOAssembler {

    public static CreateProductCommand toCommandFromDTO(ProductRequestDTO request) {
        ProductDetail productDetail = new ProductDetail(
                request.getDescription(),
                request.getComment()
        );
        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setProductCategory(request.getProductCategory());
        createProductCommand.setName(request.getName());
        createProductCommand.setPrice(request.getPrice());
        createProductCommand.setProductDetail(productDetail);
        return createProductCommand;
    }

    public static UpdateProductCommand toCommandFromDTO(
            Product originalProduct,
            ProductRequestDTO request
    ) {
        UpdateProductCommand updateProductCommand = new UpdateProductCommand(originalProduct.getId());
        // 1. Check if the product category needs to be updated
        // If yes, replace old product category with the new product category
        if (request.getProductCategory()!= null) {
            updateProductCommand.setProductCategory(request.getProductCategory());
        } else {
            updateProductCommand.setProductCategory(originalProduct.getProductCategory());
        }

        // 2. Check if the name needs to be updated
        // If yes, replace old name with the new name
        if (request.getName() != null) {
            updateProductCommand.setName(request.getName());
        } else {
            updateProductCommand.setName(originalProduct.getName());
        }

        // 3. Check if the price needs to be updated
        // If yes, replace old price with the new price
        if (request.getPrice() != null) {
            updateProductCommand.setPrice(request.getPrice());
        } else {
            updateProductCommand.setPrice(originalProduct.getPrice());
        }

        // 4. Check if the product detail needs to be updated
        // If yes, replace old detail with the new detail
        ProductDetail originProductDetail = originalProduct.getProductDetail();
        if (request.getComment() != null || request.getDescription() != null) {
            String newDescription = request.getDescription() != null
                    ? request.getDescription()
                    : originProductDetail.getDescription();
            String newComment = request.getComment() != null
                    ? request.getComment()
                    : originProductDetail.getComment();
            updateProductCommand.setProductDetail(new ProductDetail(newDescription, newComment));
        } else {
            updateProductCommand.setProductDetail(originProductDetail);
        }

        return updateProductCommand;
    }
}
