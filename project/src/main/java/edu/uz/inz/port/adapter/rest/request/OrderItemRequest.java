package edu.uz.inz.port.adapter.rest.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderItemRequest {

    private final UUID productId;
    private final Integer quantity;
}
