package com.iogui.securebank.secure_banking_api.application.dto.customer.response;

import java.time.Instant;

public record UpdateCustomerResponse(
        String customerId,
        String firstName,
        String lastName,
        String email,
        String customerType,
        String status,
        Instant createdAt,
        Instant updatedAt,
        String message
        ) { }
