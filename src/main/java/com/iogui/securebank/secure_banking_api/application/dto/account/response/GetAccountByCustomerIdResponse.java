package com.iogui.securebank.secure_banking_api.application.dto.account.response;

import java.math.BigDecimal;
import java.time.Instant;

public record GetAccountByCustomerIdResponse(
        String accountNumber,
        String customerId,
        String accountType,
        BigDecimal balance,
        BigDecimal getDailyTransactionLimit,
        String status,
        Instant createdAt,
        Instant updatedAt
) {
}
