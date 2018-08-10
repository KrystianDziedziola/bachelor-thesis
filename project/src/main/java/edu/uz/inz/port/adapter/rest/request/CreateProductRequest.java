package edu.uz.inz.port.adapter.rest.request;

import edu.uz.inz.application.command.CreateProductCommand;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductRequest {

    private final String name;
    private final BigDecimal price;
    private final String description;

    public CreateProductCommand asCommand() {
        return new CreateProductCommand(name, price, description);
    }
}
