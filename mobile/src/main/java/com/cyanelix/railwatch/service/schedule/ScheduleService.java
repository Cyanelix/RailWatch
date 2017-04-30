package com.cyanelix.railwatch.service.schedule;

import android.os.AsyncTask;

import com.cyanelix.railwatch.domain.Schedule;

import javax.inject.Inject;

public class ScheduleService {
    @Inject
    ScheduleClient scheduleClient;

    @Inject
    public ScheduleService() { }

    public void createSchedule(Schedule schedule) {
        new HttpRequestTask().execute(schedule);
    }

    private class HttpRequestTask extends AsyncTask<Schedule, Void, Void> {
        @Override
        protected Void doInBackground(Schedule... schedules) {
            scheduleClient.createSchedule(schedules[0]);
            return null;
        }
    }
}
