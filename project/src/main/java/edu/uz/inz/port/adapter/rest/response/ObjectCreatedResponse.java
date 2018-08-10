package edu.uz.inz.port.adapter.rest.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ObjectCreatedResponse {

    private final UUID uuid;
}
