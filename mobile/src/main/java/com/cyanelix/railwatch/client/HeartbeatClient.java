package com.cyanelix.railwatch.client;

import com.cyanelix.railwatch.config.Urls;

import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class HeartbeatClient extends AbstractClient {

    @Inject
    public HeartbeatClient(RestTemplate restTemplate, Urls urls) {
        super(restTemplate, urls);
    }

    public void sendHeartbeat(String notificationTarget) {
        restTemplate.put(urls.getHeartbeatURI(notificationTarget), null);
    }
}
