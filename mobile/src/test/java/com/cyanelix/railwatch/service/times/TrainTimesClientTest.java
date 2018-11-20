package com.cyanelix.railwatch.service.times;

import com.cyanelix.railwatch.client.TrainTimesClient;
import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.TrainTime;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TrainTimesClientTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Urls urls;

    @InjectMocks
    private TrainTimesClient trainTimesClient;

    @Test
    public void noTrainTimesRetrieved_emptyArrayReturned() throws URISyntaxException {
        // Given...
        URI uri = new URI("http://example.com/departures?from=KYN&to=BRI");

        given(urls.getDeparturesUrl("KYN", "BRI")).willReturn(uri);
        given(restTemplate.getForObject(uri, TrainTime[].class)).willReturn(new TrainTime[0]);

        // When...
        TrainTime[] trainTimes = trainTimesClient.getTrainTimes();

        // Then...
        assertThat(trainTimes.length, is(0));
    }

    @Test
    public void trainTimesRetrieved_arrayReturned() throws URISyntaxException {
        // Given...
        URI uri = new URI("http://example.com/departures?from=KYN&to=BRI");
        TrainTime expected = mock(TrainTime.class);
        given(urls.getDeparturesUrl("KYN", "BRI")).willReturn(uri);
        given(restTemplate.getForObject(uri, TrainTime[].class)).willReturn(new TrainTime[] { expected });

        // When...
        TrainTime[] trainTimes = trainTimesClient.getTrainTimes();

        // Then...
        assertThat(trainTimes.length, is(1));
        assertThat(trainTimes[0], is(expected));
    }
}