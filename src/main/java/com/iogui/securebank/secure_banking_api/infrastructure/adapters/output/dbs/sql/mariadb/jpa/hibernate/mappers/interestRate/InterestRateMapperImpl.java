package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.interestRate;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.InterestRateData;

import java.util.List;
import java.util.stream.Collectors;

public class InterestRateMapperImpl implements InterestRateMapper {

    @Override
    public List<InterestRatesResponse> mapToDto(List<InterestRateData> entityList) {
        if (entityList == null) {
            return null;
        }

        return entityList.stream()
                .map(domain -> new InterestRatesResponse(
                        domain.getId(),
                        domain.getProductType(),
                        domain.getProductName(),
                        domain.getRate(),
                        domain.getTerm(),
                        domain.getMinimumAmount(),
                        domain.getMaximumAmount(),
                        domain.getDescription(),
                        domain.getIsActive(),
                        domain.getEffectiveDate(),
                        domain.getLastUpdated()
                ))
                .collect(Collectors.toList());
    }
}
