package com.cyanelix.railwatch.client;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.User;
import com.cyanelix.railwatch.domain.UserId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class UserClientTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Urls urls;

    @InjectMocks
    private UserClient userClient;

    private URI usersUri;

    @Before
    public void setup() throws URISyntaxException {
        usersUri = new URI("http://example.com/users");
        given(urls.getUsersURI()).willReturn(usersUri);
    }

    @Test
    public void shouldCreateUser() throws URISyntaxException {
        // Given...
        User user = new User("notification-to");

        String idString = "9d35efcb-77da-4690-a9c9-b57dcc5ed50c";
        given(restTemplate.postForLocation(usersUri, user))
                .willReturn(new URI(usersUri.getPath() + "/" + idString));

        // When...
        UserId userId = userClient.createUser(user);

        // Then...
        assertThat(userId.getUuid().toString(), is(idString));
    }
}