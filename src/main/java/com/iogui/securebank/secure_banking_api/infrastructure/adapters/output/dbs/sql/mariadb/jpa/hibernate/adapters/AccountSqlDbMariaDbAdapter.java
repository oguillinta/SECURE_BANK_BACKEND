package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.adapters;

import com.iogui.securebank.secure_banking_api.application.ports.output.account.AccountOutputPort;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import com.iogui.securebank.secure_banking_api.domain.entity.AccountSummaryReport;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.AccountData;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.account.AccountMapper;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class AccountSqlDbMariaDbAdapter implements AccountOutputPort {

    private static final Logger logger = LoggerFactory.getLogger(AccountSqlDbMariaDbAdapter.class);
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountSqlDbMariaDbAdapter(
            AccountRepository accountRepository,
            AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    @Override
    public Account createAccount(Account account) {
        logger.debug("Creating new account for customer: {}", account.getCustomerId());

        AccountData savedAccount = accountRepository.save(accountMapper.mapToData(account));
        return accountMapper.mapToDomain(savedAccount);
    }

    @Override
    public Account updateAccount(Account account) {
        logger.debug("Updating account: {} (Status: {}, Frozen: {})",
                account.getAccountNumber(), account.getStatus(), account.isFrozen());

        try {
            // Verify account exists
            if (!accountRepository.existsById(account.getAccountNumber())) {
                throw new IllegalArgumentException("Account not found for update: " + account.getAccountNumber());
            }

            AccountData entity = accountMapper.mapToData(account);
            AccountData savedEntity = accountRepository.save(entity);

            Account updatedAccount = accountMapper.mapToDomain(savedEntity);

            logger.debug("Updated account: {} - Freeze status changed. Type: {}, Reference: {}",
                    updatedAccount.getAccountNumber(),
                    updatedAccount.getFreezeType(),
                    updatedAccount.getFreezeReferenceNumber());

            return updatedAccount;

        } catch (Exception e) {
            logger.error("Error updating account: {}", account.getAccountNumber(), e);
            throw new RuntimeException("Failed to update account", e);
        }
    }

    @Override
    public Optional<Account> getAccountById(String accountNumber) {
        logger.debug("Getting account by ID: {}", accountNumber);

        try {
            Optional<AccountData> entityOpt = accountRepository.findByAccountNumber(accountNumber);

            if (entityOpt.isPresent()) {
                Account account = accountMapper.mapToDomain(entityOpt.get());
                logger.debug("Found account with ID: {} (Status: {}, Frozen: {})",
                        accountNumber, account.getStatus(), account.isFrozen());
                return Optional.of(account);
            } else {
                logger.debug("No account found with ID: {}", accountNumber);
                return Optional.empty();
            }

        } catch (Exception e) {
            logger.error("Error getting account by ID: {}", accountNumber, e);
            throw new RuntimeException("Failed to get account", e);
        }
    }

    @Override
    public List<Account> getAccountsByCustomerId(String customerId) {
        logger.debug("Getting accounts for customer: {}", customerId);

        List<AccountData> dbAccounts = accountRepository.findByCustomerId(customerId);

        return dbAccounts.stream()
                .map(accountMapper::mapToDomain)
                .toList();
    }

    @Override
    public List<AccountSummaryReport> getAccountsSummary() {
        logger.debug("Getting the account summary report ");
        return accountRepository.findAccountsSummary()
                .stream()
                .map(accountMapper::mapToAccountSummaryReportv2)
                .toList();
    }


}
