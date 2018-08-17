package com.cyanelix.railwatch.service.schedule;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.Schedule;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.inject.Inject;

public class ScheduleClient {
    public static final ParameterizedTypeReference<List<Schedule>> SCHEDULES_LIST_TYPE
            = new ParameterizedTypeReference<List<Schedule>>() { };

    private final RestTemplate restTemplate;

    private final Urls urls;

    @Inject
    public ScheduleClient(RestTemplate restTemplate, Urls urls) {
        this.restTemplate = restTemplate;
        this.urls = urls;
    }

    public void createSchedule(Schedule schedule) {
        restTemplate.put(urls.getSchedulesURI(), schedule);
    }

    public List<Schedule> getSchedules() {
        ResponseEntity<List<Schedule>> responseEntity = restTemplate.exchange(urls.getSchedulesURI(), HttpMethod.GET, null, SCHEDULES_LIST_TYPE);
        return responseEntity.getBody();
    }
}
