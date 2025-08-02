package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.interestRate;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.InterestRateData;

import java.util.List;

public interface InterestRateMapper {
    List<InterestRatesResponse> mapToDto(List<InterestRateData> entityList);
}
