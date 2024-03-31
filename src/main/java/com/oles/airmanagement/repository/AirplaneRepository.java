package com.oles.airmanagement.repository;

import com.oles.airmanagement.model.Airplane;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Optional<Airplane> getByAirplaneId(Long id);

    Boolean existsAirplaneByName(String name);

    Boolean existsAirplaneByAirplaneId(Long id);
}
