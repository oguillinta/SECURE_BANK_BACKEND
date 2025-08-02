package com.iogui.securebank.secure_banking_api.application.ports.input.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.CreateAccountResponse;

import java.math.BigDecimal;

public interface CreateAccountUseCase {
    //Account createAccount(String customerId, String accountType, BigDecimal initialBalance);
    CreateAccountResponse createAccount(String customerId, String accountType, BigDecimal initialBalance);
}
