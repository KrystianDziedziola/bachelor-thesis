package edu.uz.inz.port.adapter.repository.read;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customers")
public class CustomerReadModel {

    @Id
    private final String uuid;
    private final String name;
    private final AddressReadModel address;
    private final BigDecimal balance;
    private final List<OrderReadModel> orders;

    public CustomerReadModel addOrder(final OrderReadModel order) {
        final List<OrderReadModel> orders = createOrders();
        orders.add(order);

        return new CustomerReadModel(uuid, name, address, balance, orders);
    }

    private List<OrderReadModel> createOrders() {
        if (Objects.isNull(orders)) {
            return new ArrayList<>();
        }

        return orders;
    }
}
