package com.iogui.securebank.secure_banking_api.application.ports.output.account;

import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import com.iogui.securebank.secure_banking_api.domain.entity.AccountSummaryReport;

import java.util.List;
import java.util.Optional;


public interface AccountOutputPort {
    Account createAccount(Account account);
    Account updateAccount (Account account);
    Optional<Account> getAccountById(String accountNumber);
    List<Account> getAccountsByCustomerId(String customerId);
    List<AccountSummaryReport> getAccountsSummary();
}
