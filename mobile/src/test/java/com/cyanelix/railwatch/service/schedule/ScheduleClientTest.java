package com.cyanelix.railwatch.service.schedule;

import com.cyanelix.railwatch.client.ScheduleClient;
import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.Schedule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ScheduleClientTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Urls urls;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleClient scheduleClient;

    @Test
    public void createSchedule_putRequestSent() throws URISyntaxException {
        // Given...
        URI uri = new URI("http://example.com/schedules");
        given(urls.getSchedulesURI()).willReturn(uri);
        Schedule schedule = new Schedule("11:00", "12:00", new String[] {"Monday"}, "FOO", "BAR", "target");

        // When...
        scheduleClient.createSchedule(schedule);

        // Then...
        verify(restTemplate).put(uri, schedule);
    }

    @Test
    public void getSchedules_getRequestSent() throws URISyntaxException {
        // Given...
        URI uri = new URI("http://example.com/schedules");
        given(urls.getSchedulesURI()).willReturn(uri);
        Schedule schedule = new Schedule("11:00", "12:00", new String[] {"Monday"}, "FOO", "BAR", "target");

        ResponseEntity<List<Schedule>> responseEntity = new ResponseEntity<>(Collections.singletonList(schedule), HttpStatus.OK);

        given(restTemplate.exchange(uri, HttpMethod.GET, null, ScheduleClient.SCHEDULES_LIST_TYPE))
                .willReturn(responseEntity);

        // When...
        List<Schedule> schedules = scheduleClient.getSchedules();

        // Then...
        assertThat(schedules.size(), is(1));
        assertThat(schedules.get(0), is(schedule));
    }
}