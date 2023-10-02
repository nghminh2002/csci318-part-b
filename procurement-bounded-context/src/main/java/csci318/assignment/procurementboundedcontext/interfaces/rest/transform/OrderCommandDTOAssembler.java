package csci318.assignment.procurementboundedcontext.interfaces.rest.transform;

import csci318.assignment.procurementboundedcontext.domain.model.commands.CreateOrderCommand;
import csci318.assignment.procurementboundedcontext.domain.model.commands.UpdateOrderCommand;
import csci318.assignment.procurementboundedcontext.interfaces.rest.dto.OrderRequestDTO;

public class OrderCommandDTOAssembler {

    public static CreateOrderCommand toCommandFromDTO(OrderRequestDTO orderRequestDTO){
        return new CreateOrderCommand(
                orderRequestDTO.getQuantity(),
                orderRequestDTO.getSupplier(),
                orderRequestDTO.getProduct()
                );
    }

    public static UpdateOrderCommand toCommandFromDTO(String orderId){
        return new UpdateOrderCommand(orderId);
    }
}
