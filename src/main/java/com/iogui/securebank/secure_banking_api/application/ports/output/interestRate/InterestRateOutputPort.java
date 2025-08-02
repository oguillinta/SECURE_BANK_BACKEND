package com.iogui.securebank.secure_banking_api.application.ports.output.interestRate;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;

import java.util.List;

public interface InterestRateOutputPort {
    List<InterestRatesResponse> findAll();
}
