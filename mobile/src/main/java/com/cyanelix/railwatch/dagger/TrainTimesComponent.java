package com.cyanelix.railwatch.dagger;

import com.cyanelix.railwatch.ChooseStationsActivity;
import com.cyanelix.railwatch.DeparturesActivity;
import com.cyanelix.railwatch.MainActivity;

import dagger.Component;

@Component(modules = TrainTimesModule.class)
public interface TrainTimesComponent {
    void inject(MainActivity mainActivity);

    void inject(ChooseStationsActivity chooseStationsActivity);

    void inject(DeparturesActivity departuresActivity);
}
