package com.cyanelix.railwatch.service.heartbeat;

import com.cyanelix.railwatch.config.Urls;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class HeartbeatClientTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Urls urls;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HeartbeatClient heartbeatClient;

    @Test
    public void sendHeartbeat_heartbeatSent() throws URISyntaxException {
        // Given...
        URI uri = new URI("http://example.com/heartbeat/notification-to");
        given(urls.getHeartbeatURI("notification-to")).willReturn(uri);

        // When...
        heartbeatClient.sendHeartbeat("notification-to");

        // Then...
        verify(restTemplate).put(uri, null);
    }
}