package edu.uz.inz.domain.model.event;

import edu.uz.inz.domain.model.Product;
import edu.uz.inz.port.adapter.repository.read.ProductReadModel;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductCreatedEvent implements DomainEvent {

    private final UUID uuid;
    private final String name;
    private final BigDecimal price;
    private final String description;

    private ProductCreatedEvent(
        final UUID uuid,
        final String name,
        final BigDecimal price,
        final String description) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static ProductCreatedEvent from(final Product product) {
        return new ProductCreatedEvent(
            product.getUuid(),
            product.getName(),
            product.getPrice(),
            product.getDescription());
    }

    public ProductReadModel asReadModel() {
        return new ProductReadModel(uuid.toString(), name, price, description);
    }
}
