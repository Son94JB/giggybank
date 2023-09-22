package com.d208.giggyrank.repository;

import com.d208.giggyrank.domain.begger.HallOfBegger;
import com.d208.giggyrank.dto.BeggerRankDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallOfBeggerRepository extends CrudRepository<BeggerRankDto, String> {
}
