package edu.uz.inz.port.adapter.repository.read;

import edu.uz.inz.domain.model.OrderItem;
import java.util.UUID;
import lombok.Data;

@Data
public class OrderItemReadModel {

    private final String uuid;
    private final Integer quantity;
    private final ProductReadModel product;

    public static OrderItemReadModel from(final OrderItem item) {
        final UUID uuid = item.getUuid();
        final Integer quantity = item.getQuantity();
        final ProductReadModel product = ProductReadModel.from(item.getProduct());

        return new OrderItemReadModel(uuid.toString(), quantity, product);
    }
}
