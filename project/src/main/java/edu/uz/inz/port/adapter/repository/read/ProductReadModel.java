package edu.uz.inz.port.adapter.repository.read;

import edu.uz.inz.domain.model.Product;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class ProductReadModel {

    @Id
    private final String uuid;
    private final String name;
    private final BigDecimal price;
    private final String description;

    public static ProductReadModel from(final Product product) {
        return new ProductReadModel(product.getUuid().toString(), product.getName(),
            product.getPrice(),
            product.getDescription());
    }
}
