package com.cyanelix.railwatch;

import android.app.Application;

public class RailWatchApp extends Application {
    private TrainTimesComponent trainTimesComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        trainTimesComponent = DaggerTrainTimesComponent.builder().build();
    }

    public TrainTimesComponent getTrainTimesComponent() {
        return trainTimesComponent;
    }
}
