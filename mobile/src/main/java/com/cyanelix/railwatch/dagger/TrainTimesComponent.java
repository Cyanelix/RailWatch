package com.cyanelix.railwatch.dagger;

import com.cyanelix.railwatch.MainActivity;

import dagger.Component;

@Component(modules = TrainTimesModule.class)
public interface TrainTimesComponent {
    void inject(MainActivity mainActivity);
}
