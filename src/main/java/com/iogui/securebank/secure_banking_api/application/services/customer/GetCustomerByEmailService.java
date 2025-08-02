package com.iogui.securebank.secure_banking_api.application.services.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.response.GetCustomerByEmailResponse;
import com.iogui.securebank.secure_banking_api.application.mapper.customer.CustomerApplicationMapper;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.GetCustomerByEmailUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.customer.CustomerOutputPort;

public class GetCustomerByEmailService implements GetCustomerByEmailUseCase {
    private final CustomerOutputPort customerOutputPort;
    private final CustomerApplicationMapper customerMapper;

    public GetCustomerByEmailService(CustomerOutputPort customerOutputPort, CustomerApplicationMapper customerMapper) {
        this.customerOutputPort = customerOutputPort;
        this.customerMapper = customerMapper;
    }

    @Override
    public GetCustomerByEmailResponse getCustomerByEmail(String email) {
        return customerMapper.mapToDto(this.customerOutputPort.getCustomerByEmail(email).get());
    }
}
