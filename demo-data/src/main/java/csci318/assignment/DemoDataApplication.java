package csci318.assignment;

import csci318.assignment.dto.OrderRequestDTO;
import csci318.assignment.dto.OrderResponseDTO;
import csci318.assignment.dto.ProductRequestDTO;
import csci318.assignment.dto.ProductResponseDTO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootApplication
public class DemoDataApplication {

    public static void main(String[] args) throws InterruptedException {

        final String productUrl = "http://localhost:8081/product";
        final String orderUrl = "http://localhost:8082/order";
        RestTemplate restTemplate = new RestTemplate();

        ProductRequestDTO[] products = {
                new ProductRequestDTO("Meat", "Chicken", 10.5, "Free cage chicken", "Produced in Australia"),
                new ProductRequestDTO("Fruit", "Apple", 0.8, "Crispy red apple", "Grown in USA"),
                new ProductRequestDTO("Vegetable", "Carrot", 0.5, "Orange crunchy carrot", "Grown Locally"),
                new ProductRequestDTO("Dairy", "Milk", 1.2, "Fresh milk", "Produced in Canada"),
                new ProductRequestDTO("Grain", "Rice", 1.5, "Long grain rice", "Imported from Asia"),
                new ProductRequestDTO("Meat", "Beef", 15.0, "Grass-fed beef", "Sourced from New Zealand"),
                new ProductRequestDTO("Fruit", "Orange", 0.7, "Juicy orange", "Imported from Spain"),
                new ProductRequestDTO("Vegetable", "Broccoli", 1.3, "Green broccoli", "Organic"),
                new ProductRequestDTO("Dairy", "Cheese", 3.0, "Matured cheddar", "Produced in France"),
                new ProductRequestDTO("Grain", "Wheat", 0.9, "Healthy whole wheat", "Grown in USA"),
                new ProductRequestDTO("Meat", "Pork", 12.0, "Organic pork", "Sourced from Germany"),
                new ProductRequestDTO("Fruit", "Banana", 0.6, "Yellow ripe banana", "Grown in Ecuador"),
                new ProductRequestDTO("Vegetable", "Spinach", 1.2, "Fresh spinach", "Organic"),
                new ProductRequestDTO("Dairy", "Yogurt", 1.8, "Greek yogurt", "Produced in Greece"),
                new ProductRequestDTO("Grain", "Oats", 0.7, "Rolled oats", "Imported from Scotland"),
                new ProductRequestDTO("Meat", "Lamb", 17.0, "Grass-fed lamb", "Sourced from New Zealand"),
                new ProductRequestDTO("Fruit", "Strawberry", 1.4, "Red juicy strawberries", "Grown in California"),
                new ProductRequestDTO("Vegetable", "Potato", 0.5, "Starchy potatoes", "Grown Locally"),
                new ProductRequestDTO("Dairy", "Butter", 2.5, "Creamy unsalted butter", "Produced in Ireland"),
                new ProductRequestDTO("Grain", "Corn", 0.8, "Sweet corn kernels", "Grown in USA"),
        };

        for (ProductRequestDTO product : products) {
            ProductResponseDTO newProduct = restTemplate.postForObject(productUrl, product, ProductResponseDTO.class);
            String message = String.format("Product [ID = %s]: Category %s, Name %s",
                    newProduct.getProductId(), newProduct.getProductCategory(), newProduct.getName());
            System.out.println(message);
            Thread.sleep(500);
        }

        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            Long randomSupplierId = 1L + random.nextInt(2);
            Long randomProductId = 1L + random.nextInt(20);
            Integer randomQuantity = random.nextInt(100) + 1;
            OrderRequestDTO newOrderRequest = new OrderRequestDTO(randomSupplierId, randomProductId, randomQuantity);
            OrderResponseDTO newOrder = restTemplate.postForObject(orderUrl, newOrderRequest, OrderResponseDTO.class);
            String message = String.format("Order [ID = %s]: Company %s, Product %s",
                    newOrder.getOrderId(), newOrder.getCompanyName(), newOrder.getName());
            System.out.println(message);
            Thread.sleep(500);
        }
    }

}
