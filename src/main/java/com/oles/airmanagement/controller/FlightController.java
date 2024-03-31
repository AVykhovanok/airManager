package com.oles.airmanagement.controller;

import com.oles.airmanagement.dto.flight.FlightRequest;
import com.oles.airmanagement.dto.flight.FlightResponse;
import com.oles.airmanagement.service.FlightService;
import com.oles.airmanagement.utils.FlightStatus;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightResponse> getFlightResponseById(@NotNull @PathVariable Long flightId) {
        return ResponseEntity.ok(flightService.getFlightResponseById(flightId));
    }

    @GetMapping("/active_from_yesterday")
    public ResponseEntity<List<FlightResponse>> getAllActiveFlightStartedYesterday() {
        return ResponseEntity.ok(flightService.getAllActiveFlightStartedYesterday());
    }

    @GetMapping("/delayed/statuses/completed")
    public ResponseEntity<List<FlightResponse>> getAllCompletedFlightWithFlightTimeBiggerThenEstimated() {
        return ResponseEntity.ok(flightService.getAllCompletedDelayedFlight());
    }

    @PostMapping("/create")
    public ResponseEntity<FlightResponse> createFlight(@Valid FlightRequest flightRequest) {
        return ResponseEntity.ok(flightService.create(flightRequest));
    }

    @PutMapping("/update/{flightId}")
    public ResponseEntity<FlightResponse> updateFlight(
        @NotNull @PathVariable Long flightId, @Valid FlightRequest flightRequest) {
        return ResponseEntity.ok(flightService.update(flightId, flightRequest));
    }

    @PutMapping("/update/{flightId}/statuses/{flightStatus}")
    public ResponseEntity<FlightResponse> updateAirplane(@NotNull @PathVariable("flightId") Long flightId,
        @PathVariable("flightStatus") FlightStatus flightStatus) {
        return ResponseEntity.ok(flightService.updateFlightStatus(flightId, flightStatus));
    }

    @DeleteMapping("/delete/{flightId}")
    public  ResponseEntity<FlightResponse> deleteAirplaneById(@NotNull @PathVariable Long flightId) {
        return ResponseEntity.ok(flightService.deleteById(flightId));
    }
}
