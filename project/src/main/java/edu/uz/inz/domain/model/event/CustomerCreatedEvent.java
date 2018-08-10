package edu.uz.inz.domain.model.event;

import edu.uz.inz.domain.model.Address;
import edu.uz.inz.domain.model.Customer;
import edu.uz.inz.port.adapter.repository.read.AddressReadModel;
import edu.uz.inz.port.adapter.repository.read.CustomerReadModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CustomerCreatedEvent implements DomainEvent {

    private final UUID uuid;
    private final String name;
    private final String street;
    private final String city;
    private final String postCode;
    private final BigDecimal balance;

    public CustomerCreatedEvent(final UUID uuid, final String name,
        final String street, final String city, final String postCode, final BigDecimal balance) {
        this.uuid = uuid;
        this.name = name;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.balance = balance;
    }

    public static CustomerCreatedEvent from(final Customer customer) {
        final Address address = customer.getAddress();
        return new CustomerCreatedEvent(customer.getUuid(), customer.getName(), address.getStreet(),
            address.getCity(), address.getPostCode(), customer.getBalance());
    }

    public CustomerReadModel asReadModel() {
        final AddressReadModel address = new AddressReadModel(street, city, postCode);
        return new CustomerReadModel(uuid.toString(), name, address, balance, new ArrayList<>());
    }
}
