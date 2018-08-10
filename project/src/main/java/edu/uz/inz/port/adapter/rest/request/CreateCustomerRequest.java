package edu.uz.inz.port.adapter.rest.request;

import edu.uz.inz.application.command.CreateCustomerCommand;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCustomerRequest {

    private final String name;
    private final CustomerAddressRequest address;
    private final BigDecimal balance;

    public CreateCustomerCommand asCommand() {
        return new CreateCustomerCommand(name, address.toAddress(), balance);
    }
}
