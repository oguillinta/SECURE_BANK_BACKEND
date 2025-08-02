package com.iogui.securebank.secure_banking_api.application.services.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetAllCustomersResponse;
import com.iogui.securebank.secure_banking_api.application.mapper.customer.CustomerApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetAllCustomersUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.customer.CustomerOutputPort;

import java.util.List;

public class GetAllCustomersService implements GetAllCustomersUseCase {
    private final CustomerOutputPort customerOutputPort;
    private final CustomerApplicationMapper customerMapper;

    public GetAllCustomersService(
            CustomerOutputPort customerOutputPort,
            CustomerApplicationMapper customerMapper) {
        this.customerOutputPort = customerOutputPort;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<GetAllCustomersResponse> getAllCustomers() {
        return customerOutputPort.findAll().stream().map(customerMapper::mapToGetAllCustomerResponse).toList();
    }
}
