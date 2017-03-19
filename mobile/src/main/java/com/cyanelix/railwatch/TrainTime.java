package com.cyanelix.railwatch;

public class TrainTime {
    private String scheduledDepartureTime;
    private String expectedDepartureTime;
    private String message;

    public String toString() {
        if (scheduledDepartureTime.equals(expectedDepartureTime)) {
            return scheduledDepartureTime;
        }

        if (message.isEmpty()) {
            return scheduledDepartureTime + " -> " + expectedDepartureTime;
        }

        if (expectedDepartureTime.isEmpty()) {
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
