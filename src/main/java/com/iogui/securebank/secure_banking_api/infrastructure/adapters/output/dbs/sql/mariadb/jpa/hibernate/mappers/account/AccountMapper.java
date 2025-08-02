package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByCustomerIdResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountSummaryResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import com.iogui.securebank.secure_banking_api.domain.entity.AccountSummaryReport;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.AccountData;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.AccountSummaryProjection;

public interface AccountMapper {
    Account mapToDomain(AccountData entity);
    GetAccountByCustomerIdResponse mapToDto(AccountData entity);
    GetAccountByCustomerIdResponse mapToDto(Account domain);
    AccountData mapToData(Account domain);
    AccountSummaryReport mapToAccountSummaryReport(GetAccountSummaryResponse dto);
    AccountSummaryReport mapToAccountSummaryReportv2(AccountSummaryProjection projection);
}
