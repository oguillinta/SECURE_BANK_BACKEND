package com.iogui.securebank.secure_banking_api.application.dto.account.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotBlank(message = "Customer ID is required")
        String customerId,

        @NotBlank(message = "Account type is required")
        String accountType,

        @PositiveOrZero(message = "Initial balance must be zero or positive")
        BigDecimal initialBalance
) {}