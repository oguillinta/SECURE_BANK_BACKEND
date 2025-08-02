package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "accounts")
public class AccountData {
    @Id
    @Column(name = "account_number", length = 50)
    private String accountNumber;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "account_type", nullable = false, length = 30)
    private String accountType;

    @Column(name = "balance", precision = 15, scale = 2, nullable = false)
    private BigDecimal balance;

    @Column(name = "daily_transaction_limit", precision = 15, scale = 2)
    private BigDecimal dailyTransactionLimit;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    // ========================================================================
    // NEW FREEZE-RELATED FIELDS
    // ========================================================================

    @Column(name = "freeze_reason", length = 255)
    private String freezeReason;

    @Column(name = "freeze_type", length = 30)
    private String freezeType;

    @Column(name = "freeze_date")
    private Instant freezeDate;

    @Column(name = "frozen_by", length = 50)
    private String frozenBy;

    @Column(name = "freeze_review_date")
    private Instant freezeReviewDate;

    @Column(name = "freeze_reference_number", length = 50)
    private String freezeReferenceNumber;

    public AccountData() {
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

    public AccountData(
            String accountNumber,
            String customerId,
            String accountType,
            BigDecimal balance,
            BigDecimal dailyTransactionLimit,
            String status) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.dailyTransactionLimit = dailyTransactionLimit;
        this.status = status;
        // Initialize freeze fields as null
        this.freezeReason = null;
        this.freezeType = null;
        this.freezeDate = null;
        this.frozenBy = null;
        this.freezeReviewDate = null;
        this.freezeReferenceNumber = null;
    }

    /**
     * Full constructor with freeze fields
     */
    public AccountData(String accountNumber, String customerId, String accountType,
                            BigDecimal balance, BigDecimal dailyTransactionLimit, String status,
                            Instant createdAt, Instant updatedAt,
                            String freezeReason, String freezeType, Instant freezeDate,
                            String frozenBy, Instant freezeReviewDate, String freezeReferenceNumber) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.dailyTransactionLimit = dailyTransactionLimit;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.freezeReason = freezeReason;
        this.freezeType = freezeType;
        this.freezeDate = freezeDate;
        this.frozenBy = frozenBy;
        this.freezeReviewDate = freezeReviewDate;
        this.freezeReferenceNumber = freezeReferenceNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getDailyTransactionLimit() {
        return dailyTransactionLimit;
    }

    public void setDailyTransactionLimit(BigDecimal dailyTransactionLimit) {
        this.dailyTransactionLimit = dailyTransactionLimit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
