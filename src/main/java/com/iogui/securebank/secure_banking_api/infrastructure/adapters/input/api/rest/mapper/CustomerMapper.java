package com.iogui.securebank.secure_banking_api.infrastructure.adapters.input.api.rest.mapper;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetAllCustomersResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Customer;

import java.util.List;

public interface CustomerMapper {
    List<GetAllCustomersResponse> mapToDto(List<Customer> domain);
}
