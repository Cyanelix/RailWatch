package com.cyanelix.railwatch.service.times;

import android.util.Log;

import com.cyanelix.railwatch.TrainTime;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class TrainTimesClient {
    @Inject
    public TrainTimesClient() { }

    public TrainTime[] getTrainTimes() {
        try {
            final String url = "http://railwatch.cyanelix.com/departures?from=KYN&to=BRI";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            TrainTime[] trainTimes = restTemplate.getForObject(url, TrainTime[].class);
            return trainTimes;
        } catch (Exception e) {
            Log.e("ChooseStationsActivity", e.getMessage(), e);
            return null;
        }
    }
}
