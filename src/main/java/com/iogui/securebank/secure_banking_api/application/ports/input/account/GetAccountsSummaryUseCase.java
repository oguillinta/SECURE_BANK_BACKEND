package com.iogui.securebank.secure_banking_api.application.ports.input.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountSummaryResponse;

import java.util.List;

public interface GetAccountsSummaryUseCase {
    List<GetAccountSummaryResponse> getAccountsSummary();
}
