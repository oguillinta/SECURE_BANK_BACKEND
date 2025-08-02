package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByCustomerIdResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountSummaryResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import com.iogui.securebank.secure_banking_api.domain.entity.AccountSummaryReport;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.AccountData;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.AccountSummaryProjection;

public class AccountMapperCustomImpl implements AccountMapper {
    @Override
    public Account mapToDomain(AccountData entity) {
        return new Account(
                entity.getAccountNumber(),
                entity.getCustomerId(),
                entity.getAccountType(),
                entity.getBalance(),
                entity.getDailyTransactionLimit(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public GetAccountByCustomerIdResponse mapToDto(AccountData entity) {
        return new GetAccountByCustomerIdResponse(
                entity.getAccountNumber(),
                entity.getCustomerId(),
                entity.getAccountType(),
                entity.getBalance(),
                entity.getDailyTransactionLimit(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public GetAccountByCustomerIdResponse mapToDto(Account domain) {
        return new GetAccountByCustomerIdResponse(
                domain.getAccountNumber(),
                domain.getCustomerId(),
                domain.getAccountType(),
                domain.getBalance(),
                domain.getDailyTransactionLimit(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    @Override
    public AccountData mapToData(Account domain) {
        return new AccountData(
                domain.getAccountNumber(),
                domain.getCustomerId(),
                domain.getAccountType(),
                domain.getBalance(),
                domain.getDailyTransactionLimit(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt(),
                // Freeze-related fields
                domain.getFreezeReason(),
                domain.getFreezeType(),
                domain.getFreezeDate(),
                domain.getFrozenBy(),
                domain.getFreezeReviewDate(),
                domain.getFreezeReferenceNumber()
        );
    }

    @Override
    public AccountSummaryReport mapToAccountSummaryReport(GetAccountSummaryResponse dto) {
        return new AccountSummaryReport(
                dto.accountType(),
                dto.totalAccounts(),
                dto.totalBalance(),
                dto.averageBalance(),
                dto.activeAccountsCount(),
                dto.frozenAccountsCount(),
                dto.closedAccountsCount()
        );
    }

    @Override
    public AccountSummaryReport mapToAccountSummaryReportv2(AccountSummaryProjection projection) {
        return new AccountSummaryReport(
                projection.getAccountType(),
                projection.getTotalAccounts(),
                projection.getTotalBalance(),
                projection.getAverageBalance(),
                projection.getActiveAccounts(),
                projection.getFrozenAccounts(),
                projection.getClosedAccounts()
        );
    }
}
