package com.cyanelix.railwatch.domain;

import org.apache.commons.lang3.StringUtils;

public class TrainTime {
    private String scheduledDepartureTime;
    private String expectedDepartureTime;
    private String message;

    public String toString() {
        if (scheduledDepartureTime.equals(expectedDepartureTime)) {
            return scheduledDepartureTime;
        }

        if (StringUtils.isBlank(message)) {
            return scheduledDepartureTime + " -> " + expectedDepartureTime;
        }

        if (StringUtils.isBlank(expectedDepartureTime)) {
            return scheduledDepartureTime + " (" + message + ")";
        }

        return "";
    }

    public String getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public String getExpectedDepartureTime() {
        return expectedDepartureTime;
    }

    public String getMessage() {
        return message;
    }
}
