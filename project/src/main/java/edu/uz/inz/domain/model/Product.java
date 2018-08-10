package edu.uz.inz.domain.model;

import edu.uz.inz.application.command.CreateProductCommand;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    @NotNull
    private UUID uuid;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String description;

    public Product(
        final String name,
        final BigDecimal price,
        final String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static Product fromCommand(final CreateProductCommand command) {
        return new Product(
            command.getName(),
            command.getPrice(),
            command.getDescription());
    }
}
