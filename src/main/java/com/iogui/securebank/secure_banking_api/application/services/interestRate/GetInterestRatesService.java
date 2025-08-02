package com.iogui.securebank.secure_banking_api.application.services.interestRate;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;
import com.iogui.securebank.secure_banking_api.application.ports.input.interestRate.GetInterestRatesUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.interestRate.InterestRateOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetInterestRatesService implements GetInterestRatesUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetInterestRatesService.class);
    private final InterestRateOutputPort interestRateOutputPort;

    public GetInterestRatesService(InterestRateOutputPort interestRateOutputPort) {
        this.interestRateOutputPort = interestRateOutputPort;
    }

    @Override
    public List<InterestRatesResponse> getInterestRates() {
        List<InterestRatesResponse> interestRates = interestRateOutputPort.findAll();
        logger.debug("Found {} interest rates", interestRates);
        return interestRates;

    }
}
