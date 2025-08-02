package com.iogui.securebank.secure_banking_api.application.ports.input.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByEmailResponse;

@FunctionalInterface
public interface GetCustomerByEmailUseCase {
    GetCustomerByEmailResponse getCustomerByEmail(String email);
}
