package com.oles.airmanagement.service.impl;

import com.oles.airmanagement.converter.DtoConverter;
import com.oles.airmanagement.dto.flight.FlightRequest;
import com.oles.airmanagement.dto.flight.FlightResponse;
import com.oles.airmanagement.exception.AlreadyExistException;
import com.oles.airmanagement.exception.NotFoundException;
import com.oles.airmanagement.model.AirCompany;
import com.oles.airmanagement.model.Airplane;
import com.oles.airmanagement.model.Flight;
import com.oles.airmanagement.repository.FlightRepository;
import com.oles.airmanagement.service.AirCompanyService;
import com.oles.airmanagement.service.AirplaneService;
import com.oles.airmanagement.service.FlightService;
import com.oles.airmanagement.utils.FlightStatus;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final AirCompanyService airCompanyService;
    private final AirplaneService airplaneService;

    private final DtoConverter dtoConverter;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, @Lazy AirCompanyService airCompanyService,
                             @Lazy AirplaneService airplaneService, DtoConverter dtoConverter) {
        this.flightRepository = flightRepository;
        this.airCompanyService = airCompanyService;
        this.airplaneService = airplaneService;
        this.dtoConverter = dtoConverter;
    }

    public Flight getFlightById(Long id) {
        return flightRepository.getByFlightId(id).orElseThrow(() -> new NotFoundException(
            String.format("Not found flight with id -> %d", id)));
    }

    @Override
    public FlightResponse getFlightResponseById(Long id) {
        return null;
    }

    public List<FlightResponse> getAllCompletedFlightWithFlightTimeBiggerThenEstimated() {
        List<Flight> completedFlights = flightRepository.getAllCompletedFlight();
        return completedFlights.stream()
            .filter(this::checkFlightTimeBiggerThenEstimated)
            .map(this::convertToFlightResponse)
            .collect(Collectors.toList());
    }

    FlightResponse convertToFlightResponse(Flight flight) {
        return dtoConverter.convertToDto(flight, FlightResponse.class);
    }
    Boolean checkFlightTimeBiggerThenEstimated(Flight flight) {
        Duration actualDuration = Duration.between(flight.getStartedAt(), flight.getEndedAt());
        return flight.getEstimatedFlightTime().compareTo(actualDuration) < 0;
    }

    @Override
    public FlightResponse create(FlightRequest flightRequest) {
        AirCompany airCompany = airCompanyService.getAirCompanyById(flightRequest.getAirCompanyId());
        Airplane airplane = airplaneService.getAirplaneById(flightRequest.getAirplaneId());
        if (flightRepository.existsFlightByAirplaneIdAndDateRange(
            airplane.getAirplaneId(), flightRequest.getStartedAt(), flightRequest.getEndedAt())) {
            throw new AlreadyExistException(String.format(
                "Not available airplane with id -> %d in this range startedAt -> %s and endedAt -> %s",
                airplane.getAirplaneId(), flightRequest.getStartedAt(), flightRequest.getEndedAt()));
        }
        Flight flight = Flight.builder()
            .flightStatus(FlightStatus.PENDING)
            .airCompany(airCompany)
            .airplane(airplane)
            .departureCountry(flightRequest.getDepartureCountry())
            .destinationCountry(flightRequest.getDestinationCountry())
            .distance(flightRequest.getDistance())
            .estimatedFlightTime(flightRequest.getEstimatedFlightTime())
            .startedAt(flightRequest.getStartedAt())
            .endedAt(flightRequest.getEndedAt())
            .createdAt(LocalDateTime.now())
            .build();
        flight = flightRepository.save(flight);
        return dtoConverter.convertToDto(flight, FlightResponse.class);
    }

    @Override
    public FlightResponse update(Long id, FlightRequest flightRequest) {
        AirCompany airCompany = airCompanyService.getAirCompanyById(flightRequest.getAirCompanyId());
        Airplane airplane = airplaneService.getAirplaneById(flightRequest.getAirplaneId());
        Flight flight = getFlightById(id);
        if (flightRepository.existsFlightByAirplaneIdAndDateRange(
            airplane.getAirplaneId(), flightRequest.getStartedAt(), flightRequest.getEndedAt())) {
            throw new AlreadyExistException(
                String.format("Not available airplane with id -> %d in this range startedAt -> %s and endedAt -> %s",
                    airplane.getAirplaneId(), flightRequest.getStartedAt(), flightRequest.getEndedAt()));
        }
        Flight newFlight = Flight.builder()
            .flightId(flight.getFlightId())
            .flightStatus(FlightStatus.getFlightStatus(flightRequest.getFlightStatus()))
            .airCompany(airCompany)
            .airplane(airplane)
            .departureCountry(flightRequest.getDepartureCountry())
            .destinationCountry(flightRequest.getDestinationCountry())
            .distance(flightRequest.getDistance())
            .estimatedFlightTime(flightRequest.getEstimatedFlightTime())
            .startedAt(flightRequest.getStartedAt())
            .endedAt(flightRequest.getEndedAt())
            .delayStartedAt(flight.getDelayStartedAt())
            .createdAt(flight.getCreatedAt())
            .build();
        return dtoConverter.convertToDto(flightRepository.save(newFlight), FlightResponse.class);
    }

    public FlightResponse updateFlightStatus(Long flightId, FlightStatus flightStatus) {
        Flight flight = getFlightById(flightId);
        LocalDateTime now = LocalDateTime.now();
        switch (flightStatus) {
            case ACTIVE:
                flight.setStartedAt(now);
                break;
            case DELAYED:
                flight.setDelayStartedAt(now);
                break;
            case COMPLETED:
                flight.setEndedAt(now);
                break;
        }
        return dtoConverter.convertToDto(flightRepository.save(flight), FlightResponse.class);
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }
}
