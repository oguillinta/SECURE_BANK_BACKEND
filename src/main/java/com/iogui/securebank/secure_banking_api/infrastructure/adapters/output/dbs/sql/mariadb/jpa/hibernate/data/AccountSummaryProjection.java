package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data;

import java.math.BigDecimal;

public interface AccountSummaryProjection {
    String getAccountType();

    Integer getTotalAccounts();

    BigDecimal getTotalBalance();

    BigDecimal getAverageBalance();

    Integer getActiveAccounts();

    Integer getFrozenAccounts();

    Integer getClosedAccounts();
}
