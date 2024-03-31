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
@RequestMapping("/air_company")
public class AirCompanyController {
    private final AirCompanyService airCompanyService;

    @Autowired
    public AirCompanyController(AirCompanyService airCompanyService) {
        this.airCompanyService = airCompanyService;
    }

    @GetMapping("/{airCompanyId}")
    public ResponseEntity<AirCompanyResponse> getAirCompanyResponseById(@NotNull @PathVariable Long airCompanyId) {
        return new ResponseEntity<>(airCompanyService.getAirCompanyResponseById(airCompanyId), HttpStatus.OK);
    }

    @GetMapping("/{flightStatus}/{airCompanyName}")
    public ResponseEntity<List<FlightResponse>> getAllAirCompanyFlightsByStatus(
        @NotNull @PathVariable("flightStatus") FlightStatus flightStatus,
        @NotBlank @PathVariable("airCompanyName") String airCompanyName) {
        return new ResponseEntity<>(airCompanyService
            .getAllAirCompanyFlightsByStatus(airCompanyName, flightStatus), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AirCompanyResponse> createAirCompany(@Valid AirCompanyRequest airCompanyRequest) {
        return new ResponseEntity<>(airCompanyService.create(airCompanyRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{airCompanyId}")
    public ResponseEntity<AirCompanyResponse> updateAirCompany(
        @NotNull @PathVariable Long airCompanyId, @Valid AirCompanyRequest airCompanyRequest) {
        return new ResponseEntity<>(airCompanyService.update(airCompanyId, airCompanyRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{airCompanyId}")
    public void deleteAirCompanyById(@NotNull @PathVariable Long airCompanyId) {
        airCompanyService.deleteById(airCompanyId);
    }
}
