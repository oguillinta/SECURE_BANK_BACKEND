package com.iogui.securebank.secure_banking_api.application.dto.account.request;

import java.time.Instant;

public record FreezeAccountRequest (
        String accountNumber,
        String action, // FREEZE, UNFREEZE
        String freezeType, // FULL_FREEZE, DEBIT_ONLY, CREDIT_ONLY
        String reason,
        String authorizedBy,
        String comments,
        Instant reviewDate // For temporary freezes
){}
