package edu.uz.inz.application.command;

import edu.uz.inz.domain.model.PaymentType;
import edu.uz.inz.port.adapter.rest.request.OrderItemRequest;
import java.util.List;
import java.util.UUID;

public class CreateOrderCommand implements Command {

    private final UUID customerUuid;
    private final List<OrderItemRequest> orderItemsRequest;
    private final PaymentType paymentType;

    public CreateOrderCommand(final UUID customerUuid,
        final List<OrderItemRequest> orderItemsRequest,
        final PaymentType paymentType) {
        this.customerUuid = customerUuid;
        this.orderItemsRequest = orderItemsRequest;
        this.paymentType = paymentType;
    }

    public UUID getCustomerUuid() {
        return customerUuid;
    }

    public List<OrderItemRequest> getOrderItemsRequest() {
        return orderItemsRequest;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
