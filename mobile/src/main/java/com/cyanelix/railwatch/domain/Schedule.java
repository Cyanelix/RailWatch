package com.cyanelix.railwatch.domain;

public class Schedule {
    private String startTime;
    private String endTime;
    private String fromStation;
    private String toStation;
    private String notificationTarget;

    public Schedule(String startTime, String endTime, String fromStation, String toStation, String notificationTarget) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.notificationTarget = notificationTarget;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public String getNotificationTarget() {
        return notificationTarget;
    }
}
