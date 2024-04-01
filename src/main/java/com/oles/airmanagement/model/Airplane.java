package com.oles.airmanagement.model;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.utils.AirplaneType;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.geo.Distance;

@Entity
@Table(name = "airplanes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airplane implements Convertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    Long airplaneId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Long factorySerialNumber;

    @ManyToOne
    @JoinColumn(name = "air_company_id", referencedColumnName = "air_company_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    AirCompany airCompany;

    Integer numberOfFlights;

    @Column(nullable = false)
    Distance flightDistance;

    @Column(nullable = false)
    BigDecimal fuelCapacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    AirplaneType type;

    @Column(nullable = false)
    Instant createdAt;
}
