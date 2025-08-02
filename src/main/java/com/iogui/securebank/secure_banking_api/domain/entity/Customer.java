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

    public static Customer createPersonalCustomer(String firstName, String lastName, String email) {
        String customerId = generateCustomerId("PER");
        return new Customer(customerId, firstName, lastName, email, "PERSONAL");
    }

    public static Customer createBusinessCustomer(String businessName, String contactEmail) {
        String customerId = generateCustomerId("BUS");
        return new Customer(customerId, businessName, "", contactEmail, "BUSINESS");
    }

    public static Customer createEmployeeCustomer(String firstName, String lastName,
                                                  String email) {
        String customerId = generateCustomerId("EMP");

        return new Customer(customerId, firstName, lastName, email, "EMPLOYEE");
    }

    private static String generateCustomerId(String prefix) {
        return prefix + System.currentTimeMillis();
    }

    public String getFullName() {
        if (isBusinessCustomer()) {
            return firstName;
        }
        return firstName + " " + lastName;
    }


    public boolean isActive() {
        return "ACTIVE".equals(status);
    }

    public boolean isBusinessCustomer() {
        return "BUSINESS".equals(customerType);
    }

    public boolean isPersonalCustomer() {
        return "PERSONAL".equals(customerType);
    }

    public boolean isEmployeeCustomer() {
        return "EMPLOYEE".equals(customerType);
    }

    public void suspend() {
        this.status = "SUSPENDED";
        marAskUpdated();
    }

    public void activate() {
        this.status = "ACTIVE";
        marAskUpdated();
    }

    public void block() {
        this.status = "BLOCKED";
        marAskUpdated();
    }

    public boolean canOpenAccounts() {
        return isActive();
    }

    public boolean canTransact() {
        return isActive();
    }

    private void marAskUpdated() {
        this.updatedAt = Instant.now();
    }

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
