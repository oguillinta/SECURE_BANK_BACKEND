package com.iogui.securebank.secure_banking_api.application.services.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByIdResponse;
import com.iogui.securebank.secure_banking_api.application.mapper.account.AccountApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.GetAccountByIdUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.account.AccountOutputPort;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;

public class GetAccountByIdService implements GetAccountByIdUseCase {
    private final AccountOutputPort accountOutputPort;
    private final AccountApplicationMapper accountMapper;

    public GetAccountByIdService(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper) {
        this.accountOutputPort = accountOutputPort;
        this.accountMapper = accountMapper;
    }

    @Override
    public GetAccountByIdResponse getAccountById(String id) {
        Account account = this.accountOutputPort.getAccountById(id).get();
        return accountMapper.mapToGetAccountByIdResponse(account);
    }
}
