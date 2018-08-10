package edu.uz.inz.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    @NotNull
    private UUID uuid;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @NotNull
    private List<OrderItem> orderItems;

    @NotNull
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "customer_uuid")
    @NotNull
    private Customer customer;

    public Order(final List<OrderItem> orderItems, final Customer customer,
        final PaymentType paymentType) {
        this.orderItems = orderItems;
        this.customer = customer;
        this.paymentType = paymentType;
        dateTime = LocalDateTime.now();
    }
}
