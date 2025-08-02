package com.iogui.securebank.secure_banking_api.application.services.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.response.GetAccountByCustomerIdResponse;
import com.iogui.securebank.secure_banking_api.application.mapper.account.AccountApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.GetAccountsByCustomerIdUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.account.AccountOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class GetAccountsByCustomerIdService implements GetAccountsByCustomerIdUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetAccountsByCustomerIdService.class);
    private final AccountOutputPort accountOutputPort;
    private final AccountApplicationMapper accountMapper;

    public GetAccountsByCustomerIdService(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper) {
        this.accountOutputPort = accountOutputPort;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<GetAccountByCustomerIdResponse> getAccountsByCustomerId(String customerId) {
        List<GetAccountByCustomerIdResponse> customerAccounts = accountOutputPort.getAccountsByCustomerId(customerId)
                .stream()
                .map(accountMapper::mapToGetAccountByCustomerIdResponse)
                .toList();
        logger.debug("Found {} accounts for customer: {}", customerAccounts.size(), customerId);

        return customerAccounts;
    }

}
