package com.cyanelix.railwatch.service.times;

import android.util.Log;

import com.cyanelix.railwatch.domain.TrainTime;

import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class TrainTimesClient {
    private final String url;

    private RestTemplate restTemplate;

    @Inject
    public TrainTimesClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public TrainTime[] getTrainTimes() {
        try {
            return restTemplate.getForObject(url, TrainTime[].class);
        } catch (Exception e) {
            Log.e("TrainTimesClient", e.getMessage(), e);
            return null;
        }
    }
}
