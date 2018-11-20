package com.cyanelix.railwatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String notificationTarget;

    public User() {
        // Required for Jackson
    }

    public User(String notificationTarget) {
        this.notificationTarget = notificationTarget;
    }

    public String getNotificationTarget() {
        return notificationTarget;
    }
}
