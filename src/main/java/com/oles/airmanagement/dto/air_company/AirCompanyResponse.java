package com.oles.airmanagement.dto.air_company;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.utils.CompanyType;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirCompanyResponse implements Convertible {
    Long airCompanyId;

    String name;

    CompanyType companyType;

    Instant foundedAt;
}
