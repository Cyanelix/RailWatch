package com.cyanelix.railwatch;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.cyanelix.railwatch.microtypes.IntentKey;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cyanelix.railwatch.R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = IntentKey.FROM_STATION.getStringExtra(intent);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(com.cyanelix.railwatch.R.id.activity_display_message);
        layout.addView(textView);

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
                Log.e("DisplayMessageActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(TrainTime[] trainTimes) {
            TextView trainTimeMessageText = (TextView) findViewById(com.cyanelix.railwatch.R.id.content_value);
            trainTimeMessageText.setText(trainTimes[0].getScheduledDepartureTime());
        }
    }
}
