package csci318.assignment.procurementboundedcontext.application.internal.domainservices;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class OrderDomainService {

    public String generateOrderID(Long supplierId, Long productId) {
        String datePart = new SimpleDateFormat("ddMMyyyy").format(new Date());
        String supplierPart = String.format("%02d", supplierId);
        String productPart = String.format("%02d", productId);
        String randomPart = String.format("%03d", new Random().nextInt(1000));

        return "ORDER" + datePart + supplierPart + productPart + randomPart;
    }
}
