package com.cyanelix.railwatch.client;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.User;
import com.cyanelix.railwatch.domain.UserId;

import org.springframework.web.client.RestTemplate;

import java.net.URI;

import javax.inject.Inject;

public class UserClient extends AbstractClient {
    @Inject
    public UserClient(RestTemplate restTemplate, Urls urls) {
        super(restTemplate, urls);
    }

    public UserId createUser(User user) {
        URI uri = restTemplate.postForLocation(urls.getUsersURI(), user);
        return new UserId(uri);
    }
}
