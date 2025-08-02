package com.iogui.securebank.secure_banking_api.application.services.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByIdResponse;
import com.iogui.securebank.secure_banking_api.application.mapper.customer.CustomerApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetCustomerByIdUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.customer.CustomerOutputPort;

public class GetCustomerByIdService implements GetCustomerByIdUseCase {
    private final CustomerOutputPort customerOutputPort;
    private final CustomerApplicationMapper customerMapper;

    public GetCustomerByIdService(
            CustomerOutputPort customerOutputPort,
            CustomerApplicationMapper customerMapper) {
        this.customerOutputPort = customerOutputPort;
        this.customerMapper = customerMapper;
    }

    @Override
    public GetCustomerByIdResponse getCustomerById(String id) {
        return this.customerOutputPort.getCustomerById(id)
                .map(customerMapper::mapToGetCustomerByIdResponse)
                .get();
    }
}
