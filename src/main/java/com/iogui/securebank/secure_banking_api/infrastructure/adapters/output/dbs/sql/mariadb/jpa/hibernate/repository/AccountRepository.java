package com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.repository;

import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.AccountData;
import com.iogui.securebank.secure_banking_api.infrastructure.adapters.output.dbs.sql.mariadb.jpa.hibernate.data.AccountSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, String> {
    List<AccountData> findByCustomerId(String customerId);

    @Query("SELECT a FROM AccountData a WHERE a.accountNumber = :accountNumber")
    Optional<AccountData> findByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("""
        SELECT\s
            a.accountType as accountType,
            COUNT(a) as totalAccounts,
            SUM(a.balance) as totalBalance,
            AVG(a.balance) as averageBalance,
            SUM(CASE WHEN a.status = 'ACTIVE' THEN 1 ELSE 0 END) as activeAccounts,
            SUM(CASE WHEN a.status = 'FROZEN' THEN 1 ELSE 0 END) as frozenAccounts,
            SUM(CASE WHEN a.status = 'CLOSED' THEN 1 ELSE 0 END) as closedAccounts
        FROM AccountData a
        GROUP BY a.accountType
        ORDER BY SUM(a.balance) DESC
       \s""")
    List<AccountSummaryProjection> findAccountsSummary();

}
