package edu.uz.inz.application;

import edu.uz.inz.application.error.CustomerNotFoundException;
import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.port.adapter.repository.read.CustomerReadModel;
import edu.uz.inz.port.adapter.repository.read.CustomerReadRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "read.source", havingValue = "nosql", matchIfMissing = true)
public class CustomersReadFromNoSqlApplicationService implements CustomersReadApplicationService {

    private final CustomerReadRepository customerReadRepository;

    public CustomersReadFromNoSqlApplicationService(
        final CustomerReadRepository customerReadRepository) {
        this.customerReadRepository = customerReadRepository;
    }

    @Override
    public List<CustomerReadModel> getCustomers() {
        return customerReadRepository.findAll();
    }

    @Override
    public CustomerReadModel getCustomer(final GetByIdQuery query) {
        final UUID uuid = query.getUuid();
        final CustomerReadModel customer = customerReadRepository.findOne(uuid.toString());

        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException(uuid);
        }

        return customer;
    }
}
