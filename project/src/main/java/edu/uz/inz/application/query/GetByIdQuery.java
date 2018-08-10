package edu.uz.inz.application.query;

import java.util.UUID;

public class GetByIdQuery implements Query {

    private final UUID uuid;

    public GetByIdQuery(final UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
