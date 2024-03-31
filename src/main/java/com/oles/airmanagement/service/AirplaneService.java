package com.oles.airmanagement.service;


import com.oles.airmanagement.dto.airplane.AirplaneCompanyUpdate;
import com.oles.airmanagement.dto.airplane.AirplaneRequest;
import com.oles.airmanagement.dto.airplane.AirplaneResponse;
import com.oles.airmanagement.model.Airplane;

public interface AirplaneService {
    Airplane getAirplaneById(Long id);

    AirplaneResponse getAirPlaneResponseById(Long id);

    AirplaneResponse create(AirplaneRequest airplaneRequest);

    AirplaneResponse update(Long id, AirplaneRequest airplaneRequest);

    AirplaneResponse updateCompany(AirplaneCompanyUpdate airplaneCompanyUpdate);

    AirplaneResponse deleteById(Long id);
}
