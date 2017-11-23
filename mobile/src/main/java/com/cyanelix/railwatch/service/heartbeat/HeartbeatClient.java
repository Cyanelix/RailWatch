package com.cyanelix.railwatch.service.heartbeat;

import com.cyanelix.railwatch.config.Urls;

import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class HeartbeatClient {
    private final RestTemplate restTemplate;

    private final Urls urls;

    @Inject
    public HeartbeatClient(RestTemplate restTemplate, Urls urls) {
        this.restTemplate = restTemplate;
        this.urls = urls;
    }

    public void sendHeartbeat(String notificationTarget) {
        restTemplate.put(urls.getHeartbeatURI(notificationTarget), null);
    }
}
