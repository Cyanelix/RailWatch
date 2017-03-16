package com.cyanelix.railwatch.microtypes;

import android.content.Intent;

public enum IntentKey {
    FROM_STATION("com.cyanelix.railwatch.FROM_STATION"),
    TO_STATION("com.cyanelix.railwatch.TO_STATION");

    private final String key;

    IntentKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getStringExtra(Intent intent) {
        return intent.getStringExtra(key);
    }
}
