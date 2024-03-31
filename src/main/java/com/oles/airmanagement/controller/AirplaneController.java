package com.oles.airmanagement.controller;

import com.oles.airmanagement.dto.airplane.AirplaneCompanyUpdate;
import com.oles.airmanagement.dto.airplane.AirplaneRequest;
import com.oles.airmanagement.dto.airplane.AirplaneResponse;
import com.oles.airmanagement.service.AirplaneService;
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
@RequestMapping("/airplanes")
public class AirplaneController {
    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/{airplaneId}")
    public ResponseEntity<AirplaneResponse> getAirplaneResponseById(@NotNull @PathVariable Long airplaneId) {
        return ResponseEntity.ok(airplaneService.getAirPlaneResponseById(airplaneId));
    }

    @PostMapping("/create")
    public ResponseEntity<AirplaneResponse> createAirplane(@Valid AirplaneRequest airplaneRequest) {
        return ResponseEntity.ok(airplaneService.create(airplaneRequest));
    }

    @PutMapping("/update/{airplaneId}")
    public ResponseEntity<AirplaneResponse> updateAirplane(
        @NotNull @PathVariable Long airplaneId, @Valid AirplaneRequest airplaneRequest) {
        return ResponseEntity.ok(airplaneService.update(airplaneId, airplaneRequest));
    }

    @PutMapping("/update/companies")
    public ResponseEntity<AirplaneResponse> updateAirplane(@Valid AirplaneCompanyUpdate airplaneCompanyUpdate) {
        return ResponseEntity.ok(airplaneService.updateCompany(airplaneCompanyUpdate));
    }

    @DeleteMapping("/delete/{airplaneId}")
    public ResponseEntity<AirplaneResponse> deleteAirplaneById(@NotNull @PathVariable Long airplaneId) {
        return ResponseEntity.ok(airplaneService.deleteById(airplaneId));
    }
}
