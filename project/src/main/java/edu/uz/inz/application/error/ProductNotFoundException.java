package edu.uz.inz.application.error;

import java.text.MessageFormat;
import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final UUID uuid) {
        super(MessageFormat.format("Product with uuid {0} not found", uuid));
    }
}
