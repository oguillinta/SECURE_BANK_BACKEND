package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "interest_rates")
public class InterestRateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type", nullable = false, length = 50)
    @NotBlank(message = "Product type is required")
    private String productType;

    @Column(name = "product_name", nullable = false, length = 100)
    @NotBlank(message = "Product name is required")
    private String productName;

    @Column(nullable = false, precision = 5, scale = 4)
    @NotNull(message = "Rate is required")
    @DecimalMin(value = "0.0", message = "Rate must be positive")
    @DecimalMax(value = "1.0", message = "Rate must be less than 100%")
    private BigDecimal rate;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Term is required")
    private String term;

    @Column(name = "minimum_amount", nullable = false, precision = 15, scale = 2)
    @NotNull(message = "Minimum amount is required")
    @DecimalMin(value = "0.0", message = "Minimum amount must be positive")
    private BigDecimal minimumAmount;

    @Column(name = "maximum_amount", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", message = "Maximum amount must be positive")
    private BigDecimal maximumAmount;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "effective_date", nullable = false)
    @NotNull(message = "Effective date is required")
    private LocalDate effectiveDate;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Constructors
    public InterestRateData() {}

    public InterestRateData(String productType, String productName, BigDecimal rate, String term, BigDecimal minimumAmount) {
        this.productType = productType;
        this.productName = productName;
        this.rate = rate;
        this.term = term;
        this.minimumAmount = minimumAmount;
        this.effectiveDate = LocalDate.now();
        this.isActive = true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }

    public BigDecimal getMinimumAmount() { return minimumAmount; }
    public void setMinimumAmount(BigDecimal minimumAmount) { this.minimumAmount = minimumAmount; }

    public BigDecimal getMaximumAmount() { return maximumAmount; }
    public void setMaximumAmount(BigDecimal maximumAmount) { this.maximumAmount = maximumAmount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "InterestRate{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", rate=" + rate +
                ", term='" + term + '\'' +
                ", minimumAmount=" + minimumAmount +
                '}';
    }
}
