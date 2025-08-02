package com.iogui.securebank.secure_banking_api.application.dto.account.response;

import java.math.BigDecimal;

public record GetAccountSummaryResponse(
        String accountType,
        Integer totalAccounts,
        BigDecimal totalBalance,
        BigDecimal averageBalance,
        Integer activeAccountsCount,
        Integer frozenAccountsCount,
        Integer closedAccountsCount
) {
}
