package com.iogui.securebank.secure_banking_api.infrastructure.adapters.input.api.rest.mapper;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetAllCustomersResponse;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByIdResponse;
import com.iogui.securebank.secure_banking_api.domain.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapperImpl {

    public List<GetAllCustomersResponse> mapToDto(List<Customer> domainList) {
        return domainList.stream().map(domain -> new GetAllCustomersResponse(
                domain.getCustomerId(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getEmail(),
                domain.getCustomerType(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        )).toList();
    }

    public GetCustomerByIdResponse maptToDto(Customer domain) {
        return new GetCustomerByIdResponse(
                domain.getCustomerId(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getEmail(),
                domain.getCustomerType(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
