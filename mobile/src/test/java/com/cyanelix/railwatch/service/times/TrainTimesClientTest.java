package com.cyanelix.railwatch.service.times;

import com.cyanelix.railwatch.TrainTime;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TrainTimesClientTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TrainTimesClient trainTimesClient;

    @Test
    public void noTrainTimesRetrieved_emptyArrayReturned() {
        // Given...
        given(restTemplate.getForObject("http://railwatch.cyanelix.com/departures?from=KYN&to=BRI", TrainTime[].class))
                .willReturn(new TrainTime[0]);

        // When...
        TrainTime[] trainTimes = trainTimesClient.getTrainTimes();

        // Then...
        assertThat(trainTimes.length, is(0));
    }

    @Test
    public void trainTimesRetrieved_arrayReturned() {
        // Given...
        TrainTime expected = mock(TrainTime.class);
        given(restTemplate.getForObject("http://railwatch.cyanelix.com/departures?from=KYN&to=BRI", TrainTime[].class))
                .willReturn(new TrainTime[] { expected });

        // When...
        TrainTime[] trainTimes = trainTimesClient.getTrainTimes();

        // Then...
        assertThat(trainTimes.length, is(1));
        assertThat(trainTimes[0], is(expected));
    }
}