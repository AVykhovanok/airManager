package com.oles.airmanagement.dto.airplane;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.utils.AirplaneType;
import java.math.BigDecimal;
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
public class AirplaneRequest implements Convertible {
    @NotNull
    @NotBlank(message = "Airplane name should not be empty")
    String name;

    @NotNull(message = "Factory serial number should not be empty")
    Long factorySerialNumber;

    Long airCompanyId;

    Integer numberOfFlights;

    @NotNull(message = "Distance should not be empty")
    Distance flightDistance;

    @NotNull(message = "Fuel capacity should not be empty")
    BigDecimal fuelCapacity;

    @NotNull(message = "Airplane type should not be empty")
    AirplaneType type;

    @NotNull(message = "Created date should not be empty")
    LocalDateTime createdAt;
}
