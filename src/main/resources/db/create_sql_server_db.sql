-- Create SECURE_BANKING_DB Database
CREATE DATABASE SECURE_BANKING_DB;
GO

-- Use the newly created database
USE SECURE_BANKING_DB;
GO

-- Create Customer table (parent table)
CREATE TABLE Customer (
    customerId NVARCHAR(50) PRIMARY KEY,
    firstName NVARCHAR(100) NOT NULL,
    lastName NVARCHAR(100) NOT NULL,
    email NVARCHAR(255) NOT NULL UNIQUE,
    customerType NVARCHAR(50) NOT NULL,
    status NVARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    createdAt DATETIME2(7) NOT NULL DEFAULT GETUTCDATE(),
    updatedAt DATETIME2(7) NOT NULL DEFAULT GETUTCDATE(),
    
    -- Constraints
    CONSTRAINT CK_Customer_Status CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'CLOSED')),
    CONSTRAINT CK_Customer_Type CHECK (customerType IN ('INDIVIDUAL', 'BUSINESS', 'CORPORATE', 'PREMIUM'))
);
GO

-- Create Account table (child table with foreign key to Customer)
CREATE TABLE Account (
    accountNumber NVARCHAR(50) PRIMARY KEY,
    customerId NVARCHAR(50) NOT NULL,
    accountType NVARCHAR(50) NOT NULL,
    balance DECIMAL(18,2) NOT NULL DEFAULT 0.00,
    dailyTransactionLimit DECIMAL(18,2) NOT NULL DEFAULT 5000.00,
    status NVARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    createdAt DATETIME2(7) NOT NULL DEFAULT GETUTCDATE(),
    updatedAt DATETIME2(7) NOT NULL DEFAULT GETUTCDATE(),
    
    -- Freeze-related fields
    freezeReason NVARCHAR(500) NULL,
    freezeType NVARCHAR(20) NULL,
    freezeDate DATETIME2(7) NULL,
    frozenBy NVARCHAR(100) NULL,
    freezeReviewDate DATETIME2(7) NULL,
    freezeReferenceNumber NVARCHAR(50) NULL,
    
    -- Foreign key constraint
    CONSTRAINT FK_Account_Customer FOREIGN KEY (customerId) REFERENCES Customer(customerId)
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    -- Check constraints
    CONSTRAINT CK_Account_Status CHECK (status IN ('ACTIVE', 'INACTIVE', 'FROZEN', 'CLOSED')),
    CONSTRAINT CK_Account_Type CHECK (accountType IN ('SAVINGS', 'CHECKING', 'CREDIT', 'BUSINESS', 'INVESTMENT')),
    CONSTRAINT CK_Account_Balance CHECK (balance >= -999999999.99),
    CONSTRAINT CK_Account_DailyLimit CHECK (dailyTransactionLimit >= 0),
    CONSTRAINT CK_Account_FreezeType CHECK (freezeType IS NULL OR freezeType IN ('FULL_FREEZE', 'DEBIT_ONLY', 'CREDIT_ONLY'))
);
GO

-- Create InterestRate table
CREATE TABLE InterestRate (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    productType NVARCHAR(50) NOT NULL,
    productName NVARCHAR(100) NOT NULL,
    rate DECIMAL(5,4) NOT NULL,
    term NVARCHAR(20) NULL,
    minimumAmount DECIMAL(18,2) NULL,
    maximumAmount DECIMAL(18,2) NULL,
    description NVARCHAR(500) NULL,
    isActive BIT NOT NULL DEFAULT 1,
    effectiveDate DATE NOT NULL,
    lastUpdated DATETIME2(7) NOT NULL DEFAULT GETUTCDATE(),
    
    -- Check constraints
    CONSTRAINT CK_InterestRate_Rate CHECK (rate >= 0 AND rate <= 1),
    CONSTRAINT CK_InterestRate_MinAmount CHECK (minimumAmount IS NULL OR minimumAmount >= 0),
    CONSTRAINT CK_InterestRate_MaxAmount CHECK (maximumAmount IS NULL OR maximumAmount >= 0),
    CONSTRAINT CK_InterestRate_MinMax CHECK (minimumAmount IS NULL OR maximumAmount IS NULL OR minimumAmount <= maximumAmount)
);
GO

-- Create AccountSummaryReport table (for reporting purposes)
CREATE TABLE AccountSummaryReport (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    accountType NVARCHAR(50) NOT NULL,
    totalAccounts INT NOT NULL DEFAULT 0,
    totalBalance DECIMAL(18,2) NOT NULL DEFAULT 0.00,
    averageBalance DECIMAL(18,2) NOT NULL DEFAULT 0.00,
    activeAccountsCount INT NOT NULL DEFAULT 0,
    frozenAccountsCount INT NOT NULL DEFAULT 0,
    closedAccountsCount INT NOT NULL DEFAULT 0,
    reportGeneratedAt DATETIME2(7) NOT NULL DEFAULT GETUTCDATE(),
    
    -- Check constraints
    CONSTRAINT CK_AccountSummary_TotalAccounts CHECK (totalAccounts >= 0),
    CONSTRAINT CK_AccountSummary_ActiveCount CHECK (activeAccountsCount >= 0),
    CONSTRAINT CK_AccountSummary_FrozenCount CHECK (frozenAccountsCount >= 0),
    CONSTRAINT CK_AccountSummary_ClosedCount CHECK (closedAccountsCount >= 0)
);
GO

-- Create indexes for better performance
CREATE INDEX IX_Account_CustomerId ON Account(customerId);
CREATE INDEX IX_Account_Status ON Account(status);
CREATE INDEX IX_Account_AccountType ON Account(accountType);
CREATE INDEX IX_Account_CreatedAt ON Account(createdAt);
CREATE INDEX IX_Customer_Email ON Customer(email);
CREATE INDEX IX_Customer_Status ON Customer(status);
CREATE INDEX IX_InterestRate_ProductType ON InterestRate(productType);
CREATE INDEX IX_InterestRate_IsActive ON InterestRate(isActive);
CREATE INDEX IX_InterestRate_EffectiveDate ON InterestRate(effectiveDate);
GO

-- Create triggers for updating updatedAt timestamps
CREATE TRIGGER TR_Customer_UpdateTimestamp
ON Customer
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE Customer 
    SET updatedAt = GETUTCDATE()
    FROM Customer c
    INNER JOIN inserted i ON c.customerId = i.customerId;
END;
GO

CREATE TRIGGER TR_Account_UpdateTimestamp
ON Account
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE Account 
    SET updatedAt = GETUTCDATE()
    FROM Account a
    INNER JOIN inserted i ON a.accountNumber = i.accountNumber;
END;
GO

CREATE TRIGGER TR_InterestRate_UpdateTimestamp
ON InterestRate
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE InterestRate 
    SET lastUpdated = GETUTCDATE()
    FROM InterestRate ir
    INNER JOIN inserted i ON ir.id = i.id;
END;
GO

-- Insert sample data
INSERT INTO Customer (customerId, firstName, lastName, email, customerType, status)
VALUES 
    ('CUST001', 'John', 'Doe', 'john.doe@email.com', 'INDIVIDUAL', 'ACTIVE'),
    ('CUST002', 'Jane', 'Smith', 'jane.smith@email.com', 'PREMIUM', 'ACTIVE'),
    ('CUST003', 'ABC Corp', 'Limited', 'contact@abccorp.com', 'BUSINESS', 'ACTIVE');
GO

INSERT INTO Account (accountNumber, customerId, accountType, balance, dailyTransactionLimit, status)
VALUES 
    ('ACC001001', 'CUST001', 'SAVINGS', 15000.00, 2000.00, 'ACTIVE'),
    ('ACC001002', 'CUST001', 'CHECKING', 5000.00, 5000.00, 'ACTIVE'),
    ('ACC002001', 'CUST002', 'SAVINGS', 50000.00, 10000.00, 'ACTIVE'),
    ('ACC003001', 'CUST003', 'BUSINESS', 100000.00, 50000.00, 'ACTIVE');
GO

INSERT INTO InterestRate (productType, productName, rate, term, minimumAmount, maximumAmount, description, isActive, effectiveDate)
VALUES 
    ('SAVINGS', 'Standard Savings', 0.0250, '1 Year', 100.00, 100000.00, 'Standard savings account interest rate', 1, '2024-01-01'),
    ('SAVINGS', 'Premium Savings', 0.0350, '1 Year', 10000.00, 1000000.00, 'Premium savings account for high-value customers', 1, '2024-01-01'),
    ('BUSINESS', 'Business Account', 0.0200, '1 Year', 1000.00, NULL, 'Business account interest rate', 1, '2024-01-01');
GO

PRINT 'SECURE_BANKING_DB database created successfully with all tables, constraints, indexes, and sample data!';