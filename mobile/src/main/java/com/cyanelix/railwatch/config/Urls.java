package com.cyanelix.railwatch.config;

public class Urls {
    private static final String BASE_URL = "http://railwatch.cyanelix.com";

    public String getDeparturesUrl(String from, String to) {
        return String.format("%s/departures?from=%s&to=%s", BASE_URL, from, to);
    }
}
