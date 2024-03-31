package com.oles.airmanagement.model;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.utils.FlightStatus;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.geo.Distance;

@Entity
@Table(name = "flights")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight implements Convertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long flightId;

    @Enumerated
    FlightStatus flightStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "air_company_id", referencedColumnName = "air_company_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    AirCompany airCompany;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airplane_id", referencedColumnName = "airplane_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Airplane airplane;

    @Column(nullable = false)
    String departureCountry;

    @Column(nullable = false)
    String destinationCountry;

    @Column(nullable = false)
    Distance distance;

    @Column(nullable = false)
    Duration estimatedFlightTime;

    @Column(nullable = false)
    LocalDateTime startedAt;

    @Column(nullable = false)
    LocalDateTime endedAt;

    LocalDateTime delayStartedAt;

    @Column(nullable = false)
    LocalDateTime createdAt;
}

