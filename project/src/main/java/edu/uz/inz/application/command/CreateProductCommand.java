package edu.uz.inz.application.command;

import java.math.BigDecimal;

public class CreateProductCommand implements Command {

    private final String name;
    private final BigDecimal price;
    private final String description;

    public CreateProductCommand(
        final String name, final BigDecimal price, final String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
