package com.cyanelix.railwatch.service.times;

import com.cyanelix.railwatch.TrainTime;

import javax.inject.Inject;

public class TrainTimesService {
    @Inject
    TrainTimesClient trainTimesClient;

    @Inject
    public TrainTimesService() { }

    public TrainTime[] getTrainTimes() {
        return trainTimesClient.getTrainTimes();
    }
}
