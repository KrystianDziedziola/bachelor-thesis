package edu.uz.inz.domain.model.event;

import edu.uz.inz.domain.model.Customer;
import edu.uz.inz.domain.model.Order;
import edu.uz.inz.domain.model.OrderItem;
import edu.uz.inz.domain.model.PaymentType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderCreatedEvent {

    private final UUID uuid;
    private final UUID customerUuid;
    private final LocalDateTime dateTime;
    private final List<OrderItem> orderItems;
    private final PaymentType paymentType;

    public OrderCreatedEvent(final UUID uuid, final UUID customerUuid,
        final LocalDateTime dateTime, final List<OrderItem> orderItems,
        final PaymentType paymentType) {
        this.uuid = uuid;
        this.customerUuid = customerUuid;
        this.dateTime = dateTime;
        this.orderItems = orderItems;
        this.paymentType = paymentType;
    }

    public static OrderCreatedEvent from(final Order order) {
        final Customer customer = order.getCustomer();

        return new OrderCreatedEvent(order.getUuid(), customer.getUuid(), order.getDateTime(),
            order.getOrderItems(), order.getPaymentType());
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getCustomerUuid() {
        return customerUuid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
