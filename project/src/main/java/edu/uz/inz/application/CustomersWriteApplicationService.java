package edu.uz.inz.application;

import edu.uz.inz.application.command.CreateCustomerCommand;
import edu.uz.inz.domain.model.Customer;
import edu.uz.inz.domain.model.Order;
import edu.uz.inz.domain.model.event.CustomerCreatedEvent;
import edu.uz.inz.port.adapter.repository.write.CustomerWriteRepository;
import java.util.ArrayList;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomersWriteApplicationService {

    private final CustomerWriteRepository customerWriteRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CustomersWriteApplicationService(
        final CustomerWriteRepository customerWriteRepository,
        final ApplicationEventPublisher eventPublisher) {
        this.customerWriteRepository = customerWriteRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public UUID create(final CreateCustomerCommand command) {
        final Customer customer = saveCustomer(command);

        eventPublisher.publishEvent(CustomerCreatedEvent.from(customer));
        return customer.getUuid();
    }

    private Customer saveCustomer(final CreateCustomerCommand command) {
        final ArrayList<Order> emptyOrders = new ArrayList<>();
        final Customer entity = new Customer(command.getName(), command.getAddress(),
            command.getBalance(), emptyOrders);

        return customerWriteRepository.save(entity);
    }
}
