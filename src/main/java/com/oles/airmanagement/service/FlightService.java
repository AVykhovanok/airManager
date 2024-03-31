package com.oles.airmanagement.service;

import com.oles.airmanagement.dto.flight.FlightRequest;
import com.oles.airmanagement.dto.flight.FlightResponse;
import com.oles.airmanagement.utils.FlightStatus;
import java.util.List;

public interface FlightService {
    FlightResponse getFlightResponseById(Long id);

    List<FlightResponse> getAllActiveFlightStartedYesterday();

    List<FlightResponse> getAllCompletedDelayedFlight();

    FlightResponse create(FlightRequest flightRequest);

    FlightResponse update(Long id, FlightRequest flightRequest);

    FlightResponse updateFlightStatus(Long flightId, FlightStatus flightStatus);

    void deleteById(Long id);
}
