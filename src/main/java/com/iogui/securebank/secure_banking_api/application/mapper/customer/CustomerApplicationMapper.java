package com.iogui.securebank.secure_banking_api.application.mapper.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetAllCustomersResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByEmailResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByIdResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Customer;

public interface CustomerApplicationMapper {
    GetCustomerByEmailResponse mapToDto(Customer customer);
    GetAllCustomersResponse mapToGetAllCustomerResponse(Customer customer);
    GetCustomerByIdResponse mapToGetCustomerByIdResponse(Customer customer);
}
