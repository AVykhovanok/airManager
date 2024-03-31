package com.oles.airmanagement.repository;

import com.oles.airmanagement.model.AirCompany;
import com.oles.airmanagement.model.Flight;
import com.oles.airmanagement.utils.FlightStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCompanyRepository extends JpaRepository<AirCompany, Long> {
    Optional<AirCompany> getByAirCompanyId(Long id);

    @Query("SELECT DISTINCT f "
        + " FROM Flight f"
        + " where f.airCompany.name =:airCompanyName and f.flightStatus =:flightStatus")
    List<Flight> getAllAirCompanyFlightsByStatus(String airCompanyName, FlightStatus flightStatus);

    Boolean existsAirCompanyByName(String name);

    Boolean existsAirCompanyByAirCompanyId(Long id);
}
