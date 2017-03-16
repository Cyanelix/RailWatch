package com.cyanelix.railwatch.microtypes;

import com.cyanelix.railwatch.R;

public enum EditTextId {
    FROM_STATION(R.id.from_station),
    TO_STATION(R.id.to_station);

    private final int id;

    EditTextId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
