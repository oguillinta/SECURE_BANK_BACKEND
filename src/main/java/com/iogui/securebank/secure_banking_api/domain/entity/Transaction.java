package com.iogui.securebank.secure_banking_api.domain.entity;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private final String transactionId;
    private final String fromAccount;
    private final String toAccount;
    private final BigDecimal amount;
    private final String transactionType;
    private final String initiatedBy;
    private String description;
    private String status;
    private String approvedBy;
    private String channel;
    private final Instant transactionDate;
    private final Instant createdAt;
    private Instant updatedAt;

    // Private constructor - forces use of static factory methods
    private Transaction(
            String transactionId,
            String fromAccount,
            String toAccount,
            BigDecimal amount,
            String transactionType,
            String initiatedBy) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionType = transactionType;
        this.initiatedBy = initiatedBy;
        this.status = "PENDING";
        this.transactionDate = Instant.now();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.channel = "ONLINE";
    }

    // ============================================================================
    // STATIC FACTORY METHODS - PERUVIAN BANKING OPERATIONS
    // ============================================================================

    /**
     * Creates a transfer between accounts (most common operation)
     */
    public static Transaction createTransfer(
            String fromAccount,
            String toAccount,
            BigDecimal amount,
            String initiatedBy) {
        String transactionId = generateTransactionId("TRF");
        return new Transaction(transactionId, fromAccount, toAccount, amount,
                "TRANSFER", initiatedBy);
    }

    /**
     * Creates a deposit transaction
     */
    public static Transaction createDeposit(
            String toAccount,
            BigDecimal amount,
            String initiatedBy) {
        String transactionId = generateTransactionId("DEP");
        return new Transaction(transactionId, null, toAccount, amount,
                "DEPOSIT", initiatedBy);
    }

    /**
     * Creates a withdrawal transaction
     */
    public static Transaction createWithdrawal(
            String fromAccount,
            BigDecimal amount,
            String initiatedBy) {
        String transactionId = generateTransactionId("WTH");
        return new Transaction(transactionId, fromAccount, null, amount,
                "WITHDRAWAL", initiatedBy);
    }

    /**
     * Creates a salary payment (very common in Peru)
     */
    public static Transaction createSalaryPayment(
            String payrollAccount,
            BigDecimal amount,
            String companyUserId) {
        String transactionId = generateTransactionId("SAL");
        Transaction transaction = new Transaction(transactionId, null, payrollAccount,
                amount, "SALARY_PAYMENT", companyUserId);
        transaction.description = "Salary payment";
        transaction.channel = "BANK_SYSTEM";
        return transaction;
    }

    /**
     * Creates a bill payment transaction
     */
    public static Transaction createBillPayment(
            String fromAccount,
            BigDecimal amount,
            String initiatedBy,
            String billProvider) {
        String transactionId = generateTransactionId("PAY");
        Transaction transaction = new Transaction(transactionId, fromAccount, null,
                amount, "BILL_PAYMENT", initiatedBy);
        transaction.description = "Payment to " + billProvider;
        return transaction;
    }

    /**
     * Creates an ATM withdrawal
     */
    public static Transaction createATMWithdrawal(
            String fromAccount,
            BigDecimal amount,
            String initiatedBy, String atmLocation) {
        String transactionId = generateTransactionId("ATM");
        Transaction transaction = new Transaction(transactionId, fromAccount, null,
                amount, "ATM_WITHDRAWAL", initiatedBy);
        transaction.channel = "ATM";
        transaction.description = "ATM withdrawal at " + atmLocation;
        return transaction;
    }

    // Simple transaction ID generator
    private static String generateTransactionId(String prefix) {
        return prefix + "-" + System.currentTimeMillis();
    }

    // ============================================================================
    // BUSINESS METHODS
    // ============================================================================

    /**
     * Checks if transaction is pending
     */
    public boolean isPending() {
        return "PENDING".equals(status);
    }

    /**
     * Checks if transaction is completed
     */
    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }

    /**
     * Checks if transaction requires approval
     */
    public boolean isApprovalRequired() {
        return "REQUIRES_APPROVAL".equals(status);
    }

    /**
     * Checks if transaction failed
     */
    public boolean isFailed() {
        return "FAILED".equals(status);
    }

    /**
     * Checks if this is a transfer between accounts
     */
    public boolean isTransfer() {
        return "TRANSFER".equals(transactionType);
    }

    /**
     * Checks if this is a deposit
     */
    public boolean isDeposit() {
        return "DEPOSIT".equals(transactionType) || "SALARY_PAYMENT".equals(transactionType);
    }

    /**
     * Checks if this is a withdrawal
     */
    public boolean isWithdrawal() {
        return "WITHDRAWAL".equals(transactionType) || "ATM_WITHDRAWAL".equals(transactionType);
    }

    /**
     * Checks if transaction involves external payment
     */
    public boolean isPayment() {
        return "BILL_PAYMENT".equals(transactionType);
    }

    /**
     * Marks transaction as completed
     */
    public void markAsCompleted() {
        this.status = "COMPLETED";
        markAsUpdated();
    }

    /**
     * Marks transaction as failed with reason
     */
    public void markAsFailed() {
        this.status = "FAILED";
        markAsUpdated();
    }

    /**
     * Marks transaction as requiring approval
     */
    public void markAsPendingApproval() {
        this.status = "REQUIRES_APPROVAL";
        markAsUpdated();
    }

    /**
     * Approves the transaction
     */
    public void approve(String approvedBy) {
        if (!isApprovalRequired()) {
            throw new IllegalStateException("Transaction is not pending approval");
        }
        this.approvedBy = approvedBy;
        this.status = "COMPLETED";
        markAsUpdated();
    }

    /**
     * Rejects the transaction
     */
    public void reject(String rejectedBy) {
        if (!isApprovalRequired()) {
            throw new IllegalStateException("Transaction is not pending approval");
        }
        this.approvedBy = rejectedBy;
        this.status = "REJECTED";
        markAsUpdated();
    }

    /**
     * Sets transaction description
     */
    public void setDescription(String description) {
        this.description = description;
        markAsUpdated();
    }

    /**
     * Sets transaction channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
        markAsUpdated();
    }

    /**
     * Checks if transaction can be reversed
     */
    public boolean canBeReversed() {
        return isCompleted() && !isPayment(); // External payments usually can't be reversed
    }

    /**
     * Gets the account that money comes from
     */
    public String getSourceAccount() {
        return fromAccount;
    }

    /**
     * Gets the account that money goes to
     */
    public String getDestinationAccount() {
        return toAccount;
    }

    private void markAsUpdated() {
        this.updatedAt = Instant.now();
    }

    // ============================================================================
    // GETTERS (NO PUBLIC SETTERS - IMMUTABLE WHERE POSSIBLE)
    // ============================================================================

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public String getChannel() {
        return channel;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", type='" + transactionType + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", from='" + fromAccount + '\'' +
                ", to='" + toAccount + '\'' +
                '}';
    }
}
