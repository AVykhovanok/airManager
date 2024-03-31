package com.oles.airmanagement.service.impl;

import com.oles.airmanagement.converter.DtoConverter;
import com.oles.airmanagement.dto.air_company.AirCompanyRequest;
import com.oles.airmanagement.dto.air_company.AirCompanyResponse;
import com.oles.airmanagement.dto.flight.FlightResponse;
import com.oles.airmanagement.exception.AlreadyExistException;
import com.oles.airmanagement.exception.NotFoundException;
import com.oles.airmanagement.model.AirCompany;
import com.oles.airmanagement.model.Flight;
import com.oles.airmanagement.repository.AirCompanyRepository;
import com.oles.airmanagement.service.AirCompanyService;
import com.oles.airmanagement.utils.FlightStatus;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AirCompanyServiceImpl implements AirCompanyService {
    private final AirCompanyRepository airCompanyRepository;

    private final DtoConverter dtoConverter;

    @Autowired
    public AirCompanyServiceImpl(AirCompanyRepository airCompanyRepository, DtoConverter dtoConverter) {
        this.airCompanyRepository = airCompanyRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public AirCompany getAirCompanyById(Long id) {
        return airCompanyRepository.getByAirCompanyId(id)
            .orElseThrow(() -> new NotFoundException(
                String.format("Not found air company with id -> %d", id)));
    }

    @Override
    public AirCompanyResponse getAirCompanyResponseById(Long id) {
        return dtoConverter.convertToDto(getAirCompanyById(id), AirCompanyResponse.class);
    }

    @Override
    public List<FlightResponse> getAllAirCompanyFlightsByStatus(String airCompanyName, FlightStatus flightStatus) {
        List<Flight> allAirCompanyFlightsByStatus =
            airCompanyRepository.getAllAirCompanyFlightsByStatus(airCompanyName, flightStatus);
        if (allAirCompanyFlightsByStatus.isEmpty()) {
            throw new NotFoundException(
                String.format("Not found %s flights in %s ", flightStatus.toString(), airCompanyName));
        }
        return allAirCompanyFlightsByStatus.stream()
            .map(this::convertToFlightResponse)
            .collect(Collectors.toList());
    }

    FlightResponse convertToFlightResponse(Flight flight) {
        return dtoConverter.convertToDto(flight, FlightResponse.class);
    }

    @Override
    public AirCompanyResponse create(AirCompanyRequest airCompanyRequest) {
        if (airCompanyRepository.existsAirCompanyByName(airCompanyRequest.getName())) {
            throw new AlreadyExistException(
                String.format("Air company with name -> %s already exist", airCompanyRequest.getName()));
        }
        AirCompany savedAirCompany =
            airCompanyRepository.save(dtoConverter.convertToEntity(airCompanyRequest, new AirCompany()));
        return dtoConverter.convertToDto(savedAirCompany, AirCompanyResponse.class);
    }

    @Override
    public AirCompanyResponse update(Long id, AirCompanyRequest airCompanyRequest) {
        AirCompany airCompany = getAirCompanyById(id);
        BeanUtils.copyProperties(airCompanyRequest, airCompany);
        return dtoConverter.convertToDto(airCompanyRepository.save(airCompany), AirCompanyResponse.class);
    }

    @Override
    public AirCompanyResponse deleteById(Long id) {
        AirCompanyResponse airCompanyResponse = getAirCompanyResponseById(id);
        airCompanyRepository.deleteById(id);
        return airCompanyResponse;
    }
}
