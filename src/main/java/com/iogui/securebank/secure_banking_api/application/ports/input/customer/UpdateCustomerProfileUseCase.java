package com.iogui.securebank.secure_banking_api.application.ports.input.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.request.UpdateCustomerRequest;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.UpdateCustomerResponse;

public interface UpdateCustomerProfileUseCase {
    UpdateCustomerResponse updateProfile(UpdateCustomerRequest request);
}
