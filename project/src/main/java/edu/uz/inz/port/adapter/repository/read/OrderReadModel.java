package edu.uz.inz.port.adapter.repository.read;

import edu.uz.inz.domain.model.Order;
import edu.uz.inz.domain.model.PaymentType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class OrderReadModel {

    private final String uuid;
    private final String dateTime;
    private final List<OrderItemReadModel> items;
    private final PaymentType paymentType;

    public static OrderReadModel from(final Order order) {
        final LocalDateTime dateTime = order.getDateTime();
        final UUID uuid = order.getUuid();

        final List<OrderItemReadModel> orderItems = order
            .getOrderItems()
            .stream()
            .map(OrderItemReadModel::from)
            .collect(Collectors.toList());
        return new OrderReadModel(uuid.toString(), dateTime.toString(), orderItems,
            order.getPaymentType());
    }
}
