package com.cyanelix.railwatch.client;

import android.util.Log;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.domain.TrainTime;

import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class TrainTimesClient extends AbstractClient {
    @Inject
    public TrainTimesClient(RestTemplate restTemplate, Urls urls) {
        super(restTemplate, urls);
    }

    public TrainTime[] getTrainTimes() {
        try {
            return restTemplate.getForObject(urls.getDeparturesUrl("KYN", "BRI"), TrainTime[].class);
        } catch (RuntimeException e) {
            Log.e("TrainTimesClient", e.getMessage(), e);
            return null;
        }
    }
}
