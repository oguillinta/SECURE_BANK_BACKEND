package com.iogui.securebank.secure_banking_api.application.dto.customer.request;

public record UpdateCustomerRequest(
        String customerId,
        String firstName,
        String lastName,
        String email,
        String customerType, // INDIVIDUAL, BUSINESS, PREMIUM
        String status, // ACTIVE, INACTIVE, SUSPENDED, CLOSED
        String updatedBy,
        String updateReason
) {
}
