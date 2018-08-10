package edu.uz.inz.port.adapter.rest.request;

import edu.uz.inz.application.command.CreateOrderCommand;
import edu.uz.inz.domain.model.PaymentType;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateOrderRequest {

    private final UUID customerId;
    private final List<OrderItemRequest> orderItems;
    private final PaymentType paymentType;

    public CreateOrderCommand asCommand() {
        return new CreateOrderCommand(customerId, orderItems, paymentType);
    }
}
