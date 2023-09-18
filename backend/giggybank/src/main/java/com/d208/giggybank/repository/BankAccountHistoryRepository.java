package com.d208.giggybank.repository;

import com.d208.giggybank.domain.BankAccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountHistoryRepository extends JpaRepository<BankAccountHistory, Long> {

    List<BankAccountHistory> findByBankAccountId(Long useraccountid);
}
