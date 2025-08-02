package com.iogui.securebank.secure_banking_api.application.ports.input.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByCustomerIdResponse;

import java.util.List;

@FunctionalInterface
public interface GetAccountsByCustomerIdUseCase {
    List<GetAccountByCustomerIdResponse> getAccountsByCustomerId(String customerId);
}
