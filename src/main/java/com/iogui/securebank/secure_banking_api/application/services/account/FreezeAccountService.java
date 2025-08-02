package com.iogui.securebank.secure_banking_api.application.services.account;

import com.iogui.securebank.secure_banking_api.application.dto.account.request.FreezeAccountRequest;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.FreezeAccountResponse;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.FreezeAccountUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.account.AccountOutputPort;
import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class FreezeAccountService implements FreezeAccountUseCase {
    private static final Logger logger = LoggerFactory.getLogger(FreezeAccountService.class);
    private final AccountOutputPort accountOutputPort;

    public FreezeAccountService(AccountOutputPort accountOutputPort) {
        this.accountOutputPort = accountOutputPort;
    }



    @Override
    public FreezeAccountResponse freezeAccount(FreezeAccountRequest request) {
        logger.info("Processing freeze account request for account: {} - Type : {}", request.accountNumber(), request.freezeType());
        Account account = accountOutputPort.getAccountById(request.accountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        String previousStatus = account.getStatus();
        String referenceNumber = generateFreezeReference(request.freezeType());
        if ("FREEZE".equals(request.action())) {
            account.freeze(request.reason(), request.freezeType(), request.authorizedBy(), referenceNumber);

            if (request.reviewDate() != null) {
                account.setFreezeReviewDate(request.reviewDate());
            }

        } else if ("UNFREEZE".equals(request.action())) {
            account.unfreeze(request.authorizedBy());

        } else {
            throw new IllegalArgumentException("Invalid action: " + request.action());
        }
        Account updatedAccount = accountOutputPort.updateAccount(account);
        logger.info("Account freeze operation completed - Account: {}, Status: {} -> {}",
                request.accountNumber(), previousStatus, updatedAccount.getStatus());
        return new FreezeAccountResponse(
                request.accountNumber(),
                previousStatus,
                updatedAccount.getStatus(),
                referenceNumber,
                Instant.now(),
                updatedAccount.getFreezeType(),
                request.authorizedBy(),
                updatedAccount.getFreezeReviewDate(),
                String.format("Account %s successfully",
                        "FREEZE".equals(request.action()) ? "frozen" : "unfrozen")
        );
    }

    private String generateFreezeReference(String freezeType) {
        return "FRZ-" + freezeType + "-" + System.currentTimeMillis();
    }
}
