package com.oles.airmanagement.service;

import com.oles.airmanagement.dto.air_company.AirCompanyRequest;
import com.oles.airmanagement.dto.air_company.AirCompanyResponse;
import com.oles.airmanagement.model.AirCompany;

public interface AirCompanyService {
    AirCompany getAirCompanyById(Long id);

    AirCompanyResponse getAirCompanyResponseById(Long id);

    AirCompanyResponse create(AirCompanyRequest airCompanyRequest);

    AirCompanyResponse update(Long id, AirCompanyRequest airCompanyRequest);

    void deleteById(Long id);
}
