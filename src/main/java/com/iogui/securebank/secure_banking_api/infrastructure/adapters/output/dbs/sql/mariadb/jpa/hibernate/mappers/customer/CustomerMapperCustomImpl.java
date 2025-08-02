package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.customer;


import com.iogui.securebank.secure_banking_api.domain.entity.Customer;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.CustomerData;

public class CustomerMapperCustomImpl implements CustomerMapper {
    @Override
    public Customer mapToDomain(CustomerData entity) {
        return new Customer(
                entity.getCustomerId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCustomerType()
        );
    }

    @Override
    public CustomerData mapToData(Customer domain) {
        return new CustomerData(
                domain.getFirstName(),
                domain.getCustomerId(),
                domain.getLastName(),
                domain.getEmail(),
                domain.getCustomerType(),
                domain.getStatus()
        );
    }
}
