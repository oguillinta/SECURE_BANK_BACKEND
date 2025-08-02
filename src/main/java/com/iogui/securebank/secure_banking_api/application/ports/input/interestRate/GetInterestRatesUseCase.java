package com.iogui.securebank.secure_banking_api.application.ports.input.interestRate;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;

import java.util.List;

public interface GetInterestRatesUseCase {
    List<InterestRatesResponse> getInterestRates();
}
