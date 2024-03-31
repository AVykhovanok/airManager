package com.oles.airmanagement.dto.flight;

import com.oles.airmanagement.converter.mark.Convertible;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class FlightRequest implements Convertible {
    @NotNull(message = "Flight should not be empty")
    String flightStatus;

    @NotNull(message = "Air company id should not be empty")
    Long airCompanyId;

    @NotNull(message = "Airplane id should not be empty")
    Long airplaneId;

    @NotNull
    @NotBlank(message = "Departure country should not be empty")
    String departureCountry;

    @NotNull
    @NotBlank(message = "Destination country should not be empty")
    String destinationCountry;

    @NotNull(message = "Distance should not be empty")
    Distance distance;

    @NotNull(message = "Estimated flight time should not be empty")
    Duration estimatedFlightTime;

    @NotNull(message = "Start date should not be empty")
    LocalDateTime startedAt;

    @NotNull(message = "End date should not be empty")
    LocalDateTime endedAt;
}
