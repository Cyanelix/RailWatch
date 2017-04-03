package com.cyanelix.railwatch.service.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import com.cyanelix.railwatch.MainActivity;
import com.cyanelix.railwatch.R;
import com.cyanelix.railwatch.domain.TrainTime;

import javax.inject.Inject;

public class NotificationService {
    @Inject
    public NotificationService() { }

    public void notify(Activity sourceActivity, TrainTime[] trainTimes) {
        NotificationCompat.InboxStyle inboxStyle = createContent(trainTimes);
        PendingIntent pendingIntent = createPendingIntentWithArtificialBackStack(sourceActivity);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(sourceActivity)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Train times")
                        .setContentIntent(pendingIntent)
                        .setStyle(inboxStyle);

        NotificationManager notificationManager = (NotificationManager) sourceActivity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NotificationIds.MAIN_TIMES_LIST.getId(), notificationBuilder.build());
    }

    @NonNull
    private NotificationCompat.InboxStyle createContent(TrainTime[] trainTimes) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Train Times");

        for (TrainTime trainTime : trainTimes) {
            inboxStyle.addLine(trainTime.toString());
        }
        return inboxStyle;
    }

    private PendingIntent createPendingIntentWithArtificialBackStack(Activity sourceActivity) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(sourceActivity);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(new Intent(sourceActivity, sourceActivity.getClass()));
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
