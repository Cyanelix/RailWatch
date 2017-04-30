package com.cyanelix.railwatch.config;

import java.net.URI;
import java.net.URISyntaxException;

public class Urls {
    private static final String BASE_URL = "http://railwatch.cyanelix.com";

    public String getDeparturesUrl(String from, String to) {
        return String.format("%s/departures?from=%s&to=%s", BASE_URL, from, to);
    }

    public URI getSchedulesURI() {
        try {
            return new URI(String.format("%s/schedules", BASE_URL));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
