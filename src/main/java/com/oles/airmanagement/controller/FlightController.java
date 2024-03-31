package com.oles.airmanagement.controller;

import com.oles.airmanagement.dto.flight.FlightRequest;
import com.oles.airmanagement.dto.flight.FlightResponse;
import com.oles.airmanagement.service.FlightService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightResponse> getAirplaneResponseById(@NotNull @PathVariable Long flightId) {
        return new ResponseEntity<>(flightService.getFlightResponseById(flightId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<FlightResponse> createAirplane(@Valid FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.create(flightRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{flightId}")
    public ResponseEntity<FlightResponse> updateAirplane(@NotNull @PathVariable Long flightId, @Valid FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.update(flightId, flightRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{flightId}")
    public void deleteAirplaneById(@NotNull @PathVariable Long flightId) {
        flightService.deleteById(flightId);
    }
}
