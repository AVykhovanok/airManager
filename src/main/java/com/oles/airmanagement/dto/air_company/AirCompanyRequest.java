package com.oles.airmanagement.dto.air_company;

import com.oles.airmanagement.converter.mark.Convertible;
import com.oles.airmanagement.utils.CompanyType;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class AirCompanyRequest implements Convertible {
    @NotBlank(message = "Air company should not be empty")
    String name;

    @NotNull(message = "Company type should not be empty")
    CompanyType companyType;

    @NotNull(message = "Foundation date should not be empty")
    LocalDate foundedAt;
}
