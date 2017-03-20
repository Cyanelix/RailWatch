package com.cyanelix.railwatch;

import com.cyanelix.railwatch.service.times.TrainTimesService;

import dagger.Component;

@Component
public interface TrainTimesComponent {
    void inject(MainActivity mainActivity);
}
