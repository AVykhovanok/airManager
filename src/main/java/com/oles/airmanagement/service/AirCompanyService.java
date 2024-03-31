package com.oles.airmanagement.service;

import com.oles.airmanagement.dto.air_company.AirCompanyRequest;
import com.oles.airmanagement.dto.air_company.AirCompanyResponse;
import com.oles.airmanagement.dto.flight.FlightResponse;
import com.oles.airmanagement.model.AirCompany;
import com.oles.airmanagement.utils.FlightStatus;
import java.util.List;

public interface AirCompanyService {
    AirCompany getAirCompanyById(Long id);

    AirCompanyResponse getAirCompanyResponseById(Long id);

    List<FlightResponse> getAllAirCompanyFlightsByStatus(String airCompanyName, FlightStatus flightStatus);

    AirCompanyResponse create(AirCompanyRequest airCompanyRequest);

    AirCompanyResponse update(Long id, AirCompanyRequest airCompanyRequest);

    AirCompanyResponse deleteById(Long id);
}
