package com.iogui.securebank.secure_banking_api.configuration;

import com.iogui.securebank.secure_banking_api.application.mapper.account.AccountApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.mapper.account.AccountApplicationMapperImpl;
import com.iogui.securebank.secure_banking_api.application.mapper.customer.CustomerApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.mapper.customer.CustomerApplicationMapperImpl;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.GetAccountByIdUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.GetAccountsSummaryUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetCustomerByEmailUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetAllCustomersUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetCustomerByIdUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.account.AccountOutputPort;
import com.iogui.securebank.secure_banking_api.application.ports.output.customer.CustomerOutputPort;
import com.iogui.securebank.secure_banking_api.application.ports.output.interestRate.InterestRateOutputPort;
import com.iogui.securebank.secure_banking_api.application.services.account.*;
import com.iogui.securebank.secure_banking_api.application.services.customer.GetAllCustomersService;
import com.iogui.securebank.secure_banking_api.application.services.customer.GetCustomerByEmailService;
import com.iogui.securebank.secure_banking_api.application.services.customer.GetCustomerByIdService;
import com.iogui.securebank.secure_banking_api.application.services.customer.UpdateCustomerProfileService;
import com.iogui.securebank.secure_banking_api.application.services.interestRate.GetInterestRatesService;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.adapters.AccountSqlDbMariaDbAdapter;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.adapters.CustomerSqlDbMariaDbAdapter;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.adapters.InterestRateSqlDbMariaDbAdapter;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.account.AccountMapper;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.account.AccountMapperCustomImpl;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.customer.CustomerMapper;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.customer.CustomerMapperCustomImpl;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.interestRate.InterestRateMapper;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.interestRate.InterestRateMapperImpl;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.repository.AccountRepository;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.repository.CustomerRepository;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.repository.InterestRateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalRestMariaDbConfig {
    @Bean
    AccountSqlDbMariaDbAdapter accountSqlDbMariaDbAdapter(
            AccountRepository accountRepository,
            AccountMapper accountMapper) {
        return new AccountSqlDbMariaDbAdapter(accountRepository, accountMapper);
    }

    @Bean
    CustomerSqlDbMariaDbAdapter customerSqlDbMariaDbAdapter(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper) {
        return new CustomerSqlDbMariaDbAdapter(customerRepository, customerMapper);
    }

    @Bean
    InterestRateSqlDbMariaDbAdapter interestRateSqlDbMariaDbAdapter(
            InterestRateRepository interestRateRepository,
            InterestRateMapper interestRateMapper) {
        return new InterestRateSqlDbMariaDbAdapter(interestRateRepository, interestRateMapper);
    }

    @Bean
    GetAccountsByCustomerIdService getAccountsByCustomerIdService(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper) {
        return new GetAccountsByCustomerIdService(accountOutputPort, accountMapper);
    }

    @Bean
    FreezeAccountService freezeAccountService(AccountOutputPort accountOutputPort) {
        return new FreezeAccountService(accountOutputPort);
    }

    @Bean
    UpdateCustomerProfileService updateCustomerProfileService(CustomerOutputPort customerOutputPort) {
        return new UpdateCustomerProfileService(customerOutputPort);
    }

    @Bean
    GetInterestRatesService getInterestRatesService(InterestRateOutputPort interestRateOutputPort) {
        return new GetInterestRatesService(interestRateOutputPort);
    }

    @Bean
    CreateAccountService createAccountService(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper) {
        return new CreateAccountService(accountOutputPort, accountMapper);
    }

    @Bean
    GetAllCustomersUseCase getAllCustomersUseCase(
            CustomerOutputPort customerOutputPort,
            CustomerApplicationMapper customerMapper) {
        return new GetAllCustomersService(customerOutputPort, customerMapper);
    }

    @Bean
    GetCustomerByIdUseCase getCustomerByIdUseCase(
            CustomerOutputPort customerOutputPort,
            CustomerApplicationMapper customerMapper) {
        return new GetCustomerByIdService(customerOutputPort, customerMapper);
    }

    @Bean
    GetAccountByIdUseCase getAccountByIdUseCase(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper) {
        return new GetAccountByIdService(accountOutputPort, accountMapper);
    }

    @Bean
    GetCustomerByEmailUseCase getCustomerByEmailUseCase(
            CustomerOutputPort customerOutputPort,
            CustomerApplicationMapper customerMapper) {
        return new GetCustomerByEmailService(customerOutputPort, customerMapper);
    }

    @Bean
    GetAccountsSummaryUseCase getAccountsSummaryUseCase(
            AccountOutputPort accountOutputPort,
            AccountApplicationMapper accountMapper
    ) {
        return new GetAccountsSummaryService(accountOutputPort, accountMapper);
    }


    @Bean
    AccountMapper accountMapper() {
        return new AccountMapperCustomImpl();
    }

    @Bean
    CustomerMapper customerMapper() {
        return new CustomerMapperCustomImpl();
    }

    @Bean
    InterestRateMapper interestRateMapper() {
        return new InterestRateMapperImpl();
    }

    @Bean
    CustomerApplicationMapper customerApplicationMapper() {
        return new CustomerApplicationMapperImpl();
    }

    @Bean
    AccountApplicationMapper accountApplicationMapper() {
        return new AccountApplicationMapperImpl();
    }

}
