package com.d208.giggyrank.repository;

import com.d208.giggyrank.domain.begger.HallOfBegger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallOfBeggerRepository extends JpaRepository<HallOfBegger, Long> {
}
