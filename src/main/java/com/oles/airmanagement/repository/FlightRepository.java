package com.oles.airmanagement.repository;

import com.oles.airmanagement.model.Flight;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query("SELECT f"
        + " FROM Flight f"
        + " WHERE f.airplane.airplaneId =:airplaneId"
        + " AND f.startedAt BETWEEN :startedAt AND :endedAt"
        + " OR f.endedAt BETWEEN :startedAt and :endedAt")
    List<Flight> getAllFlightByAirplaneIdAndDateRange(Long airplaneId, Instant startedAt, Instant endedAt);
}
