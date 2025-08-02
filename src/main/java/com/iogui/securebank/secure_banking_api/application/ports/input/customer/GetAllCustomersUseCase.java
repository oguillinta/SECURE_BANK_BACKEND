package com.iogui.securebank.secure_banking_api.application.ports.input.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetAllCustomersResponse;

import java.util.List;

public interface GetAllCustomersUseCase {
    List<GetAllCustomersResponse> getAllCustomers();
}
