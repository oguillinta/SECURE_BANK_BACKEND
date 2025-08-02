package com.iogui.securebank.secure_banking_api.application.mapper.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.CreateAccountResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByCustomerIdResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByIdResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountSummaryResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByEmailResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import com.iogui.securebank.secure_banking_api.domain.entity.AccountSummaryReport;

public class AccountApplicationMapperImpl implements AccountApplicationMapper {

    @Override
    public GetCustomerByEmailResponse mapToDto(Account account) {
        return null;
    }

    @Override
    public CreateAccountResponse mapToCreateAccountResponse(Account account) {
        return new CreateAccountResponse(
                account.getAccountNumber(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getBalance(),
                account.getDailyTransactionLimit(),
                account.getStatus(),
                account.getCreatedAt().toString()
        );
    }

    @Override
    public GetAccountByIdResponse mapToGetAccountByIdResponse(Account account) {
        return new GetAccountByIdResponse(
                account.getAccountNumber(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getBalance(),
                account.getDailyTransactionLimit(),
                account.getStatus(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    @Override
    public GetAccountByCustomerIdResponse mapToGetAccountByCustomerIdResponse(Account account) {
        return new GetAccountByCustomerIdResponse(
                account.getAccountNumber(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getBalance(),
                account.getDailyTransactionLimit(),
                account.getStatus(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    @Override
    public GetAccountSummaryResponse mapToGetAccountSummaryResponse(AccountSummaryReport accountSummaryReport) {
        return new GetAccountSummaryResponse(
                accountSummaryReport.getAccountType(),
                accountSummaryReport.getTotalAccounts(),
                accountSummaryReport.getTotalBalance(),
                accountSummaryReport.getAverageBalance(),
                accountSummaryReport.getActiveAccountsCount(),
                accountSummaryReport.getFrozenAccountsCount(),
                accountSummaryReport.getClosedAccountsCount()
        );
    }
}
