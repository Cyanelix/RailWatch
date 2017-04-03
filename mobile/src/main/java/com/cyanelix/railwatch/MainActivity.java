package com.cyanelix.railwatch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cyanelix.railwatch.domain.TrainTime;
import com.cyanelix.railwatch.service.notification.NotificationService;
import com.cyanelix.railwatch.service.times.TrainTimesService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    TrainTimesService trainTimesService;

    @Inject
    NotificationService notificationService;

    private ListView trainTimesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RailWatchApp) getApplication()).getTrainTimesComponent().inject(this);

        setContentView(R.layout.activity_main);
        trainTimesList = (ListView) findViewById(R.id.train_times);

        getTrainTimes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_choose_stations) {
            chooseStations();
            return true;
        }

        if (item.getItemId() == R.id.action_refresh) {
            getTrainTimes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chooseStations() {
        Intent intent = new Intent(this, ChooseStationsActivity.class);
        startActivity(intent);
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
            ArrayAdapter<TrainTime> timesAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, trainTimes);
            trainTimesList.setAdapter(timesAdapter);

            notificationService.notify(MainActivity.this, trainTimes);
        }

    }
}
