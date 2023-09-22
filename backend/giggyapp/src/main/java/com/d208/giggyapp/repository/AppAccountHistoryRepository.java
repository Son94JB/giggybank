package com.d208.giggyapp.repository;

import com.d208.giggyapp.domain.AppAccountHistory;
import com.d208.giggyapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AppAccountHistoryRepository extends JpaRepository<AppAccountHistory, Long> {
    Optional<AppAccountHistory> findFirstByUserOrderByTransactionDateDesc(User user);
}
