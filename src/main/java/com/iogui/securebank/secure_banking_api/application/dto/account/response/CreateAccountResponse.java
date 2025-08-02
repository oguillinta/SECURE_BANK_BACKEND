package com.iogui.securebank.secure_banking_api.application.dto.account.response;

import com.iogui.securebank.secure_banking_api.domain.entity.Account;

import java.math.BigDecimal;

public record CreateAccountResponse(
        String accountNumber,
        String customerId,
        String accountType,
        BigDecimal balance,
        BigDecimal dailyTransactionLimit,
        String status,
        String createdAt
) {
    public static CreateAccountResponse from(Account account) {
        return new CreateAccountResponse(
                account.getAccountNumber(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getBalance(),
                account.getDailyTransactionLimit(),
                account.getStatus(),
                account.getCreatedAt().toString()
        );
    }
}