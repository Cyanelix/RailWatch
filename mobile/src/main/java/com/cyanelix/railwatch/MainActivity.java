package com.cyanelix.railwatch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.cyanelix.railwatch.domain.Schedule;
import com.cyanelix.railwatch.service.heartbeat.HeartbeatService;
import com.cyanelix.railwatch.service.notification.FirebaseIdFacade;
import com.cyanelix.railwatch.service.notification.NotificationService;
import com.cyanelix.railwatch.service.schedule.ScheduleService;
import com.cyanelix.railwatch.service.times.TrainTimesService;
import com.cyanelix.railwatch.service.user.UserService;
import com.cyanelix.railwatch.ui.ScheduleArrayAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    TrainTimesService trainTimesService;

    @Inject
    ScheduleService scheduleService;

    @Inject
    NotificationService notificationService;

    @Inject
    HeartbeatService heartbeatService;

    @Inject
    UserService userService;

    @Inject
    FirebaseIdFacade firebaseIdFacade;

    private ListView schedulesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RailWatchApp) getApplication()).getTrainTimesComponent().inject(this);

        userService.createUserIfNotExists(getSharedPreferences("com.cyanelix.railwatch.prefs", MODE_PRIVATE));

        setContentView(R.layout.activity_main);
        schedulesList = findViewById(R.id.schedules);

        getSchedules();

        firebaseIdFacade.doWithFirebaseId(new Consumer<String>() {
            @Override
            public void accept(String firebaseId) {
                heartbeatService.sendHeartbeat(firebaseId);
            }
        });
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

        if (item.getItemId() == R.id.action_show_times) {
            showDepartures();
            return true;
        }

        if (item.getItemId() == R.id.action_refresh) {
            getSchedules();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chooseStations() {
        Intent intent = new Intent(this, ChooseStationsActivity.class);
        startActivity(intent);
    }

    private void showDepartures() {
        Intent intent = new Intent(this, DeparturesActivity.class);
        startActivity(intent);
    }

    private void getSchedules() {
        new SchedulesTask().execute();
    }

    private class SchedulesTask extends AsyncTask<Void, Void, List<Schedule>> {
        @Override
        protected List<Schedule> doInBackground(Void... params) {
            Comparator<Schedule> stateComparator = new Comparator<Schedule>() {
                @Override
                public int compare(Schedule o1, Schedule o2) {
                    return o2.getState().compareTo(o1.getState());
                }
            };

            List<Schedule> schedules = scheduleService.getSchedules();
            Collections.sort(schedules, stateComparator);

            return schedules;
        }

        @Override
        protected void onPostExecute(List<Schedule> schedules) {
            ScheduleArrayAdapter schedulesAdapter = new ScheduleArrayAdapter(MainActivity.this, 0, schedules);
            schedulesList.setAdapter(schedulesAdapter);
        }
    }
}
