package edu.uz.inz.application;

import edu.uz.inz.application.command.CreateOrderCommand;
import edu.uz.inz.application.error.CustomerNotFoundException;
import edu.uz.inz.application.error.ProductNotFoundException;
import edu.uz.inz.domain.model.Customer;
import edu.uz.inz.domain.model.Order;
import edu.uz.inz.domain.model.OrderItem;
import edu.uz.inz.domain.model.PaymentType;
import edu.uz.inz.domain.model.Product;
import edu.uz.inz.domain.model.event.OrderCreatedEvent;
import edu.uz.inz.port.adapter.repository.write.CustomerWriteRepository;
import edu.uz.inz.port.adapter.repository.write.OrderWriteRepository;
import edu.uz.inz.port.adapter.repository.write.ProductWriteRepository;
import edu.uz.inz.port.adapter.rest.request.OrderItemRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrdersWriteApplicationService {

    private final OrderWriteRepository orderRepository;
    private final CustomerWriteRepository customerRepository;
    private final ProductWriteRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    public OrdersWriteApplicationService(final OrderWriteRepository orderRepository,
        final CustomerWriteRepository customerRepository,
        final ProductWriteRepository productRepository,
        final ApplicationEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public UUID create(final CreateOrderCommand command) {
        final Order order = saveEmptyOrder(command.getCustomerUuid(), command.getPaymentType());
        final Order orderWithItems = saveOrderWithItems(order, command.getOrderItemsRequest());
        eventPublisher.publishEvent(OrderCreatedEvent.from(orderWithItems));
        return orderWithItems.getUuid();
    }

    private Order saveOrderWithItems(final Order order,
        final List<OrderItemRequest> orderItemsRequest) {
        final List<OrderItem> orderItems = getOrderItems(orderItemsRequest, order);
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

    private Customer getCustomer(final UUID customerUuid) {
        final Customer customer = customerRepository.findOne(customerUuid);

        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException(customerUuid);
        }

        return customer;
    }

    private Product getProduct(final UUID productUuid) {
        final Product product = productRepository.findOne(productUuid);

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException(productUuid);
        }

        return product;
    }

    private Order saveEmptyOrder(final UUID userId, final PaymentType paymentType) {
        final Customer customer = getCustomer(userId);
        final List<OrderItem> orderItems = new ArrayList<>();

        final Order order = new Order(orderItems, customer, paymentType);
        return orderRepository.save(order);
    }

    private List<OrderItem> getOrderItems(final List<OrderItemRequest> orderItemsRequest,
        final Order order) {
        return orderItemsRequest
            .stream()
            .map((request) -> createOrderItem(request, order))
            .collect(Collectors.toList());
    }

    private OrderItem createOrderItem(final OrderItemRequest request, final Order order) {
        final Product product = getProduct(request.getProductId());
        return new OrderItem(request.getQuantity(), product, order);
    }
}
