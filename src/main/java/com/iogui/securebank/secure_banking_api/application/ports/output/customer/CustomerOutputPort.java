package com.iogui.securebank.secure_banking_api.application.ports.output.customer;

import com.iogui.securebank.secure_banking_api.domain.entity.Account;
import com.iogui.securebank.secure_banking_api.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerOutputPort {
    Customer updateCustomer (Customer customer);
    Optional<Customer> getCustomerById(String customerId);
    Optional<Customer> getCustomerByEmail(String email);
    List<Customer> findAll();
}
