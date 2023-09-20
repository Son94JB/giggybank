package com.d208.giggyapp.repository;

import com.d208.giggyapp.domain.AppAccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppAccountHistoryRepository extends JpaRepository<AppAccountHistory, Long> {
}
