package com.iogui.securebank.secure_banking_api.infrastructure.adapters.input.api.rest.controller;

import com.iogui.securebank.secure_banking_api.application.dto.interestRate.InterestRatesResponse;
import com.iogui.securebank.secure_banking_api.application.ports.input.interestRate.GetInterestRatesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interest-rates")
public class InterestRatesRestController {

    private final GetInterestRatesUseCase getInterestRatesUseCase;

    public InterestRatesRestController(GetInterestRatesUseCase getInterestRatesUseCase) {
        this.getInterestRatesUseCase = getInterestRatesUseCase;
    }

    @GetMapping
    public ResponseEntity<List<InterestRatesResponse>> getAllInterestRates() {
        List<InterestRatesResponse> rates = getInterestRatesUseCase.getInterestRates();
        return ResponseEntity.ok(rates);
    }
}
