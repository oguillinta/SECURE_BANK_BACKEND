package com.iogui.securebank.secure_banking_api.infrastructure.adapters.input.api.rest.controller;

import com.iogui.securebank.secure_banking_api.application.dto.customer.request.UpdateCustomerRequest;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetAllCustomersResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByEmailResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByIdResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.UpdateCustomerResponse;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetAllCustomersUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetCustomerByEmailUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetCustomerByIdUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.UpdateCustomerProfileUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);

    private final UpdateCustomerProfileUseCase updateCustomerProfileUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetCustomerByEmailUseCase getCustomerByEmailUseCase;

    public CustomerRestController(
            UpdateCustomerProfileUseCase updateCustomerProfileUseCase,
            GetAllCustomersUseCase getAllCustomersUseCase,
            GetCustomerByIdUseCase getCustomerByIdUseCase,
            GetCustomerByEmailUseCase getCustomerByEmailUseCase) {
        this.updateCustomerProfileUseCase = updateCustomerProfileUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
        this.getCustomerByEmailUseCase =  getCustomerByEmailUseCase;
    }

    @PreAuthorize("hasAuthority('PROFILE_UPDATER')")
    @PutMapping("/api/v1/customers/{customerId}/profile")
    public ResponseEntity<UpdateCustomerResponse> updateProfile(
            @PathVariable String customerId,
            @RequestBody UpdateCustomerRequest request
            ) {
        logger.info("REST request received: update profile for customer {} by {}",
                customerId, request.updatedBy());
        UpdateCustomerResponse response = updateCustomerProfileUseCase.updateProfile(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('CUSTOMER_VIEWER')")
    @GetMapping("/api/v1/customers")
    public ResponseEntity<List<GetAllCustomersResponse>> getAllCustomers() {
        List<GetAllCustomersResponse> response = getAllCustomersUseCase.getAllCustomers();
        return ResponseEntity.ok(response);

    }

    @PreAuthorize("hasAuthority('CUSTOMER_VIEWER')")
    @GetMapping("/api/v1/customers/{email}/email")
    public ResponseEntity<GetCustomerByEmailResponse> getCustomerByEmail(@PathVariable String email) {
        GetCustomerByEmailResponse response = getCustomerByEmailUseCase.getCustomerByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('CUSTOMER_VIEWER')")
    @GetMapping("/api/v1/customers/{customerId}")
    public ResponseEntity<GetCustomerByIdResponse> getCustomerById(@PathVariable String customerId) {
        GetCustomerByIdResponse response = getCustomerByIdUseCase.getCustomerById(customerId);
        return ResponseEntity.ok(response);

    }

}
