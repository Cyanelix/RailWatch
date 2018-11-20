package com.cyanelix.railwatch.domain;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserIdTest {
    @Test
    public void validUri_idExtracted() throws URISyntaxException {
        // Given...
        String idString = "9d35efcb-77da-4690-a9c9-b57dcc5ed50c";
        URI uri = new URI("http://example.com/user/" + idString);

        // When...
        UserId userId = new UserId(uri);

        // Then...
        assertThat(userId.getUuid(), is(UUID.fromString(idString)));
        assertThat(userId.getUuidAsString(), is(idString));
    }
}