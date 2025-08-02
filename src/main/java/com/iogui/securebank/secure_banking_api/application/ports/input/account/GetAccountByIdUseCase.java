package com.iogui.securebank.secure_banking_api.application.ports.input.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByIdResponse;

public interface GetAccountByIdUseCase {
    //Optional<Account> getAccountById(String id);
    GetAccountByIdResponse getAccountById(String id);
}
