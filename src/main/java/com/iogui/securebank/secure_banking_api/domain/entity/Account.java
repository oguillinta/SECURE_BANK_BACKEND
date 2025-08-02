package com.iogui.securebank.secure_banking_api.domain.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class Account {


    private final String accountNumber;
    private final String customerId;
    private final String accountType;
    private BigDecimal balance;
    private BigDecimal dailyTransactionLimit;
    private String status;
    private final Instant createdAt;
    private Instant updatedAt;
    // NEW FREEZE-RELATED FIELDS (MINIMAL)
    private String freezeReason;
    private String freezeType; // FULL_FREEZE, DEBIT_ONLY, CREDIT_ONLY
    private Instant freezeDate;
    private String frozenBy;
    private Instant freezeReviewDate;
    private String freezeReferenceNumber;

    public Account(
            String accountNumber,
            String customerId,
            String accountType,
            BigDecimal balance,
            BigDecimal dailyTransactionLimit,
            String status,
            Instant createdAt,
            Instant updatedAt) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.dailyTransactionLimit = dailyTransactionLimit;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Account(String accountNumber, String customerId, String accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = BigDecimal.ZERO;
        this.dailyTransactionLimit = new BigDecimal("1000.00");
        this.status = "ACTIVE";
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public static Account createSavingsAccount(String customerId) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, customerId, "SAVINGS_ACCOUNT");
        account.dailyTransactionLimit = new BigDecimal("1500.00"); // S/ 1,500 daily limit
        return account;
    }

    public static Account createCurrentAccount(String customerId) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, customerId, "CURRENT_ACCOUNT");
        account.dailyTransactionLimit = new BigDecimal("5000.00"); // S/ 5,000 daily limit
        return account;
    }

    public static Account createPayrollAccount(String customerId) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, customerId, "PAYROLL_ACCOUNT");
        account.dailyTransactionLimit = new BigDecimal("2000.00"); // S/ 2,000 daily limit
        return account;
    }

    private static String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis();
    }

    public boolean canTransact() {
        return "ACTIVE".equals(status);
    }

    public boolean isBusinessAccount() {
        return accountType != null && accountType.startsWith("BUSINESS");
    }

    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        balance = balance.subtract(amount);
        markAsUpdated();
    }

    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
        markAsUpdated();
    }

    public boolean isFrozen() {
        return "FROZEN".equals(status);
    }

    public void freeze(String reason, String freezeType, String frozenBy, String referenceNumber) {
        if (isFrozen()) {
            throw new IllegalStateException("Account is already frozen");
        }

        this.status = "FROZEN";
        this.freezeReason = reason;
        this.freezeType = freezeType;
        this.freezeDate = Instant.now();
        this.frozenBy = frozenBy;
        this.freezeReferenceNumber = referenceNumber;
        this.updatedAt = Instant.now();
    }

    public void unfreeze(String unfrozenBy) {
        if (!isFrozen()) {
            throw new IllegalStateException("Account is not frozen");
        }

        this.status = "ACTIVE";
        this.freezeReason = null;
        this.freezeType = null;
        this.freezeDate = null;
        this.frozenBy = null;
        this.freezeReferenceNumber = null;
        this.freezeReviewDate = null;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.status = "ACTIVE";
        markAsUpdated();
    }

    private void markAsUpdated() {
        this.updatedAt = Instant.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getDailyTransactionLimit() {
        return dailyTransactionLimit;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setDailyTransactionLimit(BigDecimal dailyTransactionLimit) {
        this.dailyTransactionLimit = dailyTransactionLimit;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }

    public String getFreezeType() {
        return freezeType;
    }

    public void setFreezeType(String freezeType) {
        this.freezeType = freezeType;
    }

    public Instant getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(Instant freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getFrozenBy() {
        return frozenBy;
    }

    public void setFrozenBy(String frozenBy) {
        this.frozenBy = frozenBy;
    }

    public Instant getFreezeReviewDate() {
        return freezeReviewDate;
    }

    public void setFreezeReviewDate(Instant freezeReviewDate) {
        this.freezeReviewDate = freezeReviewDate;
    }

    public String getFreezeReferenceNumber() {
        return freezeReferenceNumber;
    }

    public void setFreezeReferenceNumber(String freezeReferenceNumber) {
        this.freezeReferenceNumber = freezeReferenceNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
