package com.iogui.securebank.secure_banking_api.application.services.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountSummaryResponse;
import com.iogui.securebank.secure_banking_api.application.mapper.account.AccountApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.GetAccountsSummaryUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.account.AccountOutputPort;

import java.util.List;

public class GetAccountsSummaryService implements GetAccountsSummaryUseCase {
    private final AccountOutputPort accountOutputPort;
    private final AccountApplicationMapper accountMapper;

    public GetAccountsSummaryService(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper) {
        this.accountOutputPort = accountOutputPort;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<GetAccountSummaryResponse> getAccountsSummary() {
        return accountOutputPort.getAccountsSummary()
                .stream()
                .map(accountMapper::mapToGetAccountSummaryResponse)
                .toList();
    }
}
