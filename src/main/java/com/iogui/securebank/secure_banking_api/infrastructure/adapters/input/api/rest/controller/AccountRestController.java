package com.iogui.securebank.secure_banking_api.infrastructure.adapters.input.api.rest.controller;

import com.iogui.securebank.secure_banking_api.application.dto.account.request.CreateAccountRequest;
import com.iogui.securebank.secure_banking_api.application.dto.account.request.FreezeAccountRequest;
import com.iogui.securebank.secure_banking_api.application.dto.account.response.*;
import com.iogui.securebank.secure_banking_api.application.ports.input.account.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {
    private final GetAccountsByCustomerIdUseCase getAccountsByCustomerIdUseCase;
    private final FreezeAccountUseCase freezeAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final GetAccountsSummaryUseCase getAccountsSummaryUseCase;

    public AccountRestController(
            GetAccountsByCustomerIdUseCase getAccountsByCustomerIdUseCase,
            FreezeAccountUseCase freezeAccountUseCase,
            CreateAccountUseCase createAccountUseCase,
            GetAccountByIdUseCase getAccountByIdUseCase,
            GetAccountsSummaryUseCase getAccountsSummaryUseCase) {
        this.getAccountsByCustomerIdUseCase = getAccountsByCustomerIdUseCase;
        this.freezeAccountUseCase = freezeAccountUseCase;
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.getAccountsSummaryUseCase = getAccountsSummaryUseCase;
    }

    @PreAuthorize("hasAuthority('ACCOUNT_SUMMARY_VIEWER')")
    @GetMapping("/api/v1/accounts/{customerId}")
    public ResponseEntity<List<GetAccountByCustomerIdResponse>> getAccountsByCustomerId(@PathVariable String customerId) {
        List<GetAccountByCustomerIdResponse> response = getAccountsByCustomerIdUseCase.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('REPORT_VIEWER')")
    @GetMapping("/api/v1/accounts/reports/summary")
    public ResponseEntity<List<GetAccountSummaryResponse>> getAccountsSummary() {
        List<GetAccountSummaryResponse> response = getAccountsSummaryUseCase.getAccountsSummary();
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAuthority('ACCOUNT_SUMMARY_VIEWER')")
    @GetMapping("/api/v1/accounts/beta/{accountNumber}")
    public ResponseEntity<GetAccountByIdResponse> getAccountById(@PathVariable String accountNumber) {
        GetAccountByIdResponse response = getAccountByIdUseCase.getAccountById(accountNumber) ;
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_CREATOR')")
    @PostMapping("/api/v1/accounts")
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        CreateAccountResponse response = createAccountUseCase.createAccount(
                request.customerId(),
                request.accountType(),
                request.initialBalance()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_FREEZER')")
    @PutMapping("/api/v1/accounts/{accountNumber}/freeze")
    public ResponseEntity<FreezeAccountResponse> freezeAccount(
            @PathVariable String accountNumber,
            @RequestBody FreezeAccountRequest request) {
        FreezeAccountResponse response = freezeAccountUseCase.freezeAccount(request);
        return ResponseEntity.ok(response);
    }
}
