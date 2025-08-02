package com.iogui.securebank.secure_banking_api.application.mapper.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.CreateAccountResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByIdResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByCustomerIdResponse;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountSummaryResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByEmailResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import com.iogui.securebank.secure_banking_api.domain.entity.AccountSummaryReport;

public interface AccountApplicationMapper {
    GetCustomerByEmailResponse mapToDto(Account account);
    CreateAccountResponse mapToCreateAccountResponse(Account account);
    GetAccountByIdResponse mapToGetAccountByIdResponse(Account account);
    GetAccountByCustomerIdResponse mapToGetAccountByCustomerIdResponse(Account account);
    GetAccountSummaryResponse mapToGetAccountSummaryResponse(AccountSummaryReport accountSummaryReport);
}
