package com.iogui.securebank.secure_banking_api.infrastructure.adapters.input.api.graphql.controller;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;
import com.iogui.securebank.secure_banking_api.application.ports.input.interestRate.GetInterestRatesUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InterestRateGraphQLController {
    private static final Logger logger = LoggerFactory.getLogger(InterestRateGraphQLController.class);
    private final GetInterestRatesUseCase getInterestRatesUseCase;


    public InterestRateGraphQLController(GetInterestRatesUseCase getInterestRatesUseCase) {
        this.getInterestRatesUseCase = getInterestRatesUseCase;
    }

    @QueryMapping(value = "interestRate_findAll")
    public List<InterestRatesResponse> getAllInterestRates() {
        logger.debug("Request received: get all interest rates");
        return getInterestRatesUseCase.getInterestRates();
    }


}
