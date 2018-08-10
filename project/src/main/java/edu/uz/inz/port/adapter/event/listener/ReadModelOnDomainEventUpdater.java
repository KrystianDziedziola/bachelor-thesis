package edu.uz.inz.port.adapter.event.listener;

import edu.uz.inz.domain.model.event.CustomerCreatedEvent;
import edu.uz.inz.domain.model.event.OrderCreatedEvent;
import edu.uz.inz.domain.model.event.ProductCreatedEvent;
import edu.uz.inz.port.adapter.repository.read.CustomerReadModel;
import edu.uz.inz.port.adapter.repository.read.CustomerReadModelUpdateRepository;
import edu.uz.inz.port.adapter.repository.read.OrderItemReadModel;
import edu.uz.inz.port.adapter.repository.read.OrderReadModel;
import edu.uz.inz.port.adapter.repository.read.ProductReadModelUpdateRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReadModelOnDomainEventUpdater {

    private final ProductReadModelUpdateRepository productRepository;
    private final CustomerReadModelUpdateRepository customerRepository;

    public ReadModelOnDomainEventUpdater(
        final ProductReadModelUpdateRepository productRepository,
        final CustomerReadModelUpdateRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @EventListener
    public void handle(final ProductCreatedEvent event) {
        productRepository.save(event.asReadModel());
    }

    @EventListener
    public void handle(final CustomerCreatedEvent event) {
        customerRepository.save(event.asReadModel());
    }

    @EventListener
    public void handle(final OrderCreatedEvent event) {
        final CustomerReadModel customer = findCustomer(event);
        final CustomerReadModel customerWithNewOrder = addOrder(event, customer);

        customerRepository.save(customerWithNewOrder);
    }

    private CustomerReadModel findCustomer(final OrderCreatedEvent event) {
        final UUID customerUuid = event.getCustomerUuid();
        return customerRepository.findOne(customerUuid.toString());
    }

    private CustomerReadModel addOrder(final OrderCreatedEvent event,
        final CustomerReadModel customer) {
        final UUID uuid = event.getUuid();

        final List<OrderItemReadModel> orderItems = event.getOrderItems()
            .stream()
            .map(OrderItemReadModel::from)
            .collect(Collectors.toList());

        final OrderReadModel order = new OrderReadModel(uuid.toString(),
            event.getDateTime().toString(), orderItems, event.getPaymentType());

        return customer.addOrder(order);
    }
}
