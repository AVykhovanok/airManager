package com.oles.airmanagement.service;

import com.oles.airmanagement.dto.flight.FlightRequest;
import com.oles.airmanagement.dto.flight.FlightResponse;

public interface FlightService {
    FlightResponse getFlightResponseById(Long id);

    FlightResponse create(FlightRequest flightRequest);

    FlightResponse update(Long id, FlightRequest flightRequest);

    void deleteById(Long id);
}
