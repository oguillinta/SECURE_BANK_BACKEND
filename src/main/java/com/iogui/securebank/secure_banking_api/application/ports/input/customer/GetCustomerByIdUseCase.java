package com.iogui.securebank.secure_banking_api.application.ports.input.customer;


import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByIdResponse;

public interface GetCustomerByIdUseCase {
     GetCustomerByIdResponse getCustomerById(String id);
}
