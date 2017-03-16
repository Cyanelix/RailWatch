package com.cyanelix.railwatch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {
    private TextView trainTimeMessageText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cyanelix.railwatch.R.layout.activity_main);
        trainTimeMessageText = (TextView) findViewById(com.cyanelix.railwatch.R.id.content_value);
        getTrainTimes();
    }

    private void chooseStations() {
        Intent intent = new Intent(this, ChooseStationsActivity.class);
        startActivity(intent);
    }

    private void getTrainTimes() {
        trainTimeMessageText.setText(R.string.getting_times);
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, TrainTime[]> {
        @Override
        protected TrainTime[] doInBackground(Void... params) {
            try {
                final String url = "http://railwatch.cyanelix.com/departures?from=KYN&to=BRI";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                TrainTime[] trainTimes = restTemplate.getForObject(url, TrainTime[].class);
                return trainTimes;
            } catch (Exception e) {
                Log.e("ChooseStationsActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(TrainTime[] trainTimes) {
            trainTimeMessageText.setText(trainTimes[0].getScheduledDepartureTime());
        }
    }
}
