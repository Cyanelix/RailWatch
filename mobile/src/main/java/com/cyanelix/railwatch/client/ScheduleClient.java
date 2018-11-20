package com.cyanelix.railwatch.client;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.Schedule;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.inject.Inject;

public class ScheduleClient extends AbstractClient {
    public static final ParameterizedTypeReference<List<Schedule>> SCHEDULES_LIST_TYPE
            = new ParameterizedTypeReference<List<Schedule>>() { };

    @Inject
    public ScheduleClient(RestTemplate restTemplate, Urls urls) {
        super(restTemplate, urls);
    }

    public void createSchedule(Schedule schedule) {
        restTemplate.put(urls.getSchedulesURI(), schedule);
    }

    public List<Schedule> getSchedules() {
        ResponseEntity<List<Schedule>> responseEntity = restTemplate.exchange(urls.getSchedulesURI(), HttpMethod.GET, null, SCHEDULES_LIST_TYPE);
        return responseEntity.getBody();
    }
}
