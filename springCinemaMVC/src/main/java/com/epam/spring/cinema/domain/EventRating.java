package com.epam.spring.cinema.domain;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public enum EventRating {

    LOW("LOW"),

    MID("MID"),

    HIGH("HIGH");

    private String sysName;

    private EventRating(String sysName) {
        this.sysName = sysName;
    }

    public static EventRating getRatingBySysName(String sysName) {
        for(EventRating rating : values()) {
            if (rating.sysName.equals(sysName)) {
                return rating;
            }
        }
        return null;
    }

    public String getSysName() {
        return sysName;
    }
}
