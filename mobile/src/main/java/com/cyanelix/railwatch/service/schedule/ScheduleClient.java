package com.cyanelix.railwatch.service.schedule;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.Schedule;

import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class ScheduleClient {
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
}
