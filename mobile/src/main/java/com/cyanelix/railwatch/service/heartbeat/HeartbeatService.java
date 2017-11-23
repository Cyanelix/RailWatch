package com.cyanelix.railwatch.service.heartbeat;

import android.os.AsyncTask;

import javax.inject.Inject;

public class HeartbeatService {
    @Inject
    HeartbeatClient heartbeatClient;

    @Inject
    public HeartbeatService() { }

    public void sendHeartbeat(String notificationTarget) {
        new HttpRequestTask().execute(notificationTarget);
    }

    private class HttpRequestTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... notificationTarget) {
            heartbeatClient.sendHeartbeat(notificationTarget[0]);
            return null;
        }
    }
}
