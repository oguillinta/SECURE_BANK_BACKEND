package com.iogui.securebank.secure_banking_api.application.mapper.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetAllCustomersResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByEmailResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByIdResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Customer;

public class CustomerApplicationMapperImpl implements CustomerApplicationMapper {
    @Override
    public GetCustomerByEmailResponse mapToDto(Customer customer) {
        return new GetCustomerByEmailResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCustomerType(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    @Override
    public GetAllCustomersResponse mapToGetAllCustomerResponse(Customer customer) {
        return new GetAllCustomersResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCustomerType(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    @Override
    public GetCustomerByIdResponse mapToGetCustomerByIdResponse(Customer customer) {
        return new GetCustomerByIdResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCustomerType(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
