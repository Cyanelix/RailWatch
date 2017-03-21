package com.cyanelix.railwatch.dagger;

import com.cyanelix.railwatch.service.times.TrainTimesClient;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import dagger.Module;
import dagger.Provides;

@Module
public class TrainTimesModule {
    @Provides
    public TrainTimesClient provideTrainTimesClient() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return new TrainTimesClient(restTemplate, "http://railwatch.cyanelix.com/departures?from=KYN&to=BRI");
    }
}
