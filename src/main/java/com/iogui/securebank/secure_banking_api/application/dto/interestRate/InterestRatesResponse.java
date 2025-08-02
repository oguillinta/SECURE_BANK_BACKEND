package com.iogui.securebank.secure_banking_api.application.dto.interestRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record InterestRatesResponse(
         Long id,
         String productType,
         String productName,
         BigDecimal rate,
         String term,
         BigDecimal minimumAmount,
         BigDecimal maximumAmount,
         String description,
         Boolean isActive,
         LocalDate effectiveDate,
         LocalDateTime updatedAt

) { }

