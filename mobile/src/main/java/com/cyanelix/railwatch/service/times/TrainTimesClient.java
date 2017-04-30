package com.cyanelix.railwatch.service.times;

import android.util.Log;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.TrainTime;

import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class TrainTimesClient {
    private final Urls urls;

    private final RestTemplate restTemplate;

    @Inject
    public TrainTimesClient(RestTemplate restTemplate, Urls urls) {
        this.restTemplate = restTemplate;
        this.urls = urls;
    }

    public TrainTime[] getTrainTimes() {
        try {
            return restTemplate.getForObject(urls.getDeparturesUrl("KYN", "BRI"), TrainTime[].class);
        } catch (Exception e) {
            Log.e("TrainTimesClient", e.getMessage(), e);
            return null;
        }
    }
}
