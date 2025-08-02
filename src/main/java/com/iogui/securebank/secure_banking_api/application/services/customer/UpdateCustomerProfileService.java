package com.iogui.securebank.secure_banking_api.application.services.customer;

import com.iogui.securebank.secure_banking_api.application.dto.customer.request.UpdateCustomerRequest;
import com.iogui.securebank.secure_banking_api.application.dto.customer.response.UpdateCustomerResponse;
import com.iogui.securebank.secure_banking_api.application.ports.input.customer.UpdateCustomerProfileUseCase;
import com.iogui.securebank.secure_banking_api.application.ports.output.customer.CustomerOutputPort;
import com.iogui.securebank.secure_banking_api.domain.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class UpdateCustomerProfileService implements UpdateCustomerProfileUseCase {

    private static final Logger logger = LoggerFactory.getLogger(UpdateCustomerProfileService.class);
    private final CustomerOutputPort customerOutputPort;

    public UpdateCustomerProfileService(CustomerOutputPort customerOutputPort) {
        this.customerOutputPort = customerOutputPort;
    }


    @Override
    public UpdateCustomerResponse updateProfile(UpdateCustomerRequest request) {
        logger.info("Updating profile for customer {} by {}",
                request.customerId(), request.updatedBy());

        // Get existing customer from database - this will throw exception if not found
        Customer existingCustomer = customerOutputPort.getCustomerById(request.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Update the existing customer with new data
        updateCustomerFields(existingCustomer, request);

        // Save the updated customer
        Customer updatedCustomer = customerOutputPort.updateCustomer(existingCustomer);

        logger.info("Profile updated successfully for customer {}", request.customerId());

        return new UpdateCustomerResponse(
                updatedCustomer.getCustomerId(),
                updatedCustomer.getFirstName(),
                updatedCustomer.getLastName(),
                updatedCustomer.getEmail(),
                updatedCustomer.getCustomerType(),
                updatedCustomer.getStatus(),
                updatedCustomer.getCreatedAt(),
                updatedCustomer.getUpdatedAt(),
                "Profile updated successfully"
        );
    }

    private void updateCustomerFields(Customer customer, UpdateCustomerRequest request) {
        // Update only the fields that are provided in the request (null-safe updates)
        if (request.firstName() != null && !request.firstName().trim().isEmpty()) {
            customer.setFirstName(request.firstName().trim());
        }

        if (request.lastName() != null && !request.lastName().trim().isEmpty()) {
            customer.setLastName(request.lastName().trim());
        }

        if (request.email() != null && !request.email().trim().isEmpty()) {
            customer.setEmail(request.email().trim().toLowerCase());
        }

        if (request.customerType() != null && !request.customerType().trim().isEmpty()) {
            customer.setCustomerType(request.customerType().trim());
        }

        // Always update the timestamp
        customer.setUpdatedAt(Instant.now());
    }
}
