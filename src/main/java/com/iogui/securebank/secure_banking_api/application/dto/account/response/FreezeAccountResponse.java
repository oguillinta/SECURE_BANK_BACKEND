package com.iogui.securebank.secure_banking_api.application.dto.account.response;

import java.time.Instant;

public record FreezeAccountResponse(
        String accountNumber,
        String previousStatus,
        String currentStatus,
        String freezeReferenceNumber,
        Instant actionTimestamp,
        String freezeType,
        String authorizedBy,
        Instant nextReviewDate,
        String message
) {}