package com.d208.giggyapp.repository;

import com.d208.giggyapp.domain.game.HallOfFame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallOfFameRepository extends JpaRepository<HallOfFame, Long> {
}