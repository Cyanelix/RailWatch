package com.cyanelix.railwatch;

import android.app.Application;

import com.cyanelix.railwatch.dagger.TrainTimesComponent;
import com.cyanelix.railwatch.dagger.TrainTimesModule;
import com.cyanelix.railwatch.dagger.DaggerTrainTimesComponent;

public class RailWatchApp extends Application {
    private TrainTimesComponent trainTimesComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        trainTimesComponent = DaggerTrainTimesComponent.builder()
                .trainTimesModule(new TrainTimesModule())
                .build();
    }

    public TrainTimesComponent getTrainTimesComponent() {
        return trainTimesComponent;
    }
}
