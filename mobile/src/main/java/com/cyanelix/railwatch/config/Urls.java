package com.cyanelix.railwatch.config;

import java.net.URI;
import java.net.URISyntaxException;

public class Urls {
    private static final String BASE_URL = "http://railwatch.cyanelix.com";

    public URI getDeparturesUrl(String from, String to) {
        return createURI(String.format("%s/departures?from=%s&to=%s", BASE_URL, from, to));
    }

    public URI getSchedulesURI() {
        return createURI(String.format("%s/schedules", BASE_URL));
    }

    public URI getHeartbeatURI(String notificationTarget) {
        return createURI(String.format("%s/heartbeat/%s", BASE_URL, notificationTarget));
    }

    private URI createURI(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
