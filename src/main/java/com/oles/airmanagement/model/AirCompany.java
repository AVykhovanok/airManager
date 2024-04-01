package com.oles.airmanagement.model;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.utils.CompanyType;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "air_companies")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirCompany implements Convertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_company_id")
    Long airCompanyId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CompanyType companyType;

    @Column(nullable = false)
    Instant foundedAt;
}
