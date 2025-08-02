package com.iogui.securebank.secure_banking_api.application.services.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.CreateAccountResponse;
import com.iogui.securebank.secure_banking_api.application.mapper.account.AccountApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.CreateAccountUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.account.AccountOutputPort;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CreateAccountService implements CreateAccountUseCase {
    private final AccountOutputPort accountOutputPort;
    private final AccountApplicationMapper accountMapper;


    public CreateAccountService(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper) {
        this.accountOutputPort = accountOutputPort;
        this.accountMapper = accountMapper;
    }

    @Override
    public CreateAccountResponse createAccount(String customerId, String accountType, BigDecimal initialBalance) {

        validateAccountCreationRequest(customerId, accountType, initialBalance);

        String accountNumber = generateAccountNumber();

        Account account = new Account(
                accountNumber,
                customerId,
                accountType,
                initialBalance != null ? initialBalance : BigDecimal.ZERO,
                getDefaultDailyLimit(accountType),
                "ACTIVE",
                Instant.now(),
                Instant.now()
        );
        return accountMapper.mapToCreateAccountResponse(accountOutputPort.createAccount(account));

    }

    private void validateAccountCreationRequest(String customerId, String accountType, BigDecimal initialBalance) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        if (accountType == null || accountType.trim().isEmpty()) {
            throw new IllegalArgumentException("Account type is required");
        }

        if (!isValidAccountType(accountType)) {
            throw new IllegalArgumentException("Invalid account type: " + accountType);
        }

        if (initialBalance != null && initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
    }

    private boolean isValidAccountType(String accountType) {
        return accountType.equals("SAVINGS") ||
                accountType.equals("CHECKING") ||
                accountType.equals("BUSINESS");
    }

    private String generateAccountNumber() {
        // Simple account number generation - you might want to make this more sophisticated
        return "ACC" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private BigDecimal getDefaultDailyLimit(String accountType) {
        return switch (accountType) {
            case "SAVINGS" -> new BigDecimal("5000.00");
            case "CHECKING" -> new BigDecimal("10000.00");
            case "BUSINESS" -> new BigDecimal("50000.00");
            default -> new BigDecimal("5000.00");
        };
    }
}
