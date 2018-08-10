package edu.uz.inz.application.command;

import edu.uz.inz.domain.model.Address;
import java.math.BigDecimal;

public class CreateCustomerCommand implements Command {

    private final String name;
    private final Address address;
    private final BigDecimal balance;

    public CreateCustomerCommand(final String name, final Address address,
        final BigDecimal balance) {
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
