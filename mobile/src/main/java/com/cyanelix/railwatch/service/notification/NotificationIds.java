package com.cyanelix.railwatch.service.notification;

public enum NotificationIds {
    MAIN_TIMES_LIST(1);

    private final int id;

    private NotificationIds(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
