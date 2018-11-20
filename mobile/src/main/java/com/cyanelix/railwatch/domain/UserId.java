package com.cyanelix.railwatch.domain;

import java.net.URI;
import java.util.UUID;

public class UserId {
    private final UUID uuid;

    public UserId(URI userUri) {
        String path = userUri.getPath();
        uuid = UUID.fromString(path.substring(path.lastIndexOf('/') + 1));
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUuidAsString() {
        return uuid.toString();
    }
}
