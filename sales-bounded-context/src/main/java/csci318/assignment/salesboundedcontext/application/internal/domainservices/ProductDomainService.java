package csci318.assignment.salesboundedcontext.application.internal.domainservices;

import csci318.assignment.salesboundedcontext.domain.model.valueobject.ProductDetail;
import csci318.assignment.salesboundedcontext.interfaces.rest.dto.ProductRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductDomainService {
    public ProductDetail updateProductDetail(ProductDetail originalProductDetail, ProductRequestDTO request) {
        if (request.getComment() != null || request.getDescription() != null) {
            String newDescription = request.getDescription() != null
                    ? request.getDescription()
                    : originalProductDetail.getDescription();
            String newComment = request.getComment() != null
                    ? request.getComment()
                    : originalProductDetail.getComment();
            return new ProductDetail(newDescription, newComment);
        } else {
            return originalProductDetail;
        }
    }

}
