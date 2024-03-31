package com.oles.airmanagement.controller;

import com.oles.airmanagement.dto.airplane.AirplaneRequest;
import com.oles.airmanagement.dto.airplane.AirplaneResponse;
import com.oles.airmanagement.service.AirplaneService;
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
@RequestMapping("/airplane")
public class AirplaneController {
    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/{airplaneId}")
    public ResponseEntity<AirplaneResponse> getAirplaneResponseById(@NotNull @PathVariable Long airplaneId) {
        return new ResponseEntity<>(airplaneService.getAirPlaneResponseById(airplaneId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AirplaneResponse> createAirplane(@Valid AirplaneRequest airplaneRequest) {
        return new ResponseEntity<>(airplaneService.create(airplaneRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{airplaneId}")
    public ResponseEntity<AirplaneResponse> updateAirplane(
        @NotNull @PathVariable Long airplaneId, @Valid AirplaneRequest airplaneRequest) {
        return new ResponseEntity<>(airplaneService.update(airplaneId, airplaneRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{airplaneId}")
    public void deleteAirplaneById(@NotNull @PathVariable Long airplaneId) {
        airplaneService.deleteById(airplaneId);
    }
}
