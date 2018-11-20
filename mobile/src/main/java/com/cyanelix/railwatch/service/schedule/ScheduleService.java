package com.cyanelix.railwatch.service.schedule;

import android.os.AsyncTask;

import com.cyanelix.railwatch.client.ScheduleClient;
import com.cyanelix.railwatch.domain.Schedule;

import java.util.List;

import javax.inject.Inject;

public class ScheduleService {
    @Inject
    ScheduleClient scheduleClient;

    @Inject
    public ScheduleService() { }

    public void createSchedule(Schedule schedule) {
        new CreateScheduleTask().execute(schedule);
    }

    public List<Schedule> getSchedules() {
        return scheduleClient.getSchedules();
    }

    private class CreateScheduleTask extends AsyncTask<Schedule, Void, Void> {
        @Override
        protected Void doInBackground(Schedule... schedules) {
            scheduleClient.createSchedule(schedules[0]);
            return null;
        }
    }
}
