package com.cyanelix.railwatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {
    private String startTime;
    private String endTime;
    private String[] days;
    private String fromStation;
    private String toStation;
    private String state;
    private String userId;

    public Schedule() {
        // Required for Jackson deserialization.
    }

    public Schedule(String startTime, String endTime, String[] days, String fromStation, String toStation, String state, String userId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.state = state;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public String[] getDays() {
        return days;
    }

    public String getState() {
        return state;
    }
}
