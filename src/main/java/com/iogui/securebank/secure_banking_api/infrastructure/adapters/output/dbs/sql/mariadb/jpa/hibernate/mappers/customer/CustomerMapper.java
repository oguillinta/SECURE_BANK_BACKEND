package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.mappers.customer;

import com.iogui.securebank.secure_banking_api.domain.entity.Customer;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.CustomerData;

public interface CustomerMapper {
    Customer mapToDomain(CustomerData entity);
    CustomerData mapToData(Customer domain);
}
