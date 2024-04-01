package com.oles.airmanagement.controller;

import com.oles.airmanagement.dto.air_company.AirCompanyRequest;
import com.oles.airmanagement.dto.air_company.AirCompanyResponse;
import com.oles.airmanagement.dto.flight.FlightResponse;
import com.oles.airmanagement.service.AirCompanyService;
import com.oles.airmanagement.utils.FlightStatus;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/air_companies")
public class AirCompanyController {
    private final AirCompanyService airCompanyService;

    @Autowired
    public AirCompanyController(AirCompanyService airCompanyService) {
        this.airCompanyService = airCompanyService;
    }

    @GetMapping("/{airCompanyId}")
    public ResponseEntity<AirCompanyResponse> getAirCompanyResponseById(@NotNull @PathVariable Long airCompanyId) {
        return ResponseEntity.ok(airCompanyService.getAirCompanyResponseById(airCompanyId));
    }

    @GetMapping("/{flightStatus}/{airCompanyName}")
    public ResponseEntity<List<FlightResponse>> getAllAirCompanyFlightsByStatus(
        @NotNull @PathVariable("flightStatus") FlightStatus flightStatus,
        @NotNull @NotBlank @PathVariable("airCompanyName") String airCompanyName) {
        System.out.println(flightStatus);
        System.out.println(airCompanyName);
        return ResponseEntity.ok(airCompanyService
            .getAllAirCompanyFlightsByStatus(airCompanyName, flightStatus));
    }

    @PostMapping("/create")
    public ResponseEntity<AirCompanyResponse> createAirCompany(
        @Valid @RequestBody AirCompanyRequest airCompanyRequest) {
        System.out.println(airCompanyRequest.getName());
        System.out.println(airCompanyRequest.getCompanyType());
        System.out.println(airCompanyRequest.getFoundedAt());
        return ResponseEntity.ok(airCompanyService.create(airCompanyRequest));
    }

    @PutMapping("/update/{airCompanyId}")
    public ResponseEntity<AirCompanyResponse> updateAirCompany(
        @NotNull @PathVariable Long airCompanyId, @Valid @RequestBody AirCompanyRequest airCompanyRequest) {
        return ResponseEntity.ok(airCompanyService.update(airCompanyId, airCompanyRequest));
    }

    @DeleteMapping("/delete/{airCompanyId}")
    public ResponseEntity<AirCompanyResponse> deleteAirCompanyById(@NotNull @PathVariable Long airCompanyId) {
        return ResponseEntity.ok(airCompanyService.deleteById(airCompanyId));
    }
}
