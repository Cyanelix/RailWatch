package com.cyanelix.railwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cyanelix.railwatch.domain.Schedule;
import com.cyanelix.railwatch.service.schedule.ScheduleService;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                getSelectedDays(),
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

    private String[] getSelectedDays() {
        List<String> selectedDayNames = new ArrayList<>();

        List<Integer> dayIds = Arrays.asList(R.id.monday, R.id.tuesday, R.id.wednesday,
                R.id.thursday, R.id.friday, R.id.saturday, R.id.sunday);

        for (Integer id : dayIds) {
            String dayName = getDayNameIfChecked(id);
            if (dayName != null) {
                selectedDayNames.add(dayName);
            }
        }

        return selectedDayNames.toArray(new String[selectedDayNames.size()]);
    }

    private String getDayNameIfChecked(int id) {
        CheckBox checkBox = (CheckBox) findViewById(id);
        if (checkBox.isChecked()) {
            return (String) checkBox.getText();
        }
        return null;
    }
}
