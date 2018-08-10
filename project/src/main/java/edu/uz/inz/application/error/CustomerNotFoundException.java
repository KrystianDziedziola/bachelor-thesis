package edu.uz.inz.application.error;

import java.text.MessageFormat;
import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final UUID uuid) {
        super(MessageFormat.format("Customer with uuid {0} not found", uuid));
    }
}