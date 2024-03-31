package com.oles.airmanagement.dto.flight;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.utils.FlightStatus;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.geo.Distance;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightResponse implements Convertible {
    Long flightId;

    FlightStatus flightStatus;

    Long airCompanyId;

    Long airplaneId;

    String departureCountry;

    String destinationCountry;

    Distance distance;

    Duration estimatedFlightTime;

    LocalDateTime startedAt;

    LocalDateTime endedAt;

    LocalDateTime delayStartedAt;

    LocalDateTime createdAt;
}
