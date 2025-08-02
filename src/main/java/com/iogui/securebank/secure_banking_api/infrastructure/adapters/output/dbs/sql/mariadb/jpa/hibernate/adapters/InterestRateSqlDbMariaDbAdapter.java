package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.adapters;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;
import com.iogui.securebank.secure_banking_api.application.ports.output.interestRate.InterestRateOutputPort;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.interestRate.InterestRateMapper;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.repository.InterestRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InterestRateSqlDbMariaDbAdapter implements InterestRateOutputPort {
    private static final Logger logger = LoggerFactory.getLogger(InterestRateSqlDbMariaDbAdapter.class);
    private final InterestRateRepository interestRateRepository;
    private final InterestRateMapper interestRateMapper;

    public InterestRateSqlDbMariaDbAdapter(InterestRateRepository interestRateRepository, InterestRateMapper interestRateMapper) {
        this.interestRateRepository = interestRateRepository;
        this.interestRateMapper = interestRateMapper;
    }

    @Override
    public List<InterestRatesResponse> findAll() {
        return interestRateMapper.mapToDto(interestRateRepository.findAll());
    }
}
