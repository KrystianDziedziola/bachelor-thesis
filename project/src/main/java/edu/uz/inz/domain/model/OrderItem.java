package edu.uz.inz.domain.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "orderItems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    @NotNull
    private UUID uuid;

    @NotNull
    private Integer quantity;

    @OneToOne
    @NotNull
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_uuid")
    @NotNull
    private Order order;

    public OrderItem(final Integer quantity, final Product product, final Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }
}
