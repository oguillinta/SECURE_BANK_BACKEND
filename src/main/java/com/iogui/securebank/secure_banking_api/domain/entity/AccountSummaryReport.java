package com.iogui.securebank.secure_banking_api.domain.entity;

import java.math.BigDecimal;

public class AccountSummaryReport {
    private final String accountType;
    private final Integer totalAccounts;
    private final BigDecimal totalBalance;
    private final BigDecimal averageBalance;
    private final Integer activeAccountsCount;
    private final Integer frozenAccountsCount;
    private final Integer closedAccountsCount;

    public AccountSummaryReport(
            String accountType,
            Integer totalAccounts,
            BigDecimal totalBalance,
            BigDecimal averageBalance,
            Integer activeAccountsCount,
            Integer frozenAccountsCount,
            Integer closedAccountsCount) {
        this.accountType = accountType;
        this.totalAccounts = totalAccounts;
        this.totalBalance = totalBalance;
        this.averageBalance = averageBalance;
        this.activeAccountsCount = activeAccountsCount;
        this.frozenAccountsCount = frozenAccountsCount;
        this.closedAccountsCount = closedAccountsCount;
    }

    public String getAccountType() {
        return accountType;
    }

    public Integer getTotalAccounts() {
        return totalAccounts;
    }

    public BigDecimal getAverageBalance() {
        return averageBalance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public Integer getActiveAccountsCount() {
        return activeAccountsCount;
    }

    public Integer getFrozenAccountsCount() {
        return frozenAccountsCount;
    }

    public Integer getClosedAccountsCount() {
        return closedAccountsCount;
    }


}
