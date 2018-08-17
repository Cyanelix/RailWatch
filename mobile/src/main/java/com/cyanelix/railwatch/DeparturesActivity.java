package com.cyanelix.railwatch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cyanelix.railwatch.domain.TrainTime;
import com.cyanelix.railwatch.service.times.TrainTimesService;

import javax.inject.Inject;

public class DeparturesActivity extends AppCompatActivity {
    @Inject
    TrainTimesService trainTimesService;

    private ListView trainTimesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RailWatchApp) getApplication()).getTrainTimesComponent().inject(this);

        setContentView(R.layout.activity_departures);
        trainTimesList = (ListView) findViewById(R.id.train_times);

        getTrainTimes();
    }

    private void getTrainTimes() {
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, TrainTime[]> {
        @Override
        protected TrainTime[] doInBackground(Void... params) {
            return trainTimesService.getTrainTimes();
        }

        @Override
        protected void onPostExecute(TrainTime[] trainTimes) {
            ArrayAdapter<TrainTime> timesAdapter = new ArrayAdapter<>(DeparturesActivity.this, android.R.layout.simple_list_item_1, trainTimes);
            trainTimesList.setAdapter(timesAdapter);
        }
    }
}
