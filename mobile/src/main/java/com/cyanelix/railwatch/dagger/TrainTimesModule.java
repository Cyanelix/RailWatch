package com.cyanelix.railwatch.dagger;

import com.cyanelix.railwatch.config.Urls;
import com.cyanelix.railwatch.service.schedule.ScheduleClient;
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

        return new TrainTimesClient(restTemplate, new Urls());
    }

    @Provides
    public ScheduleClient provideScheduleClient() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return new ScheduleClient(restTemplate, new Urls());
    }
}
