package edu.uz.inz.application;

import edu.uz.inz.application.error.CustomerNotFoundException;
import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.domain.model.Customer;
import edu.uz.inz.port.adapter.repository.read.AddressReadModel;
import edu.uz.inz.port.adapter.repository.read.CustomerReadFromRdbmsRepository;
import edu.uz.inz.port.adapter.repository.read.CustomerReadModel;
import edu.uz.inz.port.adapter.repository.read.OrderReadModel;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "read.source", havingValue = "rdbms")
public class CustomersReadFromRdbmsApplicationService implements CustomersReadApplicationService {

    private final CustomerReadFromRdbmsRepository customerReadRepository;

    public CustomersReadFromRdbmsApplicationService(
        final CustomerReadFromRdbmsRepository customerReadRepository) {
        this.customerReadRepository = customerReadRepository;
    }

    @Override
    public List<CustomerReadModel> getCustomers() {
        final Iterable<Customer> customers = customerReadRepository.findAll();

        return StreamSupport
            .stream(customers.spliterator(), false)
            .map(this::toReadModel)
            .collect(Collectors.toList());
    }

    @Override
    public CustomerReadModel getCustomer(final GetByIdQuery query) {
        final UUID uuid = query.getUuid();
        final Customer customer = customerReadRepository.findOne(uuid);

        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException(uuid);
        }
        return toReadModel(customer);
    }

    private CustomerReadModel toReadModel(final Customer customer) {
        final AddressReadModel address = AddressReadModel.from(customer.getAddress());
        final List<OrderReadModel> orders = customer
            .getOrders()
            .stream()
            .map(OrderReadModel::from)
            .collect(Collectors.toList());

        return new CustomerReadModel(customer.getUuid().toString(), customer.getName(),
            address, customer.getBalance(), orders);
    }
}
