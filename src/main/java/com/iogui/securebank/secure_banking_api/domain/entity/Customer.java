package com.iogui.securebank.secure_banking_api.domain.entity;

import java.time.Instant;

public class Customer {
    private final String customerId;
    private String firstName;
    private  String lastName;
    private  String email;



    private  String customerType;
    private String status;
    private  Instant createdAt;
    private Instant updatedAt;

    public Customer(
            String customerId,
            String firstName,
            String lastName,
            String email,
            String customerType) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerType = customerType;
        this.status = "ACTIVE";
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ============================================================================
    // STATIC FACTORY METHODS - PERUVIAN BANKING CONTEXT
    // ============================================================================

    /**
     * Creates a personal customer (individual)
     * Most common customer type for personal banking
     */
    public static Customer createPersonalCustomer(String firstName, String lastName, String email) {
        String customerId = generateCustomerId("PER");
        return new Customer(customerId, firstName, lastName, email, "PERSONAL");
    }

    /**
     * Creates a business customer (company/organization)
     * For businesses that need corporate banking services
     */
    public static Customer createBusinessCustomer(String businessName, String contactEmail) {
        String customerId = generateCustomerId("BUS");
        return new Customer(customerId, businessName, "", contactEmail, "BUSINESS");
    }

    /**
     * Creates an employee customer (for payroll accounts)
     * Employees who receive salary through the bank
     */
    public static Customer createEmployeeCustomer(String firstName, String lastName,
                                                  String email) {
        String customerId = generateCustomerId("EMP");

        return new Customer(customerId, firstName, lastName, email, "EMPLOYEE");
    }

    // Simple customer ID generator
    private static String generateCustomerId(String prefix) {
        return prefix + System.currentTimeMillis();
    }

    // ============================================================================
    // BUSINESS METHODS
    // ============================================================================

    /**
     * Gets the full name of the customer
     */
    public String getFullName() {
        if (isBusinessCustomer()) {
            return firstName; // Business name is stored in firstName
        }
        return firstName + " " + lastName;
    }

    /**
     * Checks if customer is active
     */
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }

    /**
     * Checks if this is a business customer
     */
    public boolean isBusinessCustomer() {
        return "BUSINESS".equals(customerType);
    }

    /**
     * Checks if this is a personal customer
     */
    public boolean isPersonalCustomer() {
        return "PERSONAL".equals(customerType);
    }

    /**
     * Checks if this is an employee customer
     */
    public boolean isEmployeeCustomer() {
        return "EMPLOYEE".equals(customerType);
    }

    /**
     * Suspends the customer account
     */
    public void suspend() {
        this.status = "SUSPENDED";
        marAskUpdated();
    }

    /**
     * Activates the customer account
     */
    public void activate() {
        this.status = "ACTIVE";
        marAskUpdated();
    }

    /**
     * Blocks the customer account (for security reasons)
     */
    public void block() {
        this.status = "BLOCKED";
        marAskUpdated();
    }

    /**
     * Checks if customer can open new accounts
     */
    public boolean canOpenAccounts() {
        return isActive();
    }

    /**
     * Checks if customer can perform transactions
     */
    public boolean canTransact() {
        return isActive();
    }

    private void marAskUpdated() {
        this.updatedAt = Instant.now();
    }

    // ============================================================================
    // GETTERS (NO PUBLIC SETTERS - IMMUTABLE WHERE POSSIBLE)
    // ============================================================================

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerType() {
        return customerType;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                ", customerType='" + customerType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
