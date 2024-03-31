package com.oles.airmanagement.repository;

import com.oles.airmanagement.model.Flight;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> getByFlightId(Long id);

    @Query("SELECT f"
        + " FROM Flight f"
        + " WHERE f.flightStatus = 'ACTIVE' and f.startedAt >= CURRENT_DATE - 1")
    List<Flight> getAllActiveFlightStartedYesterday();

    @Query("SELECT f"
        + " FROM Flight f"
        + " WHERE f.flightStatus = 'COMPLETED'")
    List<Flight> getAllCompletedFlight();

    Boolean existsFlightByFlightId(Long id);

    @Query("SELECT CASE"
        + " WHEN f.startedAt BETWEEN :startedAt and :endedAt"
        + " OR f.endedAt BETWEEN :startedAt and :endedAt"
        + " THEN true"
        + " ELSE false"
        + " END"
        + " FROM Flight f"
        + " WHERE f.airplane.airplaneId =:airplaneId")
    Boolean existsFlightByAirplaneIdAndDateRange(Long airplaneId, LocalDateTime startedAt, LocalDateTime endedAt);
}
