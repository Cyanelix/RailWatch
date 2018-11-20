package com.cyanelix.railwatch.client;

import com.cyanelix.railwatch.config.Urls;

import org.springframework.web.client.RestTemplate;

class AbstractClient {
    protected final RestTemplate restTemplate;
    protected final Urls urls;

    AbstractClient(RestTemplate restTemplate, Urls urls) {
        this.restTemplate = restTemplate;
        this.urls = urls;
    }
}
