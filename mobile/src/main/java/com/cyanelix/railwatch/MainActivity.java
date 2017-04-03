package com.cyanelix.railwatch;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cyanelix.railwatch.domain.TrainTime;
import com.cyanelix.railwatch.service.times.TrainTimesService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    TrainTimesService trainTimesService;

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

            createNotification(trainTimes);
        }

        private void createNotification(TrainTime[] trainTimes) {
            Intent mainActivityIntent = new Intent(MainActivity.this, MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(mainActivityIntent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.setBigContentTitle("Times");

            for (TrainTime trainTime : trainTimes) {
                inboxStyle.addLine(trainTime.toString());
            }

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Train times")
                    .setContentIntent(pendingIntent)
                    .setStyle(inboxStyle);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder.build());
        }
    }
}
