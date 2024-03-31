package com.oles.airmanagement.service.impl;

import com.oles.airmanagement.converter.DtoConverter;
import com.oles.airmanagement.dto.airplane.AirplaneCompanyUpdate;
import com.oles.airmanagement.dto.airplane.AirplaneRequest;
import com.oles.airmanagement.dto.airplane.AirplaneResponse;
import com.oles.airmanagement.exception.AlreadyExistException;
import com.oles.airmanagement.exception.NotFoundException;
import com.oles.airmanagement.model.AirCompany;
import com.oles.airmanagement.model.Airplane;
import com.oles.airmanagement.repository.AirplaneRepository;
import com.oles.airmanagement.service.AirCompanyService;
import com.oles.airmanagement.service.AirplaneService;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirCompanyService airCompanyService;

    private final DtoConverter dtoConverter;

    @Autowired
    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, @Lazy AirCompanyService airCompanyService,
                               DtoConverter dtoConverter) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyService = airCompanyService;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.getByAirplaneId(id).orElseThrow(() -> new NotFoundException(
            String.format("Not found airplane with id -> %d", id)));
    }

    @Override
    public AirplaneResponse getAirPlaneResponseById(Long id) {
        return dtoConverter.convertToDto(getAirplaneById(id), Airplane.class);
    }

    @Override
    public AirplaneResponse create(AirplaneRequest airplaneRequest) {
        if (airplaneRepository.existsAirplaneByName(airplaneRequest.getName())) {
            throw new AlreadyExistException(
                String.format("Airplane with name -> %s already exist", airplaneRequest.getName()));
        }
        Airplane savedAirplane =
            airplaneRepository.save(dtoConverter.convertToEntity(airplaneRequest, new Airplane()));
        return dtoConverter.convertToDto(savedAirplane, AirplaneResponse.class);
    }

    @Override
    public AirplaneResponse update(Long id, AirplaneRequest airplaneRequest) {
        Airplane airplane = getAirplaneById(id);
        BeanUtils.copyProperties(airplaneRequest, airplane);
        return dtoConverter.convertToDto(airplaneRepository.save(airplane), AirplaneResponse.class);
    }

    @Override
    public AirplaneResponse updateCompany(AirplaneCompanyUpdate airplaneCompanyUpdate) {
        Airplane airplane = getAirplaneById(airplaneCompanyUpdate.getAirplaneId());
        AirCompany airCompany = airCompanyService.getAirCompanyById(airplaneCompanyUpdate.getAirCompanyId());
        airplane.setAirCompany(airCompany);
        return dtoConverter.convertToDto(airplaneRepository.save(airplane), AirplaneResponse.class);
    }

    @Override
    public AirplaneResponse deleteById(Long id) {
        AirplaneResponse airplaneResponse = getAirPlaneResponseById(id);
        airplaneRepository.deleteById(id);
        return airplaneResponse;
    }
}
