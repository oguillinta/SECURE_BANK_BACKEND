package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.adapters;

import com.iogui.securebank.secure_banking_api.application.ports.output.customer.CustomerOutputPort;
import com.iogui.securebank.secure_banking_api.domain.entity.Customer;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.CustomerData;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.customer.CustomerMapper;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class CustomerSqlDbMariaDbAdapter implements CustomerOutputPort {

    private static final Logger logger = LoggerFactory.getLogger(CustomerSqlDbMariaDbAdapter.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerSqlDbMariaDbAdapter(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        logger.debug("Updating customer: {}", customer.getCustomerId());
        CustomerData customerData = customerMapper.mapToData(customer);
        CustomerData savedCustomer = customerRepository.save(customerData);
        Customer updatedCustomer = customerMapper.mapToDomain(savedCustomer);
        logger.debug("Customer updated successfully: {}", customer.getCustomerId());

        return updatedCustomer;
    }


    @Override
    public Optional<Customer> getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::mapToDomain);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).map(customerMapper::mapToDomain);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::mapToDomain).toList();
    }
}
