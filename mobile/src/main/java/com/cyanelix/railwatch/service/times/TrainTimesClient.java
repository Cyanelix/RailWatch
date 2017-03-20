package com.cyanelix.railwatch.service.times;

import android.util.Log;

import com.cyanelix.railwatch.TrainTime;

import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class TrainTimesClient {
    private static final String URL = "http://railwatch.cyanelix.com/departures?from=KYN&to=BRI";

    @Inject
    RestTemplate restTemplate;

    @Inject
    public TrainTimesClient() { }

    public TrainTime[] getTrainTimes() {
        try {
            return restTemplate.getForObject(URL, TrainTime[].class);
        } catch (Exception e) {
            Log.e("ChooseStationsActivity", e.getMessage(), e);
            return null;
        }
    }
}
