package com.oles.airmanagement.utils;

import com.oles.airmanagement.exception.NotFoundException;

public enum FlightStatus {
    ACTIVE,
    COMPLETED,
    DELAYED,
    PENDING;

    public static FlightStatus getFlightStatus(String flightStatus) {
        for (FlightStatus f: FlightStatus.values()) {
            if (f.toString().equals(flightStatus.toUpperCase())) {
                return f;
            }
        }
        throw new NotFoundException(String.format("Not found flight status -> %s", flightStatus));
    }
}
