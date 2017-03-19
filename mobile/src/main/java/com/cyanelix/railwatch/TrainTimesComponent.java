package com.cyanelix.railwatch;

import com.cyanelix.railwatch.service.times.TrainTimesClient;
import com.cyanelix.railwatch.service.times.TrainTimesService;

import dagger.Component;

@Component
public interface TrainTimesComponent {
    TrainTimesService trainTimesService();
    void inject(MainActivity mainActivity);
}
