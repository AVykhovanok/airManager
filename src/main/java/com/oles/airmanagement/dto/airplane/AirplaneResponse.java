package com.oles.airmanagement.dto.airplane;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.model.AirCompany;
import com.oles.airmanagement.utils.AirplaneType;
import java.math.BigDecimal;
import java.time.Instant;
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
public class AirplaneResponse implements Convertible {
    Long airplaneId;

    String name;

    Long factorySerialNumber;

    AirCompany airCompany;

    Integer numberOfFlights;

    Distance flightDistance;

    BigDecimal fuelCapacity;

    AirplaneType type;

    Instant createdAt;
}
