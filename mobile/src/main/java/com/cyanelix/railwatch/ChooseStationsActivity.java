package com.cyanelix.railwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cyanelix.railwatch.domain.Schedule;
import com.cyanelix.railwatch.service.schedule.ScheduleService;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

public class ChooseStationsActivity extends AppCompatActivity {
    @Inject
    ScheduleService scheduleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RailWatchApp) getApplication()).getTrainTimesComponent().inject(this);

        setContentView(com.cyanelix.railwatch.R.layout.activity_choose_stations);
    }

    public void sendMessage(View view) {
        Schedule schedule = new Schedule(
                getEditTextValue(R.id.schedule_start_time),
                getEditTextValue(R.id.schedule_end_time),
                getEditTextValue(R.id.from_station),
                getEditTextValue(R.id.to_station),
                FirebaseInstanceId.getInstance().getToken());

        scheduleService.createSchedule(schedule);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @NonNull
    private String getEditTextValue(int id) {
        EditText editText = (EditText) findViewById(id);
        return editText.getText().toString();
    }
}
